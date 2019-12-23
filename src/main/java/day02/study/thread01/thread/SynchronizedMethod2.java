package day02.study.thread01.thread;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月23日
 *
 * @author 徐威
 * @version : 1.0
 */
public class SynchronizedMethod2 {
    static int ticketNum = 100;
    public static void main(String[] args) { //传入同一对象，对象方法
        Thread t = new Thread(new MyMethod2());
        Thread wty = new Thread(t, "wty");
        Thread zwj = new Thread(t, "zwj");
        Thread zpb = new Thread(t, "zpb");
        Thread cyl = new Thread(t, "cyl");
        wty.start();
        zwj.start();
        zpb.start();
        cyl.start();
    }
}

class MyMethod2 implements Runnable{
    /**
     * 如果传入的是不同对象，需要在方法前加static将方法变成类方法，才能锁住
     * 如果不加static也可，将其中传入的对象，设置为同一个对象
     */
    public synchronized void getTicket() { //不加static 就是对象方法，一个对象可以对应一个方法
        if (SynchronizedMethod2.ticketNum <= 0) return;
        System.out.println(Thread.currentThread().getName() + "抢到一张票,还剩：" + (--SynchronizedMethod2.ticketNum) + "张票");
    }

    @Override
    public void run() {
        while (SynchronizedMethod2.ticketNum > 0) {
            try {
                Thread.sleep(200);
                getTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
