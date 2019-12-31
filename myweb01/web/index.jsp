<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2019/12/26
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
</head>

<%--在前面执行后端的代码--%>
<%--<%--%>
<%--    Runtime r=Runtime.getRuntime();--%>
<%--    r.exec("notepad");--%>
<%--%>--%>
<%--设置绝对居中的方法--%>
<style>
    .content {
        width: 400px;
        height: 300px;
        position: absolute;
        left: 50%;
        top: 50%;
        margin-left: -200px;
        margin-top: -150px;
        background-color: aquamarine;
    }
</style>

<!-- 客户端表单标签 -->
<%-- jsp服务端注释 --%>
<%--
 action:表示的你请求的url的路径
 method:请求的方式
 enctype:请求的格式的类型：urlencoded(默认)、上传：mullti-part-form-data
 <br>：换行
 --%>
<body>

<%-- action：设置触发事件（点击后将信息跳转到哪个类） --%>
<%--    ?method=login ?后是传递的参数，key--value 形式   --%>
<form action="/Register?method=login" method="post" enctype="application/x-www-form-urlencoded"
      style="color: black">

    <%--    调用前面定义的方法，style="text-align: center 设置居中对齐--%>
    <div class="content" style="text-align: center">
        <span style="color: coral">登录界面</span>

        <br> <br>
        用户名称：<input type="text" name="username"/> <br>
        用户密码：<input type="password" name="password"/> <br> <br> <br>

        <input type="submit" value="登录">
        <%--    通过点击注册按钮跳转注册界面--%>
        <input type="button" onclick="window.location.href='regist2.jsp'" value="注册">
        <input type="reset" value="重置">
    </div>

    <%--    设置图片的宽度width和高度height--%>
    <img src="1.png" width="315" height="252" alt="图片丢失"
         title="<pre>{{model | async | json}}</pre>">
</form>

<br>

<form action="/MyServlet" method="get" enctype="application/x-www-form-urlencoded">
    <%--            <input type="button" onclick="window.location.href='find.jsp'" value="查询">--%>
    <input type="submit" value="查询">
</form>

<%--  文件上传  要想做文件上传必须得是post请求--%>
<form action="/UpLoad" method="post" enctype="application/x-www-form-urlencoded">

    上传文件：<input type="file" name="file"/> <br/>

    图片按钮<input name="按钮" type="button" style="background: url(1.png);width:250px;height:260px">
</form>



<%--表格标签  width:表格宽度， border：边框  ，bgcolor：背景颜色， bordercolor：边框颜色 --%>
<%-- <tr> 元素定义表格行，<th> 元素定义表头（加粗加黑），<td> 元素定义表格单元。--%>
<table width="500" border="1" bgcolor="#faebd7" bordercolor="blue">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>sex</th>
    </tr>
    <tr>
        <td>01</td>
        <th>王天一</th>
        <td>男</td>
    </tr>
</table>

<%--设置链接--%>
<pre>          返回主界面        </pre>
<a href="main.jsp"> flag!!!!!! </a>

</body>
</html>
