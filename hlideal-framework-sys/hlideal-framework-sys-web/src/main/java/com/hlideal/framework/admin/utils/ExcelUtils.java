package com.hlideal.framework.admin.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Intellij idea
 * User: liu.y
 * Date: 2016/12/27 0027 16:37
 * Description:
 * To change this template use File | Setting | File and Code Templates
 */
public class ExcelUtils {

    public static void parseXlsx(String fileAddress) throws IOException {
        InputStream stream = new FileInputStream(fileAddress);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(stream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        int rowStart = xssfSheet.getFirstRowNum();
        int rowEnd = xssfSheet.getLastRowNum();
        for(int i=rowStart;i<=rowEnd;i++)
        {
            XSSFRow row = xssfSheet.getRow(i);
            if(null == row) continue;
            int cellStart = row.getFirstCellNum();
            int cellEnd = row.getLastCellNum();

            for(int k=cellStart;k<=cellEnd;k++)
            {
                XSSFCell cell = row.getCell(k);
                if(null==cell) continue;

                switch (cell.getCellType())
                {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        System.out.print(cell.getNumericCellValue()
                                + "\t");
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        System.out.print(cell.getStringCellValue()
                                + "\t");
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        System.out.println(cell.getBooleanCellValue()
                                + "\t");
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        System.out.print(cell.getCellFormula() + "\t");
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        System.out.println(" ");
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        System.out.println(" ");
                        break;
                    default:
                        System.out.print("未知类型   ");
                        break;
                }

            }
            System.out.print("\n");
        }
    }

    public static void parseXls(String address) throws IOException {
        File file = new File(address);
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
        HSSFWorkbook hssfWorkbook =  new HSSFWorkbook(poifsFileSystem);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

        int rowstart = hssfSheet.getFirstRowNum();
        int rowEnd = hssfSheet.getLastRowNum();
        for(int i=rowstart;i<=rowEnd;i++)
        {
            HSSFRow row = hssfSheet.getRow(i);
            if(null == row) continue;
            int cellStart = row.getFirstCellNum();
            int cellEnd = row.getLastCellNum();

            for(int k=cellStart;k<=cellEnd;k++)
            {
                HSSFCell cell = row.getCell(k);
                if(null==cell) continue;

                switch (cell.getCellType())
                {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        System.out.print(cell.getNumericCellValue()
                                + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        System.out.print(cell.getStringCellValue()
                                + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        System.out.println(cell.getBooleanCellValue()
                                + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        System.out.print(cell.getCellFormula() + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        System.out.println(" ");
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        System.out.println(" ");
                        break;
                    default:
                        System.out.print("未知类型   ");
                        break;
                }

            }
            System.out.print("\n");
        }

    }

    /**
     * 生成Excel
     * @param models 封装需要导出的数据BEAN结合
     * @param className 导成Excel的实体BEAN包名.类名
     * @param excelName 生成的Excel名
     */
    public static void exportExcel(List<List> models, List<String> className,
                                   String excelName, HttpServletResponse response)
            throws IOException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        OutputStream os = response.getOutputStream();

//        WritableWorkbook workbook = Workbook.createWorkbook(os);
//        WritableSheet sheet = workbook.createSheet(excelName, 0);
//
//        //绘画excle 表格
//          auto10bagger
//        workbook.write();
//        workbook.close();

        os.flush();
        os.close();
    }

    public static void main(String[] args) throws IOException {
        ExcelUtils.parseXlsx("E:\\aidi-company\\weekly-report\\工作周报模板.xlsx");
        ExcelUtils.parseXls("E:\\aidi-company\\weekly-report\\新建 Microsoft Excel 97-2003 工作表.xls");

    }
}
