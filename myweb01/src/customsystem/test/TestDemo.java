package customsystem.test;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月28日
 *
 * @author 徐威
 * @version : 1.0
 */
public class TestDemo {


    @Test
    public void xx() throws IOException {
        Properties properties = new Properties();
//        properties.load(new FileInputStream("resources/dbcp.properties"));
        properties.load(TestDemo.class.getClassLoader().getResourceAsStream("dbcp" +
                ".properties"));
        String driverClassName = properties.getProperty("customer");
        System.out.println(properties.get("username"));
        Object username = properties.get("username");
        System.out.println(username);
        System.out.println(driverClassName);
    }
}
