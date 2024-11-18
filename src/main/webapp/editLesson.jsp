<%@ page import="org.example.studentlessonservlet.entity.Lesson" %>
<%@ page import="org.example.studentlessonservlet.util.DateUtil" %><%--
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
<h1><a href="/WEB-INF/index.jspndex.jsp">MAIN</a></h1>
<h1><a href="/lesson">Lessons</a></h1>
<% Lesson lesson = (Lesson) request.getAttribute("lesson");%>
<form action="/editLesson" method="post">
    <input type="hidden" value="<%=lesson.getId()%>" name="lessonId">
    NAME <input type="text" name="name" value="<%=lesson.getName()%>">
    DURATION <input type="datetime-local" name="duration"
                    value="<%=DateUtil.dateToWebTimeString(lesson.getDuration())%>">
    LECTURER NAME <input type="text" name="lName" value="<%=lesson.getLecturerName()%>">
    PRICE <input type="number" name="price" value="<%=lesson.getPrice()%>">
    <input type="submit" value="EDIT">
</form>
</body>
</html>
