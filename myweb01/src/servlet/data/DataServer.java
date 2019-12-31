package servlet.data;

import servlet.pojo.User;

import java.io.*;
import java.util.ArrayList;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月25日
 *
 * @author 徐威
 * @version : 1.0
 */
public class DataServer {
    static ArrayList<User> ulist = new ArrayList();
    static ObjectInputStream objectInputStream = null;
    static ObjectOutputStream objectOutputStream = null;


    public boolean check(String admin, String password) { //校验登录
        in();
//        User user1 = new User("lx","123");
//        ulist.add(user1);

        for (User user : ulist) {
            if (user.getAdmin().equals(admin) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(User user) {
        for (User u : ulist) {
            if (u.getAdmin().equals(user.getAdmin()) || user.getPassword().equals(null) || user.getAdmin().equals(null)) {
                return false;
            }
        }
        ulist.add(user); //成功添加用户，写入文件
        out();
        System.out.println(ulist);
        return true;
    }


    // -----------------流式操作-----------------
    public void in() { //读取的方法
        try {
            //此处需要写绝对路径
            objectInputStream = new ObjectInputStream(new FileInputStream("D:\\application\\Git\\ideaproject\\git_idea_java\\MyJavaCode\\myweb01\\resources\\user"));
            ulist = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void out() { //写入文件的方法
        try {
            //此处需要写绝对路径
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\application\\Git\\ideaproject\\git_idea_java\\MyJavaCode\\myweb01\\resources\\user"));
            objectOutputStream.writeObject(ulist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() { // 关流的方法
        try {
            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
