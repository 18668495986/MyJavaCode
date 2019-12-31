package customsystem.web.servlet;

import beanfactory.staticfactory.Factory;
import customsystem.dao.impl.Data;
import customsystem.pojo.Customer;
import customsystem.server.CustomServer;
import utils.MyUTF;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */

//登录界面
public class RegistServlet extends BaseServlet {
    CustomServer customServer = Factory.createBean("customServer");
    Customer customer = Factory.createBean("customer");
    boolean isSuccessRe = false; // Data中注册成功后会返回false；
    Data data = Factory.createBean("data");

    //注册
    public String regist(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        //解决从jsp到servlet的乱码问题
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String s = new String(username.getBytes("ISO-8859-1"), "utf-8");
//        String passwd = new String(password.getBytes("ISO-8859-1"), "utf-8");

        String name= MyUTF.getNewString(request.getParameter("username"));
        String passwd=MyUTF.getNewString(request.getParameter("password"));
        customer.setAdmin(name);
        customer.setPassword(passwd);

        //永久化到文件中存储
//        Customer customer = new Customer(username, password);
//        isSuccessRe = customServer.regist(customer);
        isSuccessRe = data.inset(customer);
        if (isSuccessRe) return "redirect:/success.jsp";
        else return "redirect:/error.jsp";
    }

    //登录
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //读取文件的中的数据登录
//        boolean login = customServer.login(username, password);

        //读取数据库中的数据登录
        boolean login = data.login(username, password);
        if (login) {
            return "forward:/main.jsp";
        } else {
//            PrintWriter printWriter = new PrintWriter(response.getOutputStream());
//            printWriter.print("登录失败！");
            return "forward:/error.jsp";
        }
    }

    public void find(HttpServletRequest request, HttpServletResponse response){
        Set<Customer> cSet = data.findAll();
        request.setAttribute("cSet",cSet);
    }
}
