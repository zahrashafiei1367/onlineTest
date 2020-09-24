<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: KOMEIL
  Date: 8/26/2020
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-image: url("/images/background.jpg");
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


        /* Change styles for span and cancel button on extra small screens */

    </style>
    <script>
        function myFunction() {
            var x = document.getElementById("psw").value;
            var hash = CryptoJS.MD5(x);
            document.getElementById("psw2").value = hash;
            if (document.getElementById("enbl").checked) {
                document.getElementById("enbl").value = true;
            } else
                document.getElementById("enbl").value = false;
            return true;
        }

        function foo() {
            if (document.getElementById("enbl").value = true) {
                document.getElementById("enbl").checked = true;
            } else
                document.getElementById("enbl").checked = false;
        }
    </script>
</head>
<body onload="foo()">
<table id="ttbl">
    <tr>
        <th>username:</th>
        <th>name:</th>
        <th>family:</th>
        <th>password:</th>
        <th>status:</th>
        <th></th>
    </tr>
    <tr>
        <form:form modelAttribute="user" action="editProcess" onsubmit="return myFunction()" method="GET">

            <th><form:input path="username" type="text"/></th>
            <th><form:input path="name" type="text"/></th>
            <th><form:input path="family" type="text"/></th>
            <form:hidden path="password" id="psw2"/>
            <th><input type="text" id="psw"/></th>
            <th><form:checkbox path="enabled" id="enbl"></form:checkbox></th>
            <form:hidden path="id"></form:hidden>
            <th>
                <button type="submit">Edit</button>
            </th>

            <form:hidden path="authority"></form:hidden>
        </form:form>
    </tr>
</table>

</body>
</html>
