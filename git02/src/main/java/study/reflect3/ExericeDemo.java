package study.reflect3;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2020 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年01月01日
 *
 * @author 徐威
 * @version : 1.0
 */         //exercise
public class ExericeDemo {
    /**
     *  带Declared 与不带Declared的区别：
     *  不带Declared 获取公共的
     *  带Declared 可以获取到任意的public、private和proteced，但是不包括父类的申明字段。
     *  获取private 的属性方法需要设置setAccessible(true)
     */
    @Test //三种获取对象的方法
    public void getClassTest() throws IllegalAccessException, InstantiationException,
            ClassNotFoundException {
        //1.类名.class
        Class<Cat> catClass = Cat.class;
        Cat cat = catClass.newInstance();

        //2. new 类.getClass()
        Class<? extends Cat> clazz = new Cat().getClass();
        Cat cat1 = clazz.newInstance();

        //3.Class.forName(类名)
        Class<?> clazz3 = Class.forName("study.reflect3.Cat");
        Cat o = (Cat) clazz3.newInstance();
        System.out.println(cat);
    }

    @Test //获取构造函数
    public void getConstruction() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz3 = Class.forName("study.reflect3.Cat");
        //该方法获得的对象，为空构造得到的对象
        Cat o = (Cat) clazz3.newInstance();
        System.out.println(o); //Cat{name='Tom', id=0}

        //clazz.getConstructors() 获取所有的构造器
        System.out.println(clazz3.getConstructors());

        //clazz.getConstructor 可以获得指定带有参数的构造
        Constructor<?> constructor = clazz3.getConstructor(String.class, int.class);
        Cat cat = (Cat) constructor.newInstance("jik",5);
        System.out.println(cat); // Cat{name='jik', id=5}
    }

    @Test //获取方法
    public void getMethod() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> clazz3 = Class.forName("study.reflect3.Cat");
        Cat cat = (Cat) clazz3.newInstance();
        //1. 获取无参方法
        Method call = clazz3.getMethod("call");
        call.invoke(cat); //miao~~~  执行 需要传入实例

        //1. 获取有参方法
        Method sum = clazz3.getDeclaredMethod("sum", String.class, int.class);
        sum.setAccessible(true);
        Object ok = sum.invoke(cat, "ok", 3);
        System.out.println(ok); //okokok
    }

    @Test // 获取属性
    public void getFiled() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<?> clazz3 = Class.forName("study.reflect3.Cat");
        Cat cat = (Cat) clazz3.newInstance();
        Field fieldName = clazz3.getDeclaredField("name");
        System.out.println(fieldName); //private java.lang.String study.reflect3.Cat.name
        fieldName.setAccessible(true);

        // 得到对象属性
        System.out.println(fieldName.get(cat)); //Tom

        //设置对象属性
        fieldName.set(cat,"jerry");
        System.out.println(fieldName.get(cat)); //jerry
    }
}
