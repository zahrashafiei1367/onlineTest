<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-image: url("http://myfirstpageza.gigfa.com//background.jpg");
            /*background-image: url("images/background.jpg");*/
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

        input[type=text] {
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
            width: 250px;

        }

        .button {
            background-color: blue;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            width: 250px;

        }

        .button:hover {
            opacity: 0.8;
        }

        button:hover {
            opacity: 0.8;
        }
        div {
            text-align: left;
        }

        /* Change styles for span and cancel button on extra small screens */

    </style>
</head>
<body>
<script>
    function myFunction1(){
        x=document.getElementById('a').value;
        y=document.getElementById('b');
        y.href='http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addAnswer?id=${id}&examId=${examId}&qid=${qid}&ans='+x+'&end=n';
    }
    function myFunction2(){
        x=document.getElementById('a').value;
        y=document.getElementById('c');
        y.href='http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addAnswer?id=${id}&examId=${examId}&qid=${qid}&ans='+x+'&end=y';
    }
</script>
<div>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/">Home</a>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/backTWelcome?id=${id}">Welcome Page</a>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/myExams?id=${id}">My Exams</a><br/>
    <br/>

        <label>${question.question}</label>
        <c:forEach var="answer" items="${question.answers}" >
            <p>
            <ul class="fa-ul"><li><span class="fa-li"><i class="fas fa-square"></i></span></li></ul></p><p style="margin-left: 35px">${answer}</p>
        </c:forEach>
        <br/>
        <input type="text" id="a"/>
        <a href="" id="b"onclick="myFunction1()" class="button">add answer</a>
    <a href="" id="c"onclick="myFunction2()" class="button">finish</a></br>
</div>
</body>
</html>
