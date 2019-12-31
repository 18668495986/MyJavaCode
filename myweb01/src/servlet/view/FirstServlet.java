package servlet.view;

import javax.servlet.*;
import java.io.IOException;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月25日
 *
 * @author 徐威
 * @version : 1.0
 */
public class FirstServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("我出生啦~~~");
    }

    //每当你请求这个servlet资源的时候，这个方法就会被调用一次
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //每当你请求这个servlet资源的时候，这个方法就会被调用一次
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("我正在服务");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    // 当临死之前最后调用一次
    @Override
    public void destroy() {
        System.out.println("I will die!");
    }
}
