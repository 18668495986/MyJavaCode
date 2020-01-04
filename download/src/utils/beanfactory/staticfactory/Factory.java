package utils.beanfactory.staticfactory;

import utils.allUtil.MyBatisUtils;

import java.util.Properties;

/**
 * Description：静态工厂<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public class Factory {
    //使用配置文件的方式创建
    public static  <T> T createBean(String property) { //将配置文件中要得到的属性名传进来
        T t = null;
        try {
            Properties properties = new Properties();
            //通过流的方式去加载配置文件 FileInputStream只能读取idea工程下的资源
//            properties.load(new FileInputStream("resources/customer.properties"));

            //通过类的加载器getClassLoader 加载发布到Tomcat下文件资源
            properties.load(Factory.class.getClassLoader().getResourceAsStream("customer.properties"));

            String className = properties.getProperty(property);
            // 通过反射去得到这个类
            Class<?> clazz = Class.forName(className);

            // 得到类的对象
            T obj = (T) clazz.newInstance();
            t = obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static  <T> T getBean(Class<T> clazz){
        return MyBatisUtils.getSqlSession().getMapper(clazz);
    }
}

class Dog {
    public void print(){
        System.out.println("I am a dog");
    }
}

class Test {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Dog dog =factory.createBean("dog");
        dog.print();
    }
}