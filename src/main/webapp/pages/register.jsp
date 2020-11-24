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
    <script>
        function myFunction(){
            var x=document.getElementById("psw").value;
            var y=document.getElementById("psw2").value;
            if(x==y){
                if(x.match(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/)){
                    var hash = CryptoJS.MD5(x);
                    document.getElementById("pas").value=hash;
                    return true;

                }
                document.getElementById("password").innerHTML="*password must contain 8 or more characters that are of at least one number, and one uppercase and lowercase letter.";
                return false;
            }
            alert("password and confirm password are not same");
            return false;
        }
    </script>
</head>
<body>
<div>

    <form:form modelAttribute="admin" name="myForm" onsubmit="return myFunction()" action="registerProcess"  method="POST">
        <label><b>Name</b></label><br/>
        <form:input path="name" type="text" placeholder="Enter Your Name"/><form:errors path="name" cssClass="error"/><br/>
        <label><b>Family</b></label><br/>
        <form:input path="family" type="text" placeholder="Enter Your Family"/><form:errors path="family" cssClass="error"/><br/>
        <label><b>School name:</b></label><br/>
        <form:input path="schoolName" type="text" placeholder="Enter Your School Name"/><form:errors path="schoolName" cssClass="error"/><br/>
        <label><b>Username</b></label><br/>
        <form:input path="username" name="un" type="text"  placeholder="Enter Your Email Address"/><form:errors path="username" cssClass="error"/><br/>
        <label><b>Password</b></label><br/>
        <input id="psw" type="password"  placeholder="Enter Password"/><lable id="password" class="error"></lable><br/>
        <label><b>Confirm Password</b></label><br/>
        <input id="psw2" type="password" placeholder="Confirm Password"/><br/>
        <form:hidden path="password" id="pas"></form:hidden>
        <lable style="font-size: smaller">Sign Up as a: Teacher </lable><form:radiobutton path="authority" value="teacher"/>
        <lable style="font-size: smaller">Student</lable> <form:radiobutton path="authority" value="student"/>
        <lable style="font-size: smaller">Admin</lable> <form:radiobutton path="authority" value="admin"/><br/>
        <button type="submit">Sign Up</button><br/>
    </form:form>
</div>
</body>
</html>

