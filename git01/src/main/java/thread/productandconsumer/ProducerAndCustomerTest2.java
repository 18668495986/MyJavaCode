package thread.productandconsumer;

import java.util.concurrent.locks.Condition;
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
public class ProducerAndCustomerTest2 {  //生产者与消费者模式 <ReentrantLock方式>
    /**
     * 定义需要用到的公用变量
     */
    static boolean should = true;
    static String[] name = {"iPhone", "华为", "小米", "三星"}; //产品名称
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition condition = reentrantLock.newCondition(); //通过锁来拿到当前状态

    public static void main(String[] args) {
        Product product = new Product();
        //生产者
        new Thread(new Producer(product), "wty").start();
        new Thread(new Producer(product), "zwj").start();
        new Thread(new Producer(product), "zpb").start();

        //消费者
        new Thread(new Customer(product), "zq").start();
        new Thread(new Customer(product), "cq").start();
        new Thread(new Customer(product), "xw").start();
    }
}

class Producer2 implements Runnable {
    Product product;

    public Producer2(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true){
            ProducerAndCustomerTest2.reentrantLock.lock(); //进行加锁
            try {
                if(ProducerAndCustomerTest2.should){ //需要开始生产了
                    int i = (int)(Math.random()*4);
                    product.setName(ProducerAndCustomerTest2.name[i]); //生产相应的产品
                    System.out.println(Thread.currentThread().getName()+"生产了"+product.getName());
                    Thread.sleep(200);
                    ProducerAndCustomerTest2.should = false;
                    ProducerAndCustomerTest2.condition.signalAll();//切换状态 通知消费
                }else {
                    ProducerAndCustomerTest2.reentrantLock.wait(); //其他线程进行等待
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ProducerAndCustomerTest2.reentrantLock.unlock(); //进行锁的释放
            }
        }
    }
}

class Customer2 implements Runnable {
    Product product;

    public Customer2(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true){
            ProducerAndCustomerTest2.reentrantLock.lock();
            try {
                if(ProducerAndCustomerTest2.should){ //还在生产，需要等待
                    ProducerAndCustomerTest2.reentrantLock.wait();
                }else {
                    //抢到资源的 开始消费
                    System.out.println(Thread.currentThread().getName()+"开始消费"+product.getName());
                    Thread.sleep(200);
                    ProducerAndCustomerTest2.should = true; //切换状态 已经消费完，要开始生产了
                    ProducerAndCustomerTest2.condition.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ProducerAndCustomerTest2.reentrantLock.unlock();
            }
        }
    }
}