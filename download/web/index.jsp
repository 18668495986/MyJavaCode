<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2020/1/4
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>

测试：
<form action="${pageContext.request.contextPath}/down2Load?method=upload"
<%--      enctype的类型将会影响后端取值--%>
      enctype="application/x-www-form-urlencoded"
      method="post">
    上传用户：<input type="text" name="username" value="123"><br/>
    <input type="hidden" name="nihao" value="need" >
    <input type="submit" value="提交">
</form>


无限制上传：
<form action="${pageContext.request.contextPath}/down2Load?method=upload"
      enctype="multipart/form-data"
      method="post">
    上传用户：<input type="text" name="username"><br/>
    上传文件1：<input type="file" name="file1"><br/>
    上传文件2：<input type="file" name="file2"><br/>
    <input type="submit" value="提交">
</form>
${message} <br>

改良上传：
<form action="${pageContext.request.contextPath}/down2Load?method=googUpload"
      enctype="multipart/form-data"
      method="post">
    上传用户：<input type="text" name="username" value="123"><br/>
    上传文件1：<input type="file" name="file1"><br/>
    上传文件2：<input type="file" name="file2"><br/>
    <input type="hidden" name="nihao" value="need" >
    <input type="submit" value="提交" >
    <input type="button" onclick="window.location.href='listFile.jsp'" value="查询">
</form>
</body>
</html>
