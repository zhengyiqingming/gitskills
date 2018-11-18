package com.baizhi.fdfs.test;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FastDFSDemo {

    @Autowired
    private FastFileStorageClient client;  // 通过client对象操作分布式文件系统

    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("F:\\1521006626111089739.mp4");
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = client.uploadFile(inputStream, file.length(), "mp4", null);//　输入流　文件的大小　　文件的扩展名　元数据列表

        //缩略图片
        //StorePath storePath = client.uploadImageAndCrtThumbImage(inputStream, file.length(), "jpg", null);
        System.out.println(storePath.getFullPath());
    }

    @Test
    public void testDownload() throws IOException {
        //byte[] bytes = client.downloadFile("group1", "M00/00/00/wKh_jVvi-v6ATYJ4AAGaAGytmvQ880.mp4", new DownloadByteArray());
        //byte[] bytes = client.downloadFile("group1", "M00/00/00/wKi6gFviuxGAGGNgAABPZvzPBOk478.jpg", new DownloadByteArray());
        byte[] bytes = client.downloadFile("group1","M00/00/00/wKh_jVvi_NOASARgAAKLdiyCDf8431_150x150.jpg",new DownloadByteArray());
        FileOutputStream outputStream = new FileOutputStream("F:\\suolue4.jpg");
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

}
