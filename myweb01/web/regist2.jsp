<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2019/12/26
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
</head>
<body>
<form action="/Register?method=regist" method="post" enctype="application/x-www-form-urlencoded"
      style="color: black">
    用户名称：<input type="text" name="username"/> <br>
    用户密码：<input type="password" name="password"/> <br>
    <input type="submit" value="提交">

    <input type="button" onclick="window.location.href='index.jsp'" value="返回">
</form>
</body>
</html>
