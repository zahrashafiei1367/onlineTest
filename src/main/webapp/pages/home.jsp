<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            alignment: left;
            font-family: Arial, Helvetica, sans-serif;
            background-image: url("/images/background.jpg");
            background-repeat: no-repeat;
            background-clip: border-box;
            background-attachment:local;
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
            width: 90px;
            border-radius: 12px;
        }

        button:hover {
            opacity: 0.8;
        }
        div{
            position: absolute;
            top: 90%;
            width: 100%;
            text-align: center;
        }
        form{

        }

        .submitAdmin {
            background-color:palevioletred;
            color: rgb(230, 16, 62);
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            text-align: center;
            min-width: fit-content;
            border-radius: 12px;
            width: 25%;
        }
        .submitAdmin:hover {
            opacity: 0.8;
        }



        /* Change styles for span and cancel button on extra small screens */

    </style>
</head>
<body>
<table>
    <tr>
        <td>
<form action="signIn2">
<button type="submit">Login</button>
</form>
        </td>
        <td>
<form action="register">
<button type="submit">Sign Up</button>
</form>
        </td>
    </tr>
</table>
<div>
<form action="signIn">
    <label class="lableAdmin" style="text-decoration-style: solid;color: blue;">It is a site for creating and holding online tests .It is suitable and easy to use</label><br/>
    <input class="submitAdmin" type="submit" value="univercity and school managers click here">
</form>
</div>
</body>
</html>

