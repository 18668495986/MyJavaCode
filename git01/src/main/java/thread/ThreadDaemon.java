package thread;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月23日
 *
 * @author 徐威
 * @version : 1.0
 */
public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyDaemonThread());
        thread.setDaemon(true); //设置守护线程，主线程结束，子线程也就结束
        thread.start();
        for(int i=0;i<=1000;i++){
            System.out.println(i);
        }
    }
}

class MyDaemonThread implements Runnable{

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1);
                System.out.println("-------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


