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
    <title>login</title>
</head>
<body>


<form action="/jmper/shiro/login.do" method="post">
    userNme: <input name="userName"> <br/><br/>
    password: <input type="password" name="password"> <br/><br/>
    <input type="submit" value="submit">
</form>
</body>
</html>
