<%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 18.11.24
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
<%
    String msg = (String) session.getAttribute("msg");
    if (msg != null) {
%>
<h3 style="color:red"><%=msg%>
</h3>
<%
        session.invalidate();
    }
%>
<h3></h3>
<form action="/login" method="post">
    email <input type="text" name="email">
    password <input type="text" name="password">
    <input type="submit" value="LOGIN">
</form>
<br>
<a href="/register">REGISTER</a>
</body>
</html>
