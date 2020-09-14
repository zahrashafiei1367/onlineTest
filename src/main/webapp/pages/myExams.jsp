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
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/backTWelcome?id=${id}">Welcome Page</a>
<table id="demo" class="main">
    <tr>
        <th>Course Title</th>
        <th>Exam Starts At:</th>
        <th>Exam finish At:</th>
        <th>Timer</th>
        <th>Questions</th>
        <th>Add A Question</th>
        <th>Students take part:</th>
        <th>Result:</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="crs" items="${exams}">
        <tr>
            <td>${crs.course.title}</td>
            <td>${crs.theBeginning}</td>
            <td>${crs.theEnd}</td>
            <td>${crs.timer}</td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/examQuestion?id=${id}&examId=${crs.id}">Questions</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addAQuestionChoose?id=${id}&examId=${crs.id}">Add A Question</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/examStudent?id=${id}&examId=${crs.id}">Students</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/examResult?id=${id}&examId=${crs.id}">Results</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/examEdit?id=${id}&examId=${crs.id}">Edit</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/examDelete?id=${id}&examId=${crs.id}">Delete</a></td>

        </tr>
    </c:forEach>
</table>
<br/>
<c:if test="${questions != null}">
    <table>
        <c:forEach var="q" items="${questions}">
            <tr>
                <td>${q.question}</td></tr>
                <c:if test="${q.questionType==test}">
                    <c:forEach var="ans" items="${q.answers}">
                        <tr>
                            <td class="aligner"><ul class="fa-ul"><li><span class="fa-li"><i class="fas fa-square"></i></span></li></ul></td>
                            <td>${ans}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addAQuestionChoose?id=${id}&examId=${examId}">Add A Question</a>
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

