package day02.study.thread;

/**
 * Description：方法锁 源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月23日
 *
 * @author 徐威
 * @version : 1.0
 */
public class SynchronizeMethon { //方法锁 传入不同对象 同一方法
    static int ticketNum = 100;

    public static void main(String[] args) {
        Thread wty = new Thread(new MyMethod(), "wty");
        Thread zwj = new Thread(new MyMethod(), "zwj");
        Thread zpb = new Thread(new MyMethod(), "zpb");
        Thread cyl = new Thread(new MyMethod(), "cyl");
        wty.start();
        zwj.start();
        zpb.start();
        cyl.start();
    }
}

class MyMethod implements Runnable { //加了static就是类方法，多个对象也都对应着同一个方法
    public static synchronized void getTicket() { //如果传入的是不同对象，需要在方法前加static将方法变成类方法，才能锁住
        if (SynchronizeMethon.ticketNum <= 0) return;
        System.out.println(Thread.currentThread().getName() + "抢到一张票,还剩：" + (--SynchronizeMethon.ticketNum) + "张票");
    }

    @Override
    public void run() {
        while (SynchronizeMethon.ticketNum > 0) {
            try {
                Thread.sleep(200);
                getTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}