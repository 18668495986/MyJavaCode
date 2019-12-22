package homework.bank.bean;

import java.io.Serializable;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月16日
 *
 * @author 徐威
 * @version : 1.0
 */
public class Customer implements Cloneable, Serializable {
    private String admin; //用户名
    private String passwd; //密码
    private double money = 1000; //存款

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Customer() {
    }

    public Customer(String admin, String passwd) {
        this.admin = admin;
        this.passwd = passwd;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Customer(String admin, String passwd, double money) {
        this.admin = admin;
        this.passwd = passwd;
        this.money = money;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "admin='" + admin + '\'' +
                ", passwd='" + passwd + '\'' +
                ", money=" + money +
                '}';
    }
}
