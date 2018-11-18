package com.baizhi.fdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class FastDFSDemo {
    private StorageClient client = null;
    /**
     *
     * 初始化方法
     */
    @Before
    public void before() throws IOException, MyException {
        // 加载配置文件
        ClientGlobal.init("fdfs_client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        // 通过client对象操作分布式文件系统
        client = new StorageClient(trackerServer, null);
    }


    @Test
    public void testUpload() throws IOException, MyException {
        String[] fileId = client.upload_file("F:\\1.jpg","jpg",new NameValuePair[]{new NameValuePair("width","1024")});
        System.out.println(fileId[0]+" | "+fileId[1]);
    }

    @Test
    public void testDownload() throws IOException, MyException {
        byte[] bytes = client.download_file("group1", "M00/00/00/wKh_jVvirtKAF58RAAKLdiyCDf8060.jpg");
        FileOutputStream outputStream = new FileOutputStream("F:\\2.jpg");
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void testOther() throws IOException, MyException {
        client.delete_file("group1", "M00/00/00/wKh_jVvirtKAF58RAAKLdiyCDf8060.jpg");
        FileInfo fileInfo = client.get_file_info("group1", "M00/00/00/wKh_jVvirtKAF58RAAKLdiyCDf8060.jpg");
        System.out.println(fileInfo.getFileSize() + " | "+ fileInfo.getCreateTimestamp() +" | "+ fileInfo.getSourceIpAddr());
    }

    @After
    public void after(){
        System.out.println("after");
    }

}
