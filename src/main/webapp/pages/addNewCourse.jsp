<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            background-attachment: local;
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

        div {
            text-align: left;
        }

        h {
            color: red;
        }

        .error {
            font-size: small;
            color: red
        }


        /* Change styles for span and cancel button on extra small screens */

    </style>
    <script>
        function myFunction() {
            var ele = document.getElementsByName('rad');
            if(ele.length>0)
                ele[0].click();}
    function setClassfication(){
        var ele = document.getElementsByName('rad');
        for( i=0;i<ele.length;i++){
            if(ele[i].checked){
                document.getElementById("classification").value=ele[i].value;
                return true;
            }

        }
    }

    </script>
</head>
<body onload="myFunction()">
<div>

    <form:form modelAttribute="course" name="myForm" onsubmit="return setClassfication()"  action="addNewCourseProcess?id=${adminId}" method="POST">
            <label><b>Number</b></label><br/>
            <form:input path="number" type="text" placeholder="Enter unique number of course"/><form:errors path="number" cssClass="error"/><br/>
            <label><b>Caption</b></label><br/>
            <form:input path="caption" type="text" placeholder="Enter Your caption"/><form:errors path="caption" cssClass="error"/><br/>
            <label><b>Title</b></label><br/>
            <form:input path="title" type="text" placeholder="Enter title of the course"/><br/>
        <label><b>start class from day:</b></label><br/>
        <form:input path="theBeginning" type="text" placeholder="Enter date of starting:yyyy/mm/dd" id="bt"/><form:errors path="theBeginning" cssClass="error"/><br/>
        <label><b>class will be finished at:</b></label><br/>
        <form:input path="theEnd" type="text" placeholder="Enter date of finishing:yyyy/mm/dd" id="et"/><form:errors path="theEnd" cssClass="error"/><br/>
        <form:hidden path="embeddableClassification" id="classification"/>
        <p class="error" >${errorMsg}</p><br/>
        <lable style="font-size: smaller">Classification: </lable>
        <c:forEach var="cls" items="${classifications}">
            <tr>
                <td>${cls.value}</td>
                <td><input type="radio" name="rad" value="${cls.value}"/></td>
            </tr>
        </c:forEach><br/>
        <button type="submit">AddCourse</button>
        </form:form>

        <br/>
</div>
</body>
</html>

