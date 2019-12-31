package customsystem.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月26日
 *
 * @author 徐威
 * @version : 1.0
 */
public class BaseServlet extends HttpServlet {

    //service 方法 每请求浏览器一次，就会被调用一次
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取方法名称
        String methodName = request.getParameter("method");
        if (null == methodName || methodName.trim().isEmpty()) {
            throw new RuntimeException("你没有申明method参数");
        }

        //2. 创建method对象
        Method method = null;
        try {
//            Class<?> registClass = Class.forName("customsystem.web.servlet.RegistServlet");
//            /**
//             * getMethod(方法名称，参数类型)
//             * registClass 该类中的方法有两个参数：HttpServletRequest，HttpServletResponse
//             * 需要将其传进来
//             */
//            method = registClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //子类调用父类方法，此处的this 指代的就是子类对象 RegistServlet
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//            System.out.println(this);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(methodName + "它不存在！！！");
        }

        //3. 调用方法
        String result = null;
        try {
            result = (String) method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException("当前版本只支持public的方法！！！");
        }

        if (null == result) {
            throw new RuntimeException("当前版本只支持返回类型为String");
        }

        int index = result.indexOf(":");

        if (index == -1) throw new RuntimeException("你的格式不正确！！！");

        String prefix = result.substring(0, index);
        String suffix = result.substring(index + 1);

        switch (prefix) { //登录
            case "forward":
                request.getRequestDispatcher(suffix).forward(request, response);
                break;
            case "redirect": //注册
                System.out.println(request.getContextPath());
                response.sendRedirect(request.getContextPath() + suffix);
                break;
            default:
                throw new RuntimeException("你给的格式当前版本不支持!!!");
        }
    }
}
