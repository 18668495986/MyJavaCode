package day02.study.thread01.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月23日
 *
 * @author 徐威
 * @version : 1.0
 */
public class ReentrantLockDemo { //java 1.5 版本加入的互斥锁 更加面向对象（将🔒也看成一个对象）
    public static void main(String[] args) {
//        Thread t = new Thread(new MyReentrantLock()); //方式1
//        Thread wty = new Thread(t, "wty");
//        Thread zwj = new Thread(t, "zwj");
//        Thread zpb = new Thread(t, "zpb");
//        Thread cyl = new Thread(t, "cyl");
//        wty.start();
//        zwj.start();
//        zpb.start();
//        cyl.start();

//        Thread wty = new Thread(new MyReentrantLock(), "wty"); //方式2
//        Thread zwj = new Thread(new MyReentrantLock(), "zwj");
//        Thread zpb = new Thread(new MyReentrantLock(), "zpb");
//        Thread cyl = new Thread(new MyReentrantLock(), "cyl");
//        wty.start();
//        zwj.start();
//        zpb.start();
//        cyl.start();

        ReentrantLock lock = new ReentrantLock(true);
        Thread wty = new Thread(new MyReentrantLock(lock), "wty");
        Thread zwj = new Thread(new MyReentrantLock(lock), "zwj");
        Thread zpb = new Thread(new MyReentrantLock(lock), "zpb");
        Thread cyl = new Thread(new MyReentrantLock(lock), "cyl");
        wty.start();
        zwj.start();
        zpb.start();
        cyl.start();
    }
}

class MyReentrantLock implements Runnable {
    static int tickNum = 100;
    //    ReentrantLock lock = new ReentrantLock(true);//方式 1
    //    static ReentrantLock lock = new ReentrantLock(true);//加上static 设置为公平锁 方式 2
    ReentrantLock lock;//设置为 特有的单一属性

    public MyReentrantLock() {
    }

    public MyReentrantLock(ReentrantLock lock) { //传入单个指定的对象（保证多线程间用到的是同一把锁）方式 3
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                lock.lock();
                if (tickNum <= 0) break;
                System.out.println(Thread.currentThread().getName() + "抢到一张票,还剩：" + (--tickNum) + "张票");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); //在finally里释放锁
            }
        }
    }
}
