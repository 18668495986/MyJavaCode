<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2019/12/25
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>


<h1>用户登录系统</h1>

<!-- 客户端表单标签 -->
<%-- jsp服务端注释 --%>
<%--
 action:表示的你请求的url的路径
 method:请求的方式
 enctype:请求的格式的类型：urlencoded(默认)、上传：mullti-part-form-data
 <br>：换行
 --%>

<br> <br>
<%-- 表单--%>
<form action="/httpServlet" method="post" enctype="application/x-www-form-urlencoded">
    <%--        text文本输入项--%>
    用户名称：<input type="text" name="username"/><br>
    <%--    密码输入项--%>
    用户密码：<input type="password" name="password"><br> <br> <br>

    <%--        提交按钮--%>
    <input type="submit" value="提交"/>
    <input type="reset" value="重置"/>
    <input type="button" value="登录按钮">

    <%--    单选按钮--%>
    男：<input type="radio" name="sex" value="1">
    女：<input type="radio" name="sex" value="2"> <br>

    <%--    复选框--%>
    爱好：<br>
    吃饭 <input type="checkbox" name="love" value="eat">
    睡觉 <input type="checkbox" name="love" value="sleep">
    打代码 <input type="checkbox" name="love" value="code"> <br> <br>

    <input type="image" src="../1.png"> <br> <br>

    <%--        下拉列表  multiple="true" 定义为多选--%>
    国家
    <%--        多选框--%>
    <select name="country" multiple="true">
        <option value="china">中国</option>
        <option value="japan">小鬼子</option>
        <option value="korea" name="韩国"/>
        <br>
    </select>

    省
        <%--        单选框--%>
    <select name="province">
        <option value="chongqing">重庆</option>
        <option value="hangzhou">杭州</option>
        <option value="beijing">北京</option>
    </select>

    <%--        隐藏框--%>
    <input type="hidden" value="who?" name="隐藏1"/>

    <%--        文件上传--%>
    <input type="file" name="file"/> <br>

</form>

</body>
</html>
