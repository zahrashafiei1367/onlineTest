<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: KOMEIL
  Date: 8/26/2020
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function myFunction() {
            var x=document.getElementById("username").value;
            ${x}=x;
        }
    </script>
</head>
<body>
<form modelAttribute="oldUser">
    <table>
        <tr>
            <th>username:</th>
        </tr>
        <th>
            <input id="username" type="text"/>
        </th>
    </table>

</form>
<form:form modelAttribute="user" onsubmit="myFunction()" action="editProcess?${x}" method="GET">
    <table id="ttbl">
        <tr>
            <th>username:</th><th>name:</th><th>family:</th><th>authority:</th><th>enabled:</th><th>password:</th>
        </tr>
        <th><form:input path="username" type="text"/></th>
        <th><form:input path="name" type="text" placeholder="New Username"/></th>
        <th><form:input path="family" type="text" placeholder="New Family"/></th>
        <th><form:input path="authority" type="text" placeholder="New Authority"/></th>
        <th><form:input path="enabled" type="text" placeholder="enabled"/></th>
        <th><form:input path="password" type="text" placeholder="New Password"/></th>
    </table>
    <button type="submit">Edit</button>

</form:form>

</body>
</html>
