<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-image: url("/images/background.jpg");
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


        /* Change styles for span and cancel button on extra small screens */

    </style>
    <script>
        function myFunction(){
            var x=document.getElementById("psw").value;
            var y=document.getElementById("psw2").value;
            if(x==y){
                document.getElementById("pas").value=x;
                return true;
            }
            alert("password and confirm password are not same");
            return false;
        }
    </script>
</head>
<body>
<div>

    <form:form modelAttribute="teacher" name="myForm" onsubmit="return myFunction()" action="addNewTeacherProcess?id=${admin.id}"  method="POST">
        <label><b>Name</b></label><br/>
        <form:input path="name" type="text" placeholder="Enter Your Name"/><br/>
        <label><b>Family</b></label><br/>
        <form:input path="family" type="text" placeholder="Enter Your Family"/><br/>
        <label><b>Username</b></label><br/>
        <form:input path="username" name="un" type="text"  placeholder="Enter Your Email Address" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/><br/>
        <label><b>Password</b></label><br/>
        <input id="psw" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="Enter Password"/><br/>
        <label><b>Confirm Password</b></label><br/>
        <input id="psw2" type="password" placeholder="Confirm Password"/><br/>
        <form:hidden path="password" id="pas"></form:hidden>
        <button type="submit">Add</button><br/>
    </form:form>
</div>
</body>
</html>

