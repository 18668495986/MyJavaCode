package day02.study.thread.productandconsumer;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月23日
 *
 * @author 徐威
 * @version : 1.0
 */
public class ProducerAndCustomerTest { //生产者与消费者模式 <synchronized方式>
    static boolean should = true; //true:开始生产
    static String[] name = {"iPhone", "华为", "小米", "三星"}; //产品名称

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

class Producer implements Runnable {
    Product product;

    public Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            synchronized ("") {
                try {
                    if (ProducerAndCustomerTest.should) { //开始生产
                        int i = (int) (Math.random() * 4);
                        product.setName(ProducerAndCustomerTest.name[i]);
                        System.out.println(Thread.currentThread().getName() + "开始生产" + product.getName());
                        ProducerAndCustomerTest.should = false; //切换状态
                        Thread.sleep(200); //进行线程等待
                        "".notifyAll(); //通知来消费
                    } else {
                        "".wait(); // 没有抢到资源的线程进行等待
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Customer implements Runnable {
    Product product;

    public Customer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true){
            synchronized (""){ // 抢到资源的线程
                try {
                    if(ProducerAndCustomerTest.should){ // 生产者正在生产
                        "".wait();
                    }else { //进行消费
                        System.out.println(Thread.currentThread().getName()+"开始消费"+product.getName());
                        Thread.sleep(200);
                        ProducerAndCustomerTest.should = true; //切换状态
                        "".notifyAll(); // 已经消费完，通知开始生产
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}