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
            text-align:left;
            padding: 14px 0px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            min-width: fit-content;
            border-radius: 12px;
        }

        button {
            background-color: blue;
            color: white;
            padding: 10px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 70px;
            border-radius: 12px;
        }

        button:hover {
            opacity: 0.8;
        }

        table.main, td, tr, th {
            border: 1px solid black;
        }

        .error {
            font-size: small;
            color: red
        }



        /* Change styles for span and cancel button on extra small screens */

    </style>
</head>
<body>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/">Home</a>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/welcome?id=${adminId}">Welcome Page</a>
<table id="demo" class="main">
    <tr>
        <th>Classification</th>
        <th>Edit</th>
    </tr>
    <c:forEach var="cls" items="${classifications}">
        <tr>
            <td>${cls.value}</td>
            <td>
                <a href='' onclick="this.href='http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/editClassificationsProcess?id=${adminId}&number=${cls.id}&value='+document.getElementById('value').value" style="color: blue" >Edit</a>
            </td>
        </tr>
    </c:forEach>
    <div>
   <form:form modelAttribute="classification" action="addClassificationsProcess?id=${adminId}"  method="POST">
       <form:input path="value" id="value" style="width : 100px"/>
       <button type="submit">Submit</button><form:errors path="value" cssClass="error"/><br/>
   </form:form>
    </div>
</table>
</body>
</html>

