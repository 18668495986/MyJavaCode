package beanfactory.dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description：动态代理设计模式<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public class ProxyFactory { // 动态代理设计模式
    private Object target; // 目标对象
    private BeforeAdvice beforeAdvice; //前置增强
    private AfterAdvice afterAdvice;  // 后置增强


    public Object createProxy() {
        /**
         * newProxyInstance（目标对象，目标对象的接口，InvocationHandler）
         * InvocationHandler：调用处理器 需要使用匿名内部类的方式去写（底层自己调用）
         * 返回的Object 相当于接口 可以代理所有的子类
         */
        Object object = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { //invoke执行
                        if (beforeAdvice != null) beforeAdvice.before();
                        Object invoke = method.invoke(target, args);
                        if (afterAdvice != null) afterAdvice.after();
                        return invoke;
                    }
                });
        return object;
    }

    // 构造方法和get/set方法
    public ProxyFactory() {
    }

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public ProxyFactory(Object target, BeforeAdvice beforeAdvice, AfterAdvice afterAdvice) {
        this.target = target;
        this.beforeAdvice = beforeAdvice;
        this.afterAdvice = afterAdvice;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public BeforeAdvice getBeforeAdvice() {
        return beforeAdvice;
    }

    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    public AfterAdvice getAfterAdvice() {
        return afterAdvice;
    }

    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }
}

interface BeforeAdvice {
    void before();
}

interface AfterAdvice {
    void after();
}