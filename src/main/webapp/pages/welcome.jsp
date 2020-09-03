<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
    <script>
        function myFunction(user) {

            if (user.enabled = true) {
                user.enabled = false;
            } else
                user.enabled = true;
        }

    </script>
</head>
<body>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/">Home</a>

<div>
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
                <td>< style="color: blue">${std.enabled}</td>
                <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/editStudent/${std.id}%${admin.id}" style="color: blue">edit</a></td>
                <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/deleteByUsername/${std.id}%${admin.id}" style="color: blue">delete</a></td>

            </tr>
        </c:forEach>
        <c:forEach var="std" items="${teachers}">
            <tr>
                <td>${std.username}</td>
                <td>${std.name}</td>
                <td>${std.family}</td>
                <td>teacher</td>
                <td>< style="color: blue">${std.enabled}</td>
                <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/editStudent/${std.id}%${admin.id}" style="color: blue">edit</a></td>
                <td><a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/deleteByUsername/${std.id}%${admin.id}" style="color: blue">delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <table>
        <tr class="m">
            <td class="m">
                <form action="editByUsername">
                    <button type="submit">Edit By Username</button>
                </form>
            </td>
            <td class="m">
                <form action="addNewStudent">
                    <button type="submit">Add New Student</button>
                </form>
            </td>
            <td class="m">
                <form action="addNewTeacher">
                    <button type="submit">Add New teacher</button>
                </form>
            </td>
            <td class="m">
                <form action="deleteByUsername">
                    <button type="submit">Delete By Username</button>
                </form>
            </td>
            <td class="m">
                <form action="search">
                    <button type="submit">Search</button>
                </form>
            </td>
            <td class="m">
                <form action="addNewCourse">
                    <button type="submit">Course</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

