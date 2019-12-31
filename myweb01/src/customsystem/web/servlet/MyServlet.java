package customsystem.web.servlet;

import beanfactory.staticfactory.Factory;
import customsystem.dao.impl.Data;
import customsystem.pojo.Customer;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月27日
 *
 * @author 徐威
 * @version : 1.0
 */
public class MyServlet extends HttpServlet {
    Data data = Factory.createBean("data");
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("customer",new Customer("xl","11111"));
//        req.getRequestDispatcher("find.jsp").forward(req,resp);
        Set<Customer> cSet = data.findAll();
        //设置信息到作用域
        req.setAttribute("cSet",cSet);
        //请求转发
        req.getRequestDispatcher("find.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Customer> cSet = data.findAll();
        req.setAttribute("cSet",cSet);
        req.getRequestDispatcher("find.jsp").forward(req,resp);
    }
}
