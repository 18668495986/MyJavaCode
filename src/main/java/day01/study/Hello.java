package day01.study;

import bean.BeanFactory;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月21日
 *
 * @author 徐威
 * @version : 1.0
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("hello World!!");
        User user = BeanFactory.getBean("user");
        System.out.println(user);
        user.getHi();
    }
}
