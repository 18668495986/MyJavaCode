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
public class DeadLockDemo { //模拟死锁
    public static void main(String[] args) {
        new Thread(new DeadLockThread(), "wty").start();
        new Thread(new DeadLockThread(), "zwj").start();
    }
}

class DeadLockThread implements Runnable {
    public static Object o1 = new Object(); //模拟 左筷子
    public static Object o2 = new Object(); //模拟 左筷子
    public static boolean should = true; //是否要开始抢筷子

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {

            if (should) {
                synchronized (o1) { //抢到o1筷子  只有先抢到一只筷子，再抢到另一只筷子才能开始吃饭，所以一个锁里要包括另一个锁
                    System.out.println(Thread.currentThread().getName() + "抢到了o1筷子，开始抢o2");
                    should = false;
                    try { // 休息一会再去抢o2
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2) {
                        System.out.println(Thread.currentThread().getName() + "抢到o2，开始吃饭");
                    }
                }
            } else { // 没有o1 只能抢o2
                synchronized (o2) { //只有先抢到一只筷子，再抢到另一只筷子才能开始吃饭，所以一个锁里要包括另一个锁
                    System.out.println(Thread.currentThread().getName() + "抢到了o2，开始抢o1");

                    should = true;
                    try { // 休息一会再去抢o1
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1) {
                        System.out.println(Thread.currentThread().getName() + "抢到了o1，开始吃饭");
                    }
                }
            }
        }
    }
}