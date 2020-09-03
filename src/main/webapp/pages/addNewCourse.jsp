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
            text-align: center;
        }

        h {
            color: red;
        }


        /* Change styles for span and cancel button on extra small screens */

    </style>
    <script>
        function myFunction() {
            var ele = document.getElementsByName('rad');
            var i;
            for (i = 0; i < ele.length; i++) {
                if (ele[i].checked()) {
                    document.getElementById("cls").value = ele[i].value();
                    return true;
                }
            }
            var cls = prompt("Please enter your new Classification:", "java");
            if (cls == null || cls == "") {
                alert("you should choose an existing classification or enter a new one");
                return false;
            } else {
                document.getElementById("cls").value = cls;
                return true;
            }
        }

    </script>
</head>
<body>
<div>

    <form:form modelAttribute="course" name="myForm" onsubmit="return myFunction()" action="addNewCourseProcess"
               method="POST">
        <label><b>Number</b></label>
        <form:input path="number" type="number" pattern="[0-9]" placeholder="Enter unique number of course"/><br/>
        <label><b>Caption</b></label>
        <form:input path="caption" name="un" type="text" placeholder="Enter title of the course"
                    pattern=".{,20}$"/><br/>
        <label><b>Classifications:</b></label>
        <c:forEach var="cls" items="${classifications}">
            <tr>
                <td>${cls.value}</td>
                <td><input type="radio" name="rad" value="${cls.value}"/></td>
            </tr>
        </c:forEach><br/>
        <form:hidden path="embeddableClassification" id="cls"/>
        <button type="submit">AddCourse</button>
        <br/>
    </form:form>
</div>
</body>
</html>

