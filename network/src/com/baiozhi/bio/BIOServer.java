package com.baiozhi.bio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于bio io模型的服务器编程
 *
 * 单请求: BIO Server只能处理一个客户端的请求
 *        服务器应该具备处理多个客户端请求的能力
 * 多请求: BIO Server可以处理多个客户端的请求
 *        缺点: 单线程 main 串行化
 *
 * 多线程多请求: BIO Server可以处理多个客户端的请求 并行处理
 *        理论 非常好
 *        实际 线程 系统宝贵的资源 不能够无限的创建
 *
 *        子线程数量:客户端的数量 1:1
 *
 * 多线程多请求(优化版): BIO Server可以处理多个客户端的并发请求,线程数量有限
 *        线程池 控制线程的数量 避免线程资源的无限创建 减少系统资源的浪费
 *
 *        问题:1. 因为线程池中线程数量有限,超出线程数量外的并发请求,等待(IO处理的线程释放到线程池,然后再复用)
 *            2. 阻塞
 *               先有线程 再有一次完整的IO操作 线程资源的利用率不高
 *
 *        BIO 同步阻塞IO  线程利用率不高 先有线程 再有一次完整的IO操作 IO没有完成 占用,会造成线程资源的浪费
 *
 *        NIO 同步非阻塞IO 线程利用率相对比较高 关注事件
 *
 *        AIO 异步非阻塞IO 线程利用率更高  关注事件
 *
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        //server1();
        //server2();
        //server3();
        server4();
    }

    /**
     * 单请求的服务器模型
     */
    public static void server1() throws IOException {
        //1.初始化服务器的引导对象
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器已启动!在8888端口等待客户端的连接......");


        //2. 等待客户端的连接 建立连接对象
        Socket socket = serverSocket.accept();
        System.out.println("-------------------------------");

        //3. IO操作(请求/响应处理)
        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] bytes = new byte[1024];
        while (true) {
            int read = inputStream.read(bytes);
            if (read == -1) break;
            baos.write(bytes, 0, read);
        }

        System.out.println("服务器接收的请求数据:" + new String(baos.toByteArray()));
        //表示 请求数据处理完成
        socket.shutdownInput();

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("地瓜地瓜,我是土豆".getBytes());
        socket.shutdownOutput();
        //4.释放资源
        socket.close();

    }

    /**
     * 多请求的服务器模型
     *
     * @throws IOException
     */
    public static void server2() throws IOException {
        //1.初始化服务器的引导对象
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器已启动!在8888端口等待客户端的连接......");
        while (true) {
            //2. 等待客户端的连接 建立连接对象
            Socket socket = serverSocket.accept();
            System.out.println("-------------------------------");

            //3. IO操作(请求/响应处理)
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            while (true) {
                int read = inputStream.read(bytes);
                if (read == -1) break;
                baos.write(bytes, 0, read);
            }

            System.out.println("服务器接收的请求数据:" + new String(baos.toByteArray()));
            //表示 请求数据处理完成
            socket.shutdownInput();

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("地瓜地瓜,我是土豆".getBytes());
            socket.shutdownOutput();
            //4.释放资源
            socket.close();
        }
    }

    /**
     * 多请求多线程的服务器模型
     *
     * @throws IOException
     */
    public static void server3() throws IOException {
        //1.初始化服务器的引导对象
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器已启动!在8888端口等待客户端的连接......");
        while (true) {
            //2. 等待客户端的连接 建立连接对象
            Socket socket = serverSocket.accept();// 阻塞方法 直到客户端和服务器建立连接对象后 阻塞状态消除

            //主线程只负责建立连接对象 子线程完成请求和响应的IO处理
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("IO处理的子线程:" + Thread.currentThread().getId());
                        //3. IO操作(请求/响应处理)
                        InputStream inputStream = socket.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] bytes = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bytes);
                            if (read == -1) break;
                            baos.write(bytes, 0, read);
                        }

                        System.out.println("服务器接收的请求数据:" + new String(baos.toByteArray()));
                        //表示 请求数据处理完成
                        socket.shutdownInput();

                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write("地瓜地瓜,我是土豆".getBytes());
                        socket.shutdownOutput();
                        //4.释放资源
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

    /**
     * 多请求多线程的服务器模型(优化版)
     *
     * @throws IOException
     */
    public static void server4() throws IOException {
        //1.初始化服务器的引导对象
        ServerSocket serverSocket = new ServerSocket(8888);
        //创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        System.out.println("服务器已启动!在8888端口等待客户端的连接......");
        while (true) {
            //2. 等待客户端的连接 建立连接对象
            Socket socket = serverSocket.accept();// 阻塞方法 直到客户端和服务器建立连接对象后 阻塞状态消除

            // 主线程只负责建立连接对象 子线程完成请求和响应的IO处理
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("IO处理的子线程:" + Thread.currentThread().getId());
                        //3. IO操作(请求/响应处理)
                        InputStream inputStream = socket.getInputStream();

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();

                        byte[] bytes = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bytes);
                            if (read == -1) break;
                            baos.write(bytes, 0, read);
                        }

                        System.out.println("服务器接收的请求数据:" + new String(baos.toByteArray()));
                        //表示 请求数据处理完成
                        socket.shutdownInput();

                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write("地瓜地瓜,我是土豆".getBytes());
                        socket.shutdownOutput();

                        //4.释放资源
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }
}