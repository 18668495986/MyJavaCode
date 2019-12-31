package customsystem.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public class Customer implements Serializable {
    private String admin;
    private String password;

    public Customer() {
    }

    public Customer(String admin, String password) {
        this.admin = admin;
        this.password = password;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object obj) { //重写比较方法，如果属性相同 返回true
        Customer customer= (Customer) obj;
        //用户名相同即可认为是同一个人
        return this.admin.equals(customer.admin);

    }

    @Override
    public String toString() {
        return "Customer{" +
                "admin='" + admin + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
