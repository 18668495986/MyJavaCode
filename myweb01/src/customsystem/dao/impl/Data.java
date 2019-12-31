package customsystem.dao.impl;

import beanfactory.staticfactory.Factory;
import customsystem.pojo.Customer;
import org.junit.Test;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月28日
 *
 * @author 徐威
 * @version : 1.0
 */
public class Data {
    Connection connection = DBUtil.getConnection();
    static Set<Customer> cSet = new HashSet<Customer>(); //达到去重的目的
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public boolean inset(Customer customer) { //注册操作
        String sql = "insert into customer (admin,passwd) values (?,?)";
        boolean notRepeat = isNotRepeat(customer);
        try {
            if (notRepeat) {
                statement = connection.prepareStatement(sql);
                statement.setObject(1, customer.getAdmin());
                statement.setObject(2, customer.getPassword());
                statement.execute();
                System.out.println("插入一条记录");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            DBUtil.myClose();
        }
        return true; //表示插入成功 返回true
    }

    public boolean login(String username, String password) { //登录
        String sql = "select * from customer where admin = ?";
        String passwd = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setObject(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passwd = resultSet.getString("passwd");//数据库查询出的用户密码
                System.out.println(passwd);
            }
            if (password.equals(passwd)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            DBUtil.myClose(statement, resultSet); 不能关流 不然会报：No operations allowed after
//            connection
//            closed
        }
        return false;
    }

    public Set<Customer> findAll() { //查询所有用户
        String sql = "select * from customer";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String admin = resultSet.getString("admin");
                String passwd = resultSet.getString("passwd");
                Customer customer = new Customer(admin, passwd);
                cSet.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            DBUtil.myClose();
        }
        return cSet;
    }


    public boolean isNotRepeat(Customer customer) { // 检验用户名是否重复
        for (Customer c : cSet) {
            if (c.equals(customer)) {
                return false;
            }
        }
        return true; //表示无重复
    }

    @Test
    public void insertTest() {
        Customer customer = new Customer("吕静美", "111");
        boolean inset = inset(customer);
        System.out.println(inset); //插入成功返回true
    }

    @Test
    public void findAllTest(){
        Set<Customer> all = findAll();
        System.out.println(all);
    }
}
