package netprogramming;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class ClientDemo02 { //TCP持续通讯 达到某一条件退出
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        System.out.println("准备发送...");
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        try {
            socket = new Socket("127.0.0.1", 7777);
            while (loop) {
                OutputStream outputStream = socket.getOutputStream();
                String line = scanner.nextLine();
                outputStream.write(line.getBytes());
                outputStream.flush();
                if(line.equals("bye")){ // 当发出“bye”时 客户端就退出
                    break;
                }

                //---------读取--------------
                byte[] bs = new byte[1024];
                InputStream inputStream = socket.getInputStream();
                inputStream.read(bs);
                String message = new String(bs);
                System.out.println(message);
            }
            System.out.println("开始退出");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
