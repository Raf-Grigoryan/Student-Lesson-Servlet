<%@ page import="org.example.studentlessonservlet.entity.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>STUDENT LESSON</title>
    <link rel="stylesheet" href="css/style.css">

</head>

<body>
<% User user = (User) session.getAttribute("user");%>

<%if (user == null) {%>
<h1><a href="/register">Register</a></h1>
<h1><a href="/login">Login</a></h1>
<%} else { %>
<h1>WELCOME <%=user.getName() + " " + user.getSurname()%>
    <a href="/logout">Logout</a>
</h1>
<%}%>
<h1><a href="/student">Students</a></h1>
<h1><a href="/lesson">Lessons</a></h1>
</body>

</html>