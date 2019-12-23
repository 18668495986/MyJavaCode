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
public class SynchronizedClass {
    static int ticket = 10;

    public static void main(String[] args) {
        Thread xm = new Thread(new TicketThread2(), "xm");
        Thread xh = new Thread(new TicketThread2(), "xh");
        Thread xg = new Thread(new TicketThread2(), "xg");
        Thread xl = new Thread(new TicketThread2(), "xl");
        xm.start();
        xh.start();
        xg.start();
        xl.start();
    }
}

class TicketThread2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                synchronized ("") { //类锁
                    if (SynchronizedClass.ticket <= 0) break;
                    System.out.println(Thread.currentThread().getName() + "抢到一张票，还剩：" + (--SynchronizedClass.ticket) + "张票");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
