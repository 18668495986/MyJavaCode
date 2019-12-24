package bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月20日
 *
 * @author 徐威
 * @version : 1.0
 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name","wty");
        properties.store(new FileOutputStream("user.properties"),"123");

        User user = BeanFactory2.getBean("user");
        System.out.println(user);
    }
}

class BeanFactory2{
    public static <T> T getBean(String proName){
        T t=null;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/user.properties")); //p.properties
            String className = properties.getProperty(proName);
            Class clazz = Class.forName(className);
            t = (T) clazz.newInstance();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}

class User{
}
