package customsystem.test;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月27日
 *
 * @author 徐威
 * @version : 1.0
 */
public class Demo {


    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public Demo() {
    }

//    public Demo(String sql) {
//        try {
//            Class.forName(driverClassName);
//            connection = DriverManager.getConnection(url, username, password);
//            preparedStatement = connection.prepareStatement(sql);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void close() {
        try {
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sql = "select * from customer";
//        Demo demo = Factory.createBean("demo");
        try {
            Class<?> clazz = Class.forName("customsystem.test.Demo");
            Constructor<?> constructor = clazz.getConstructor(String.class);
            Demo demo = (Demo) constructor.newInstance(sql);
            System.out.println(demo);
            ResultSet resultSet = demo.preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String admin = resultSet.getString(2);
                String passwd = resultSet.getString("passwd");
                System.out.println("id=" + id + " admin=" + admin + " passwd=" + passwd);
            }
            demo.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
