<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.entity.Lesson" %><%--
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
<%List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<form action="/addStudent" method="post">
    NAME <input type="text" name="name">
    SURNAME <input type="text" name="surname">
    EMAIL <input type="email" name="email">
    AGE <input type="number" name="age">
    <select name="lessonId">
        <%for (Lesson lesson : lessons) {%>
        <option value="<%=lesson.getId()%>"><%=lesson.getName() + " " + lesson.getLecturerName() + " " + lesson.getDuration()%>
        </option>
        <% } %>
    </select>
    <input type="submit" value="ADD">
</form>
</body>
</html>
