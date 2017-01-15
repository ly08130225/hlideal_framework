package com.hlideal.framework.admin.utils;

import com.hlideal.framework.admin.excle_model.SysroleExcel;
import com.hlideal.framework.core.entity.Sysrole;
import com.hlideal.framework.core.service.SysroleService;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Intellij idea
 * User: liu.y
 * Date: 2016/12/28 0028 10:06
 * Description:
 * To change this template use File | Setting | File and Code Templates
 */
public class ExportExcelUtil<T> {

    public void exportExcel(String[] headers, Collection<T> dataset,
                            OutputStream out) {
        exportExcel("导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
    }

    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
    public void exportExcel(String title, String[] headers,
                            Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        int sheetCount = 1000;
        if (dataset.size() > sheetCount) {
            Iterator<T> it = dataset.iterator();
            for (int i = 0; i <= 4; i++) {
                int index = 0;
                List<T> list = new ArrayList<T>();
                while (it.hasNext()) {
                    index++;
                    if (index < sheetCount) {
                        list.add(it.next());
                    } else {
                        break;
                    }
                }
                generateSheet(list, style, style2, workbook, pattern, headers,
                        title + "_" + (i + 1));
            }
        } else {
            generateSheet(dataset, style, style2, workbook, pattern, headers,
                    title);
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateSheet(Collection<T> dataset, HSSFCellStyle style,
                              HSSFCellStyle style2, HSSFWorkbook workbook, String pattern,
                              String[] headers, String title) {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("author");

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Class aClass= t.getClass();
            Field[] fields = aClass.getDeclaredFields();
            if(t.getClass().getSuperclass()!=null){
                //若有父类,则一起加载
                fields = (Field[]) ArrayUtils.addAll(fields,aClass.getSuperclass().getDeclaredFields());
            }

            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.isAnnotationPresent(ExcelAnnotation.class);
                if (field.toString().contains("static")) {
                    continue;
                }
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        if (value == null) {
                            value = "";
                        }
                        textValue = value.toString();
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("/^[0-9]*$/");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(
                                    textValue);
                            HSSFFont font3 = workbook.createFont();
                            font3.setColor(HSSFColor.BLACK.index);
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }finally {
                    // 清理资源
                }
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ExportExcelUtil<SysroleExcel> sysroleExportExcel = new ExportExcelUtil<>();

        String[] header = {"id","姓名","操作人","操作时间","备注"};
        List<SysroleExcel> sysrolesData = new ArrayList<>();

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/resources/spring/spring-context.xml");
//        SysroleService sysroleService = applicationContext.getBean("sysroleService",SysroleService.class);
        SysroleExcel sysrole = new SysroleExcel();
        sysrole.setId("1");
        sysrole.setName("test1");
        sysrole.setCreateName("test11");
        sysrole.setCreateTime(new Date());

        sysrolesData.add(sysrole);
        SysroleExcel sysrole1 = new SysroleExcel();
        sysrole1.setId("2");
        sysrole1.setName("test2");
        sysrole1.setCreateName("test12");
        sysrole1.setCreateTime(new Date());
        sysrolesData.add(sysrole1);

//        sysroleService.findList(sysrole).forEach(sysrole2->{
//            Sysrole sysrole1 = new Sysrole();
//            sysrole1.setId(sysrole2.getId());
//            sysrole1.setName(sysrole2.getName());
//            sysrole1.setCreatename(sysrole2.getCreatename());
//            sysrole1.setCreatedate(sysrole2.getCreatedate());
//            sysrolesData.add(sysrole1);
//        });

        OutputStream out = new FileOutputStream("E://a.xls");
        sysroleExportExcel.exportExcel(header,sysrolesData,out);
    }


}
