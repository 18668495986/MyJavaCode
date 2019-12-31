<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2019/12/26
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
</head>
<body>
<div style="background: chartreuse;color: black">   欢迎光临   </div> <br>

<p>
    <fmt:formatDate value='${us.createtime}' pattern='yyyy-MM-dd hh:mm'/>
    Today's date <%= (new java.util.Date()).toLocaleString()%><br>
</p>

<%--el表达式获取到值  会调用pojo里面的get方法--%>
${customer}
${customer.username}

</body>
</html>
