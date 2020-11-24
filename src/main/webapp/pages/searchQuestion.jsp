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

    <form:form modelAttribute="question" name="myForm" action="searchQuestionProcess?id=${id}&examId=${examId}"  method="POST">
        <label><b>Question</b></label><br/>
        <form:input path="question" name="un" type="text"/><br/>
        <label><b>title</b></label><br/>
        <form:input path="title" name="un" type="text"/><br/>
        <label><b>classification</b></label><br/>
        <form:input path="embCl" name="un" type="text"/><br/>
        <button type="submit">search</button><br/>
    </form:form>
</div>
</body>
</html>

