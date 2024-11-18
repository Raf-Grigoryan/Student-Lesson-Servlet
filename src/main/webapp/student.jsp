<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.entity.Student" %>
<%@ page import="org.example.studentlessonservlet.entity.User" %>
<%@ page import="org.example.studentlessonservlet.entity.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
</head>
<body>
<h1><a href="/index.jsp">BACK</a></h1>
<h1><a href="/addStudent">ADD</a></h1>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    User user = (User) session.getAttribute("user");
%>
<table border="1px">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>Lesson Name</th>
        <th>Lesson Lecturer Name</th>
        <th>Lesson Duration Time</th>
        <%if (user.getUserType() == UserType.ADMIN) {%>
        <th>Delete / EDIT</th>
        <%}%>

    </tr>
    <% for (Student student : students) { %>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSurname()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <td><%=student.getLesson().getName()%>
        </td>
        <td><%=student.getLesson().getLecturerName()%>
        </td>
        <td><%=student.getLesson().getDuration()%>
        </td>
        <%if (user.getUserType() == UserType.ADMIN) {%>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">DELETE</a> // <a
                href="/editStudent?id=<%=student.getId()%>">EDIT</a></td>
        <%}%>

    </tr>
    <%}%>
</table>

</body>
</html>
