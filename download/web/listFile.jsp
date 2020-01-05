<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2020/1/4
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件列表</title>
</head>
<body>

<h1>恭喜你 ${message}</h1>

<form action="${pageContext.request.contextPath}/down2Load?method=listFile" method="post">
    可下载文件：<input type="submit" value="查询">
    <a href="index.jsp"> 返回 </a>
</form>

<!-- 遍历Map集合 -->
<c:forEach var="me" items="${fileNameMap}">
<%--    value可选择 "/down2Load?method=downFile" （加密） 或者 "/down2Load?method=downFile2"（指定根目录）--%>
    <c:url value="/down2Load?method=downFile" var="downurl">
        <c:param name="filename" value="${me.key}"></c:param>
    </c:url>
    ${me.value}<a href="${downurl}"> &nbsp; 下载</a>
    <br/>
</c:forEach>

</body>
</html>
