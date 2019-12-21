package bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月21日
 *
 * @author 徐威
 * @version : 1.0
 */
public class BeanFactory {
    public static <T> T getBean(String propertiesName) {
        T t = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("user.properties"));
            String className = properties.getProperty(propertiesName);
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
