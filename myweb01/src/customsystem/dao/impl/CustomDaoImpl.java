package customsystem.dao.impl;

import beanfactory.staticfactory.Factory;
import customsystem.dao.CustomDao;
import customsystem.pojo.Customer;
import org.datanucleus.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public class CustomDaoImpl implements CustomDao {
    static List<Customer> clist = new ArrayList<>();
    static ObjectInputStream ois = null;
    static ObjectOutputStream ous = null;

    @Override
    public boolean insert(Customer customer) { //注册
        boolean notRepeat = isNotRepeat(customer);
        if (notRepeat) {
            clist.add(customer);
            out();
            System.out.println(clist);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean login(String admin, String password) {
        //if(!check(admin,password)) return false;
        //校验传入账户密码的合法性
        in();
        if (StringUtils.isEmpty(admin) || StringUtils.isEmpty(password)) return false;
        for (Customer customer : clist) {
            if (customer.getAdmin().equals(admin) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isNotRepeat(Customer customer) { // 检验用户名是否重复
        for (Customer c : clist) {
            if (c.equals(customer)) {
                return false;
            }
        }
        return true;
    }

    public void in() {
        try {
            //ois=new ObjectInputStream(new FileInputStream("D:\\application\\Git\\ideaproject\\git_idea_java\\MyJavaCode\\myweb01\\web\\WEB-INF\\classes\\user2"));

            ois = new ObjectInputStream(Factory.class.getClassLoader().getResourceAsStream("user2"));
            clist = (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void out() {
        try {
            //ous=new ObjectOutputStream(new FileOutputStream("D:\\application\\Git\\ideaproject\\git_idea_java\\MyJavaCode\\myweb01\\web\\WEB-INF\\classes\\user2"));
            //通过类的加载器反射得到所要的文件  File中需要传入URI 所以需要将URL转成URI
            File file = new File(Factory.class.getClassLoader().getResource("user2").toURI());
            ous = new ObjectOutputStream(new FileOutputStream(file));
            ous.writeObject(clist);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


//    //检验输入是否合法
//    public boolean check(String admin,String password){
//        if(admin.equals(null) || password.equals(null)){
//            return false;
//        }else {
//            return true;
//        }
//    }
}
