package com.hlideal.framework.admin.control;

import com.alibaba.fastjson.JSONObject;
import com.hlideal.common.base.utils.FileUtils;
import com.hlideal.common.mybatis.mapper.JsonMapper;
import com.hlideal.framework.admin.AdminBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by Intellij idea
 * User: liu.y
 * Date: 2016/12/20 0020 10:48
 * Description:
 * To change this template use File | Setting | File and Code Templates
 */
@Controller
@RequestMapping("/admin/backstage/upload")
public class FileUploadController extends AdminBaseController{


    private static final String dir_address = "e:/file/excel/";
    public static Map<String,List<Object>> objects = new HashMap<>();
    private long size = 0;

    @RequestMapping("saveFile.htm")
    @ResponseBody
    public void saveFile(HttpServletRequest request,
                         HttpServletResponse response, String jsonCallBack) throws IOException {
        JSONObject json = new JSONObject();


        String fileName  = request.getParameter("fileName");
        String fileSize = request.getParameter("fileSize");
        String uuid = request.getParameter("uuid");
        String file_progressId = request.getParameter("progressId");
        String pUipId = request.getParameter("pUipId");
        Boolean chunked = Boolean.valueOf(request.getParameter("chunked"));
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;//request强制转换注意
        MultipartFile file = mRequest.getFile("data");

        if(!chunked){  //是否分块传送
            try {
                InputStream inputStream = file.getInputStream();
                FileCopyUtils.copy(file.getBytes(), new File(dir_address+file.getOriginalFilename()));

                if(FileUtils.writeToFile(file.getOriginalFilename(),this.convertStreamToString(inputStream),true)==0){
                    json.put("code",0);
                    json.put("message","保存失败");
                }else{
                    json.put("code",1);
                    json.put("message","保存成功");
                    size = file.getSize();
                }
            } catch (IOException e) {
                json.put("code",0);
                json.put("message","保存失败");
            }
        }else{
            if(objects.size()!=0 && objects.get(uuid) !=null && objects.get(uuid).size()>0){
                objects.get(uuid).add(file);
            }else{
                List<Object> objectList = new ArrayList<>();
                objectList.add(file);
                objects.put(uuid,objectList);
            }
            size = this.merge(fileName,file,uuid);  //合并文件
        }
        json.put("fileSize",size);
        json.put("totalSize",fileSize);
        json.put("file_progressId",file_progressId);
        json.put("pUipId",pUipId);
        response.getWriter().print(JsonMapper.toJsonString(json));
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static long merge(String fileName,MultipartFile file,String uuid){

        String destPath = dir_address+fileName;//合并后的文件路径

        File destFile = new File(destPath);//合并后的文件
        OutputStream out = null;
        BufferedOutputStream bos = null;
        try {
            out = new FileOutputStream(destFile,true);  //续写文件
            bos = new BufferedOutputStream(out);

            InputStream in = file.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            byte[] bytes = new byte[1024*1024];
            int len = -1;
            while((len = bis.read(bytes))!=-1){
                bos.write(bytes, 0, len);
            }
            bis.close();
            in.close();
            objects.remove(uuid);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭流
            try {
                if(bos!=null)
                {
                        bos.close();
                }
                if(out!=null)
                {
                        out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return destFile.length();
    }
}
