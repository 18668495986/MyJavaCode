package networkprogramming;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
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
public class ClientDemo { //TCP多人通信
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",6688);
            System.out.println("开始连接服务器");
            new Thread(new ClientReceive(socket)).start();
            new Thread(new ClientSend(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ClientSend implements Runnable{
    private Socket socket;
    Scanner sc=new Scanner(System.in);

    public ClientSend(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
//            byte[] bs = new byte[1024];
            while (true) {
                String line = sc.nextLine();
                String message=socket.getInetAddress().getHostName()+line;
                out.write(message.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientReceive implements Runnable{
    private Socket socket;
    public ClientReceive(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
            while (true) {
                byte[] bs = new byte[512];
                int len = bin.read(bs);
                System.out.println(new String(bs,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}