package servlet.view;

import servlet.data.DataServer;
import servlet.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月25日
 *
 * @author 徐威
 * @version : 1.0
 */
public class MyHttpServlet extends HttpServlet {
    static DataServer server=new DataServer();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get------------");
    }
    /**
     * @param req  请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        System.out.println(user);

        boolean check = false;
        if(username == null || password == null){
            PrintWriter pw = new PrintWriter(resp.getOutputStream());
            pw.println("用户名和密码不能为空!!!!");
            pw.flush();
        }else {
            check = server.check(username, password);
        }

        if(check){
            req.getRequestDispatcher("main.jsp").forward(req,resp);
        }else {
//            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
//        请求转发 只产生一个请求 只能转发给项目内的资源
//        req.getRequestDispatcher("login.jsp").forward(req,resp);

        //重定向 一个方法里不能既有请求转发又有重定向（产生两次请求） 可以跨工程去访问
        resp.getHeader("login.jsp");
    }
}
