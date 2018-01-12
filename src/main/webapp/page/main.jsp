<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zhengheming
  Date: 2018/1/9
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main</title>
</head>
<body>

Welcome : <shiro:principal></shiro:principal><br/>
<shiro:hasRole name="list">
    <a href="../page/list.jsp">list</a><br/>
</shiro:hasRole>
<shiro:hasRole name="user">
    <a href="../page/user.jsp">user</a><br/>
</shiro:hasRole>

<a href="../shiro/logout.do">logout</a>


</body>
</html>
