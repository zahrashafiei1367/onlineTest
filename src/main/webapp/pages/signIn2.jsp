<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
    <script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
    <script>
        function myFunction() {
            var x = document.getElementById("psw").value;
                var hash = CryptoJS.MD5(x);
                document.getElementById("psw2").value = hash;
                return true;
        }
    </script>
</head>
<body>
<div>
    <b style="color: blue">Login Form</b>

    <form:form modelAttribute="user" name="myForm" onsubmit="myFunction()" action="signInProcess2"  method="POST">
        <label><b>Username</b></label>
        <form:input path="username" name="un" type="text" placeholder="Enter Your Email Address"/><br/>
        <label><b>Password</b></label>
        <input id="psw" type="password" placeholder="Enter Password"/><br/>
        <form:hidden path="password" id="psw2"/>

        Sign In as a: Teacher <form:radiobutton path="authority" value="teacher"/>
        Student <form:radiobutton path="authority" value="student"/><br/>
        <button type="submit">Login</button><br/>
    </form:form>
    <form action="register" method="GET">
        <label style="font-size: smaller">you are not a member?</label><br/>
        <button type="submit">sign up</button><br/>
    </form>
</div>
</body>
</html>

