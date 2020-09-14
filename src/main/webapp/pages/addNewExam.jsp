<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
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
            text-align: left;
        }
        h{
            color: red;
        }
        .error {
            font-size: small;
            color: red
        }


        /* Change styles for span and cancel button on extra small screens */

    </style>
</head>
<body>
<div>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/">Home</a>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/backTWelcome?id=${id}">Welcome Page</a>
    <label><b>Course Title:${course.title}</b></label><br/>
    <form:form modelAttribute="exam" name="myForm" action="addNewExamProcess?id=${id}&courseNumber=${course.number}"  method="POST">
        <label><b>Exam Title</b></label><br/>
        <form:input path="title" type="text" placeholder="Enter Exam Title"/><form:errors path="title" cssClass="error"/><br/>
        <label><b>Exam explanation</b></label><br/>
        <form:input path="explanation" type="text" placeholder="Enter Exam Explanation"/><form:errors path="explanation" cssClass="error"/><br/>
        <label><b>Exam Starts at:</b></label><br/>
        <form:input path="theBeginning" type="text" placeholder="yyyy/mm/dd"/><form:errors path="theBeginning" cssClass="error"/><br/>
        <label><b>Exam Ends At:</b></label><br/>
        <form:input path="theEnd" name="un" type="text"  placeholder="yyyy/mm/dd"/><form:errors path="theEnd" cssClass="error"/><br/>
        <label><b>timer:</b></label><br/>
        <form:input path="timer" type="text" placeholder="hh:mm:ss"/><form:errors path="timer" cssClass="error"/><br/>
        <p class="error" >${errorMsg}</p><br/>
        <button type="submit">Add</button><br/>
    </form:form>
</div>
</body>
</html>

