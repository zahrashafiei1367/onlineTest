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
            /*background-image: url("/images/background.jpg");*/
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
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/courses?id=${admin.id}&number=0&status=0">Courses</a>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/classifications?id=${admin.id}">Classifications</a>
<table id="demo" class="main">
    <tr>
        <th>Username</th>
        <th>name</th>
        <th>family</th>
        <th>authority</th>
        <th>status</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:forEach var="std" items="${students}">
        <tr>
            <td>${std.username}</td>
            <td>${std.name}</td>
            <td>${std.family}</td>
            <td>student</td>
            <td class="aligner">

                <ul class="fa-ul">
                    <li><span class="fa-li">
                            <c:if test="${std.enabled == true}"><i class="fas fa-check-square"></i></c:if>
                            <c:if test="${std.enabled == false}"><i class="fas fa-square"></i></c:if>
                        </span></li>
                </ul>
            </td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/edit?id=${std.id}"
                   style="color: blue">edit</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/deleteByUsername?id=${std.id}"
                   style="color: blue">delete</a></td>

        </tr>
    </c:forEach>
    <c:forEach var="std" items="${teachers}">
        <tr>
            <td>${std.username}</td>
            <td>${std.name}</td>
            <td>${std.family}</td>
            <td>teacher</td>
            <td class="aligner">

                <ul class="fa-ul">
                    <li><span class="fa-li">
                            <c:if test="${std.enabled == true}"><i class="fas fa-check-square"></i></c:if>
                            <c:if test="${std.enabled == false}"><i class="fas fa-square"></i></c:if>
                        </span></li>
                </ul>

            </td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/edit?id=${std.id}"
                   style="color: blue">edit</a></td>
            <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/deleteByUsername?id=${std.id}"
                   style="color: blue">delete</a></td>
        </tr>
    </c:forEach>
</table>

<br/>
<table>
    <tr class="m">
        <td class="m">
            <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addNewStudent?id=${admin.id}">Add
                New Student</a>
        </td>
        <td class="m">
            <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addNewTeacher?id=${admin.id}">Add
                New teacher</a>
        </td>
        <td class="m">
            <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/search?id=${admin.id}">Search</a>
        </td>
    </tr>
</table>
</body>
</html>

