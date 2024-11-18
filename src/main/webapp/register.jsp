<%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 18.11.24
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>REGISTER</h1>
<form action="/register" method="post">
    NAME <input type="text" name="name">
    Surname <input type="text" name="surname">
    Email <input type="text" name="email">
    Password <input type="text" name="password">
    <select name="user_type">
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
    </select>
    <input type="submit" value="REGISTER">
</form>
</body>
</html>
