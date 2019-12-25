package netprogramming;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月24日
 *
 * @author 徐威
 * @version : 1.0
 */
public class ServerDemo { //TCP多人通信
    static ArrayList<Socket> sList = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6688);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("有客户端连入");
                sList.add(socket);
                new Thread(new ServerReceiveThread(socket)).start(); //可以注掉
                new Thread(new ServerSendThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerSendThread implements Runnable {
    private Socket socket;
    BufferedInputStream bin;
    BufferedOutputStream bout;

    public ServerSendThread(Socket socket) {
        this.socket = socket;
        try {
            bin = new BufferedInputStream(socket.getInputStream());
            bout = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            while (true) {
                for(int i=0;i<ServerDemo.sList.size();i++){
                    Socket socket = ServerDemo.sList.get(i);
                    bin = new BufferedInputStream(socket.getInputStream());
                    bout = new BufferedOutputStream(socket.getOutputStream());
                    byte[] bs = new byte[512];
                    int len = bin.read(bs);
                    String line = new String(bs, 0, len);
                    String message=socket.getInetAddress().getHostName()+line;
                    bout.write(message.getBytes());
                    bout.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerReceiveThread implements Runnable {
    private Socket socket;

    public ServerReceiveThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            while (true) {
                byte[] bs = new byte[512];
                int len = in.read(bs);
                System.out.println(new String(bs, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

