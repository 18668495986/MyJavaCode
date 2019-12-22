package homework.bank.service;

import homework.bank.bean.Customer;
import org.datanucleus.util.StringUtils;

import java.io.*;
import java.util.ArrayList;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月16日
 *
 * @author 徐威
 * @version : 1.0
 */
public class CustomerServer {

    static ArrayList<Customer> clist = new ArrayList<Customer>();

    static ObjectInputStream inputStream;
    static ObjectOutputStream outputStream;
//    static {
//        //初始化一个账户  若文件中没有用户，就会报EOFE/读取不到文件的错误
//        Customer customer = new Customer("lx", "123", 2000);
//        clist.add(customer);  //初始化后必须注销，不然下次运行，上次写入的会被覆盖
//        try {
//            out();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public boolean isNotRepeat(String name) { //判断重复
        if (StringUtils.isEmpty(name)) {
            System.out.println("用户名不能为null");
            return false;
        }
        for (int i = 0; i < clist.size(); i++) {
            if (clist.get(i).getAdmin().equals(name)) { //说明存在相同用户
                return false;
            }
        }
        return true;
    }

    public void list() {
        for (Customer customer : clist) {
            System.out.println(customer);
        }
    }

    public boolean regist(Customer customer) throws IOException, ClassNotFoundException { //确保用户名不为null且不重复 注册
        boolean repeat = isNotRepeat(customer.getAdmin());
        if (customer.getAdmin() != null && repeat) {
            clist.add(customer);
            for (int i = 0; i <clist.size(); i++) {
                System.out.print(clist.get(i) + "\t");
                if (i % 3 == 0) { //每行打印三个
                    System.out.println();
                }
            }
            return true;
        }
        return false;
    }

    public Customer login(String admin, String passwd) { //登录
        for (int i = 0; i <clist.size(); i++) {
            if (clist.get(i).getAdmin().equals(admin) && clist.get(i).getPasswd().equals(passwd)) {
                return clist.get(i);
            }
        }
        return null;
    }

    public boolean outCash(Customer customer, int outCash) { //取钱
        if (customer.getMoney() > outCash && outCash > 0) {
            customer.setMoney(customer.getMoney() - outCash);
            return true;
        } else {
            return false;
        }
    }

    public boolean intoCash(Customer customer, int intoCash) { //存钱
        if (intoCash > 0) {
            customer.setMoney(customer.getMoney() + intoCash);
            return true;
        } else {
            return false;
        }
    }

    public static void out() throws IOException {
        outputStream = new ObjectOutputStream(new FileOutputStream(
                "resources" +
                        "/user.txt"));
            outputStream.writeObject(clist);
    }

    public static void in() throws IOException, ClassNotFoundException {
        inputStream = new ObjectInputStream(new FileInputStream("resources/user.txt"));
        clist = (ArrayList<Customer>) inputStream.readObject();
    }

    public void close() throws IOException { //关闭流
            inputStream.close();
            outputStream.close();
    }
}
