package servlet.pojo;

import java.io.Serializable;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月25日
 *
 * @author 徐威
 * @version : 1.0
 */
public class User implements Serializable {
    private String admin;
    private String password;

    public User() {
    }

    public User(String admin, String password) {
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
    public String toString() {
        return "User{" +
                "admin='" + admin + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
