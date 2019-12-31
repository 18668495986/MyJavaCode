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
public class LoginServlet extends HttpServlet {
    static DataServer server = new DataServer();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        System.out.println(user);
        boolean add = server.add(user);


        if (add) {
//            printWriter.println("注册成功");
//            printWriter.flush();
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("form.jsp").forward(req, resp);
            PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
            printWriter.println("用户名重复，注册失败。请重新注册！");
            printWriter.flush();
        }
    }
}
