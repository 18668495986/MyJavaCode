package utils.allUtil;

import org.junit.Test;

import java.io.IOException;
import java.sql.*;
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
public class DBUtil {
    static Properties properties = new Properties();
    static Connection connection = null;

    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;

    static { //初始化，加载配置
        try {
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("dbcp.properties"));
            driverClassName = properties.getProperty("driverClassName");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            //注册驱动 (JDK 1.5版本后 会自动加载驱动，所以可以不写驱动)
            Class.forName(driverClassName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() { //创建连接
        try {
            //方式一：传入(url, username, password)
            if (null ==connection || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
            //方式二： put进去
            /*properties.put("driver", driverClassName);
            properties.put("user", username);
            properties.put("password", password);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day24" +
                    "?useUnicode=true&characterEncoding=utf-8", properties);*/

//            preparedStatement = connection.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void myClose(Statement stat, ResultSet rs) {
        try {
            if (connection != null) connection.close();
            if (stat != null) stat.close();
            if (rs != null) rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void myClose() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void conTest() throws SQLException { //测试连接
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
        myClose();
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from customer");
//        System.out.println(preparedStatement); //测试已确实关流
    }
}
