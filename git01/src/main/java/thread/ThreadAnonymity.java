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
public class ThreadAnonymity {
    public static void main(String[] args) {
        //1.创建了内部类的对象（匿名对象+{} 就是匿名类）
        new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("hi");
                }
            }
        }.start();

        new Thread(new MyThread()).start();
        Thread t2=new Thread(new MyThread());
        t2.setPriority(Thread.MAX_PRIORITY);//设置线程的优先级 1~10
        t2.setPriority(8);

//        Thread t1 = new Thread(){ //非匿名内部类
//            @Override
//            public void run() {
//                while (true){
//                    System.out.println("hi");
//                }
//            }
//        };
//        t1.start();
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("=========");
        }
    }
}
