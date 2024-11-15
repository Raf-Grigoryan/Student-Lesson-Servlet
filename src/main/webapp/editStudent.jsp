
<%@ page import="org.example.studentlessonservlet.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 15.11.24
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD Student</title>
</head>
<body>
<%Student student = (Student) request.getAttribute("student");%>
<form action="/editStudent" method="post">
    <input type="hidden" value="<%=student.getId()%>" name="id">
    NAME <input type="text" name="name" value="<%=student.getName()%>">
    SURNAME <input type="text" name="surname" value="<%=student.getSurname()%>">
    EMAIL <input type="email" name="email" value="<%=student.getEmail()%>">
    AGE <input type="number" name="age" value="<%=student.getAge()%>">
    <input type="submit" value="ADD">
</form>
</body>
</html>
