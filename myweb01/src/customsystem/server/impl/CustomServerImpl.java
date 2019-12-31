package customsystem.server.impl;

import beanfactory.staticfactory.Factory;
import customsystem.dao.CustomDao;
import customsystem.pojo.Customer;
import customsystem.server.CustomServer;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public class CustomServerImpl implements CustomServer {
    private CustomDao customDao = Factory.createBean("customDao");

    @Override
    public boolean regist(Customer customer) { //注册
        return  customDao.insert(customer);
    }

    @Override
    public boolean login(String admin, String password) {
        return customDao.login(admin, password);
    }
}
