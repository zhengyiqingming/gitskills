package com.baizhi.imgutil;

import java.io.File;


public class AudioSizeUtil {
    public static String getSize(String fileUrl){
        File file = new File(fileUrl);
        //测试此文件是否存在
        if(file.exists()){
            //如果是文件夹
            //这里只检测了文件夹中第一层 如果有需要 可以继续递归检测
            if(file.isDirectory()){
                int size = 0;
                for(File zf : file.listFiles()){
                    if(zf.isDirectory()) continue;
                    size += zf.length();
                }
                return size/1024f/1024f+"MB";
            }else{
                return file.length()/1024f/1024f+"MB";
            }
            //如果文件不存在
        }else{
            throw new RuntimeException("文件不存在");
        }
    }
}
