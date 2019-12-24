package networkprogramming;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月24日
 *
 * @author 徐威
 * @version : 1.0
 */
public class ServerDemo02 { //TCP持续通讯 达到某一条件退出
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Scanner scanner = new Scanner(System.in);
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("开始接收...");
            Socket socket = serverSocket.accept();
            System.out.println("连接成功");

            while (true) {
                InputStream inputStream = socket.getInputStream();
                byte[] bs = new byte[512];
                int len = inputStream.read(bs);
                String message = new String(bs, 0, len);
                System.out.println(message);
                if(message.equals("bye")){ // 当接收到“bye” 服务器就退出
                    System.out.println("退出");
                    break;
                }

                //-------发送--------
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(scanner.nextLine().getBytes());
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
    }
}
