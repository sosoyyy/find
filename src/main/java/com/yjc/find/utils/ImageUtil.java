package com.yjc.find.utils;

import org.apache.commons.lang.math.RandomUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUtil {


    public static String  storeImg(InputStream inputStream,String fileName) throws IOException {
        String name = getRandomFileName()+getFileExtension(fileName);
        String target = getBasePath()+name;
        writeToLocal(target,inputStream);
        return name;
    }


    private static void writeToLocal(String dest, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(dest);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

    /**
     * 获取随机文件名
     * @return
     */
    public static String getRandomFileName(){
        //获取随机五位数
        int rannum =  RandomUtils.nextInt(89999)+10000;
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);

        return dateString+rannum;
    }

    /**
     * 删除文件或路径
     * @param storePath 相对地址
     */
    public static void deleteFileOrPath(String storePath){
        String path =getBasePath()+storePath;
        File fileOrPath = new File(path);
        if(fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File files[] = fileOrPath.listFiles();
                for (int i=0;i<files.length;i++){
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }else {
        }
    }

    /**
     * 获取文件扩展名
     * @param fileName
     * @return
     */
    public   static  String getFileExtension(String fileName){
        return fileName.substring((fileName.lastIndexOf(".")));
    }
    /***
     * 获取根目录
     * @return
     */
    public static String getBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/work/find/images/";
        } else {
            basePath = "/root/find/images/";
        }

        return basePath;
    }
}
