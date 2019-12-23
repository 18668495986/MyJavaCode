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
public class ThreadJoinDemo { //线程合并
    public static void main(String[] args) throws InterruptedException {
        Thread xixi = new Thread(new JoinThread(), "xixi");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(200);
            if (i == 20) {
                xixi.start();
                xixi.join();
            }
            Thread.currentThread().setName("I am Main");
            System.out.println(Thread.currentThread().getName()+i);
        }
    }
}

class JoinThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}