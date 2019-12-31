<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2019/12/25
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<div style="background: aquamarine;color: beige">   注册   </div> <br>

<form action="/LoginServlet" method="post" enctype="application/x-www-form-urlencoded">
    <%--        text文本输入项--%>
    用户名称：<input type="text" name="username"/><br>
    <%--    密码输入项--%>
    用户密码：<input type="password" name="password"><br> <br> <br>

    <%--        提交按钮--%>
    <input type="submit" value="提交" /> <br>

        <%--设置链接--%>
        <pre>          返回主界面        </pre>
        <a href="index.jsp" type="submit" aria-valuetext="no">  我不注册了！     </a>


</form>

</body>
</html>
