<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            width: 250px;

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
<script>
</script>
<div>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/">Home</a>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/backTWelcome?id=${id}">Welcome Page</a>
    <a href="http://localhost:8080/CreatingAndHoldingOnlineTests_war_exploded/myExams?id=${id}">My Exams</a><br/>
    <br/>
    <form:form modelAttribute="question" name="myForm" action="addNewQuestionProcess?id=${teacherId}&examId=${examId}&qt=${qt}"  method="POST">
        <label><b>Title:</b></label><br/>
        <form:input path="title" type="text" placeholder="title should be short"/><form:errors path="title" cssClass="error"/><br/>
        <label><b>Question:</b></label><br/>
        <form:input path="question" type="text" placeholder="Enter question"/><form:errors path="question" cssClass="error"/><br/>
        <label><b>Answer:</b></label><br/>
        <form:input path="correctAnswer" type="text" placeholder="Enter correct answer"/></br>
        <label><b>Classification:</b></label>
        <c:forEach var="cls" items="${classifications}" >
            <tr>
                <td>${cls.value}</td>
                <td><form:radiobutton path="embCl" name="r2" value="${cls.value}" checked="checked"/></td>
            </tr>
        </c:forEach>
        <br/>
        <lable style="font-size: smaller">Do You want to add your Question To The Question Bank: Yes</lable><form:radiobutton path="addToQuestionBank" name="r3" value="true"/>
        <lable style="font-size: smaller">No</lable> <form:radiobutton path="addToQuestionBank" name="r3" value="false"/>
    <br/><form:button type="submit">Add Question</form:button><br/>
    </form:form>

</div>
</body>
</html>

