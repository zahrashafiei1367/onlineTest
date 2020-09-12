<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <style>
        body {
            alignment: left;
            font-family: Arial, Helvetica, sans-serif;
            background-image: url("http://myfirstpageza.gigfa.com//background.jpg");
            background-repeat: no-repeat;
            background-clip: border-box;
            background-attachment: local;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            position: relative;
        }


        div {
            position: absolute;
            top: 90%;
            width: 100%;
            text-align: center;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            min-width: fit-content;
            border-radius: 12px;
        }

        button {
            background-color: blue;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 200px;
            border-radius: 12px;
        }

        button:hover {
            opacity: 0.8;
        }

        table.main, td, tr, th {
            border: 1px solid black;
        }

        td.m, tr.m {
            border: hidden;
        }



        /* Change styles for span and cancel button on extra small screens */

    </style>
</head>
<body>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/">Home</a>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/welcome?id=${adminId}">Welcome Page</a>
<table id="demo" class="main">
    <tr>
        <th>Title</th>
        <th>Number</th>
        <th>Caption</th>
        <th>Classification</th>
        <th>Students</th>
        <th>Teachers</th>
        <th>Exams</th>
    </tr>
    <c:forEach var="course" items="${courses}">
        <tr>
            <td>${course.title}</td>
            <td>${course.number}</td>
            <td>${course.caption}</td>
            <td>${course.classification.value}</td>
            <td>
                <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/courses?id=${adminId}&number=${course.number}&status=1" style="color: blue" >Students</a>
            </td>
            <td>
                <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/courses?id=${adminId}&number=${course.number}&status=2" style="color: blue" >Teachers</a>
            </td>
            <td>
                <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/courses?id=${adminId}&number=${course.number}&status=3" style="color: blue" >Exams</a>
            </td>
            <td>
                <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addStudentOrTeacherToCourse?id=${adminId}&number=${course.number}" style="color: blue" >Add or remove people</a>
            </td>
        </tr>
    </c:forEach>

</table>
<c:if test="${students != null}&${students.size() != 0}">
    <table>
        <tr>
            <th>Student Name</th>
            <th>Student Family</th>
            <th>Student Username</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.name}</td>
                <td>${student.family}</td>
                <td>${student.username}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br/>
<c:if test="${teachers != null}&${teachers.size() != 0}">
    <table>
        <tr>
            <th>Teacher Name</th>
            <th>Teacher Family</th>
            <th>Teacher Username</th>
        </tr>
        <c:forEach var="student" items="${teachers}">
            <tr>
                <td>${student.name}</td>
                <td>${student.family}</td>
                <td>${student.username}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br/>
<c:if test="${exams != null}&${exams.size() != 0}">
    <table>
        <tr>
            <th>Timer</th>
            <th>Teacher Family</th>
            <th>Teacher Username</th>
            <th>Start from:</th>
            <th>End at:</th>
        </tr>
        <c:forEach var="exam" items="${exams}">
            <tr>
                <td>${exam.timer}</td>
                <td>${exam.teacher.family}</td>
                <td>${exam.teacher.username}</td>
                <td>${exam.theBeginning}</td>
                <td>${exam.theEnd}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br/>
<table>
    <tr class="m">
        <td class="m">
            <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addNewCourse?id=${adminId}">Add New Course</a>
        </td>
    </tr>
</table>
</div>
</body>
</html>

