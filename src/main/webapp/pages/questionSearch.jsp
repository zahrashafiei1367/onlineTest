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
    <script>
        function setClassfication(){
            var ele = document.getElementsByName('rad');
            for( i=0;i<ele.length;i++) {
                if (ele[i].checked) {
                    var x=ele[i].value;
                    document.getElementById("a").href = 'http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/addQuestionFromBankProcess?id=${id}&examId=${examId}&qid='+x;
                    return true;
                }

            }

        }
    </script>
</head>
<body>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/">Home</a>
<a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/backTWelcome?id=${id}">Welcome Page</a>
<br/>
<c:if test="${questions != null}">
    <c:set var="count" value="0" scope="page" />
    <c:forEach var="q" items="${questions}">
        <c:set var="count" value="${count + 1}" scope="page"/>

        <label>${count}-${q.key.question}</label><input type="radio" name="rad" value="${q.key.id}"/><br/>
        <c:forEach var="answer" items="${q.value}" >
            <p>
            <ul class="fa-ul"><li><span class="fa-li"><i class="fas fa-square"></i></span></li></ul></p><p style="margin-left: 35px">${answer}</p>
        </c:forEach>
    </c:forEach>
</c:if>
<a href="" id="a" onclick="return setClassfication()">Add</a>
</body>
</html>

