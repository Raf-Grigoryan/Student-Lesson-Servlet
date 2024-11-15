<%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 14.11.24
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>
<form action="/addLesson" method="post">
    NAME <input type="text" name="name">
    DURATION <input type="datetime-local" name="duration">
    LECTURER NAME <input type="text" name="lName">
    PRICE <input type="number" name="price">
    <input type="submit" value="ADD">
</form>
</body>
</html>
