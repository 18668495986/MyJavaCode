package beanfactory.dynamicproxy;

/**
 * Description：动态代理设计模式<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public class TestProxy {
    public static void main(String[] args) {
        Waiter waiter = new ManWaiter();
        ProxyFactory proxyFactory = new ProxyFactory(waiter,new BeforeAdviceImpl(),new AfterAdviceImpl());
        // 必须得是接口 Waiter 不能是具体的类 ManWaiter 因为此处的得到的是代理（proxy）
        Waiter proxy = (Waiter) proxyFactory.createProxy();
        proxy.server();
    }
}

class BeforeAdviceImpl implements BeforeAdvice{
    @Override
    public void before() {
        System.out.println("hello");
    }
}

class AfterAdviceImpl implements AfterAdvice {
    @Override
    public void after() {
        System.out.println("bye ~~~ ");
    }
}
