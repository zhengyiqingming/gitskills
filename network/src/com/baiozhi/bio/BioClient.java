package com.baiozhi.bio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BioClient {
    public static void main(String[] args) throws IOException {
        //1.初始化客户端的连接对象
        Socket socket=new Socket("127.0.0.1",8888);
        //Socket socket=new Socket("127.0.0.1",9999);
        //IO操作(发送请求/获取响应数据)
        OutputStream outputStream=socket.getOutputStream();
        outputStream.write("你好!服务器,我是客户端...".getBytes());
        socket.shutdownOutput();


        InputStream inputStream=socket.getInputStream();
        byte[] bytes=new byte[1024];
        ByteArrayOutputStream baos=new ByteArrayOutputStream();

        while(true){
            int read=inputStream.read(bytes);
            if(read==-1)break;
            baos.write(bytes,0,read);
        }
        System.out.println("客户端接收的响应结果为"+new String(baos.toByteArray()));
        socket.shutdownInput();

        //释放资源
        socket.close();
    }
}