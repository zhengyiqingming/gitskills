package com.baizhi.bio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BioFileCopy {
    public static void main(String[] args) throws IOException {
        FileInputStream in=new FileInputStream("F:\\1.jpg");
        FileOutputStream out=new FileOutputStream("E:\\2.jpg");
        byte[] bytes=new byte[1024];
        while(true){
            // 整数: 读到的字节数量 末尾 -1
            int read=in.read(bytes);
            if(read==-1)break;
            out.write(bytes);
        }
        out.flush();
        out.close();
        in.close();
    }
}
