<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.entity.Lesson" %>
<%@ page import="org.example.studentlessonservlet.entity.User" %>
<%@ page import="org.example.studentlessonservlet.entity.UserType" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 14.11.24
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LESSONS</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
    User user = (User) session.getAttribute("user");
%>
<h1><a href="/index.jsp">BACK</a></h1>
<%if (user.getUserType() == UserType.ADMIN) {%>
<h1><a href="/addLesson">ADD LESSON</a></h1>
<%}%>
<table border="1px">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>duration</td>
        <td>Lecturer Name</td>
        <td>price</td>
        <%if (user.getUserType() == UserType.ADMIN) {%>
        <td>delete / edit</td>
        <% }%>

    </tr>
    <% for (Lesson lesson : lessons) {%>
    <tr>
        <td><%=lesson.getId()%>
        </td>
        <td><%=lesson.getName()%>
        </td>
        <td><%=lesson.getDuration()%>
        </td>
        <td><%=lesson.getLecturerName()%>
        </td>
        <td><%=lesson.getPrice()%>
        </td>
        <%if (user.getUserType() == UserType.ADMIN) {%>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>">DELETE</a><a
                href="/editLesson?id=<%=lesson.getId()%>">EDIT</a></td>
        <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
