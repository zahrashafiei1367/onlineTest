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
            background-image: url("/images/background.jpg");
            background-repeat: no-repeat;
            background-clip: border-box;
            background-attachment: local;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            position: relative;
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

        td.aligner {
            display: flex;
            align-items: center;
        }


        /* Change styles for span and cancel button on extra small screens */

    </style>
</head>
<body>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/">Home</a>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/myExams?id=${id}">My Exams</a>
<table id="demo" class="main">
    <tr>
        <th>Course Title</th>
        <th>Exams</th>
        <th>Students</th>
        <th>Teachers</th>
        <th>Add New Exam</th>
    </tr>
    <c:forEach var="crs" items="${courses}">
        <tr>
            <td>${crs.title}</td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/exams?id=${id}&courseId=${crs.number}">Exams</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/students?id=${id}&courseId=${crs.number}">Students</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/teachers?id=${id}&courseId=${crs.number}">Teachers</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addANewExam?id=${id}&courseId=${crs.number}">Add New Exam</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
    <c:if test="${exams != null}">
    <table>
        <tr>
            <th>teacher name</th>
            <th>timer</th>
            <th>the date of starting</th>
            <th>date of end</th>
        </tr>
        <c:forEach var="exam" items="${exams}">
            <tr>
                <td>${exam.teacher.name}</td>
                <td>${exam.timer}</td>
                <td>${exam.theBeginning}</td>
                <td>${exam.theEnd}</td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
    <c:if test="${students != null}">
    <table>
        <tr>
            <th>Student name</th>
            <th>Student family</th>
            <th>Student email</th>
        </tr>
        <c:forEach var="exam" items="${students}">
            <tr>
                <td>${exam.name}</td>
                <td>${exam.family}</td>
                <td>${exam.username}</td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
    <c:if test="${teachers} != null}">
    <table>
        <tr>
            <th>teacher name</th>
            <th>teacher family</th>
            <th>teacher email</th>
        </tr>
        <c:forEach var="exam" items="${teachers}">
            <tr>
                <td>${exam.name}</td>
                <td>${exam.family}</td>
                <td>${exam.username}</td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</body>
</html>

