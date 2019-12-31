package beanfactory.decorate;

import beanfactory.staticfactory.Factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public class Student {
    public Student() {
    }

    public void study(){
        System.out.println("play");
        System.out.println("music");
        System.out.println("math");
    }
}

class Test {
    public static void main(String[] args) {
        Student student = Factory.createBean("Student");
        student.study();

        NBStudent nbStudent1 = Factory.createBean("NBStudent");
        nbStudent1.study(student);

        System.out.println("------------------\n");
        try {
            Class<?> clazz = Class.forName("beanfactory.decorate.NBStudent");
            Constructor<?> constructor = clazz.getConstructor(Student.class);
            NBStudent nbStudent = (NBStudent) constructor.newInstance(student);
            nbStudent.study();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}