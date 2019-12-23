package day02.study.thread01.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月23日
 *
 * @author 徐威
 * @version : 1.0
 */
public class SingletonDemo {
    public static void main(String[] args) {
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
    }
}

class TestThread implements Runnable { //<测试代码>
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            SingletonLazy lazy = SingletonLazy.getInstance();
        }
        System.out.println("lazy" + SingletonLazy.set); //lazy[day02.study.thread01.thread.SingletonLazy@1cc8988a, day02.study.thread01.thread.SingletonLazy@5e99dd12]

//        for (int i = 0; i < 10; i++) {
//            SingletonHungry hungry = SingletonHungry.getInstance();
//        }
//        System.out.println("hungry"+SingletonHungry.set);
    }
}


/**
 * 懒汉  存在线程安全问题
 */
class SingletonLazy {
    static Set<SingletonLazy> set = new HashSet(); //<测试代码>
    private static SingletonLazy instance; //私有属性

    private SingletonLazy() {
    } //私有的构造函数

    public static SingletonLazy getInstance() { // 公有的获得对象的方法  如果不想出现改问题，需要在方法前加synchronized
        if (instance == null) instance = new SingletonLazy(); //
        set.add(instance);
        return instance;
    }

}

/**
 * 饿汉  不存在线程安全问题
 */
class SingletonHungry {
    static HashSet<SingletonHungry> set = new HashSet(); //<测试代码>
    private static SingletonHungry instance = new SingletonHungry(); //私有属性

    private SingletonHungry() {
    } //私有的构造函数

    public static SingletonHungry getInstance() { // 公有的获得对象的方法
        set.add(instance);
        return instance;
    }
}