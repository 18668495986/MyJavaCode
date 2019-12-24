package netprogramming;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
public class Server_UDP { // UDP 双人通信
    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket(10011);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        DatagramPacket packet1 = null;
        byte[] bs2 = null;
        String s = null;
        System.out.println("已连接，开始发送...");
        while (loop) { //发送
            try {
                String line = scanner.next();
                byte[] bs = line.getBytes("utf-8");
                InetAddress address = InetAddress.getByName("10.31.167.16");
                DatagramPacket packet = new DatagramPacket(bs, bs.length, address, 10086);
                socket.send(packet);

                //---------接收-----------------
                bs2 = new byte[1024];
                packet1 = new DatagramPacket(bs2, bs2.length);
                socket.receive(packet1);
                s = new String(bs2, 0, bs2.length);
                System.out.println(address + "说：" + s);
                if (s == "bye") {
                    loop = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }
        socket.close();
    }
}
