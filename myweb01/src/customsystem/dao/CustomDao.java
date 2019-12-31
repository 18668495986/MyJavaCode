package customsystem.dao;

import customsystem.pojo.Customer;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public interface CustomDao {
    boolean insert(Customer customer);

    boolean login(String admin, String password);

    boolean isNotRepeat(Customer customer);
}
