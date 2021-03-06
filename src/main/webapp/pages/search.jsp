<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            /*background-image: url("/images/background.jpg");*/
            background-image: url("http://myfirstpageza.gigfa.com//background.jpg");
            background-repeat: no-repeat;
            background-clip: border-box;
            background-attachment:local;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }

        form {
            border: none;
            alignment: left;

        }

        input[type=text], input[type=password] {
            width: 250px;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: blue;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            width: 100px;

        }

        button:hover {
            opacity: 0.8;
        }
        div{
            text-align: center;
        }
        h{
            color: red;
        }


        /* Change styles for span and cancel button on extra small screens */

    </style>
</head>
<body>
<div>

    <form:form modelAttribute="user" name="myForm" action="searchProcess?adminId=${admin.id}"  method="POST">

        <label><b>Username</b></label><br/>
        <form:input path="username" name="un" type="text"/><br/>
        <label><b>name</b></label><br/>
        <form:input path="name" name="un" type="text"/><br/>
        <label><b>family</b></label><br/>
        <form:input path="family" name="un" type="text"/><br/>
        Search between: teachers<form:radiobutton path="authority" value="teacher"/>
        Students <form:radiobutton path="authority" value="student"/><br/>
        <button type="submit">search</button><br/>
    </form:form>
    <form:form modelAttribute="admin" >
        <form:hidden path="id" value="${admin.id}"></form:hidden>
    </form:form>
</div>
</body>
</html>

