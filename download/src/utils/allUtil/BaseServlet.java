package utils.allUtil;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2020 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年01月02日
 *
 * @author 徐威
 * @version : 1.0
 */
public class BaseServlet extends HttpServlet { // 通过反射执行VoteServlet （帮助跳转页面 对servlet一种封装）
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); //处理post请求
        //处理响应编码
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        String methodName = req.getParameter("method");
        if (methodName == null || methodName.trim().isEmpty()) {
            throw new RuntimeException("您没有申明方法！");
        }
        Method method = null;
        try {
            //通过反射得到方法
            method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
                    HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("此方法不存在！");
        }

        String result = null;

        try {
//            method.setAccessible(true);
            result = (String) method.invoke(this, req, resp);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("不支持此方法");
        }


        if (null == result) {
            throw new RuntimeException("您的方法需要返回一个String类型的数");
        }
//        number:/zvote.jsp
        String[] split = result.split("/");
        switch (split[0]) {
            case "number:":
                req.getRequestDispatcher(split[1]).forward(req, resp);
                break;
            case "over:":
                req.getRequestDispatcher(split[1]).forward(req, resp);
                break;
            case "findSubjects:":
                System.out.println(split[1] + "!");
                resp.sendRedirect("/" + split[1]);
                break;
            case "findOptions:":
                System.out.println(split[1] + "!");
                resp.sendRedirect("/" + split[1]);
                break;


            //文件上传下载
            case "upload:": //普通上传
                System.out.println(split[1] + "!");
                resp.sendRedirect("/" + split[1]);
                break;
            case "googUpload:": //改良后的上传
                System.out.println(split[1] + "!");
                req.getRequestDispatcher(split[1]).forward(req, resp); //请求转发
                break;
            case "listFile:": //文件列表
                System.out.println(split[1] + "!");
                req.getRequestDispatcher(split[1]).forward(req, resp); //请求转发
                break;
            case "downFile:": //下载功能
                System.out.println(split[1] + "!");
                req.getRequestDispatcher(split[1]).forward(req, resp); //请求转发
                break;
            case "downFile2:":
                System.out.println(split[1] + "!");
                req.getRequestDispatcher(split[1]).forward(req, resp); //请求转发
                break;
            default:
                System.out.println("请求参数不正确！！");
        }
    }

    @Test
    public void stringTest() {
        String str = "find/main.jsp";
        String[] split = str.split("/");
        System.out.println(split[0] + "\t" + split[1]);
        int i = str.indexOf("/");
        System.out.println(str.substring(0, i));
        System.out.println(str.substring(i + 1));
    }
}
