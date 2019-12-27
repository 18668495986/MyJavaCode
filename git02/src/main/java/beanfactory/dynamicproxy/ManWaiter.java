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
public class ManWaiter implements Waiter{ // 动态代理设计模式
    @Override
    public void server() {
        System.out.println("can I help you?");
    }
}
