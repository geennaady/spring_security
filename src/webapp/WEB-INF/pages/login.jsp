<%--
  Created by IntelliJ IDEA.
  User: Святослав
  Date: 25.11.2019
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="/login">
        <p>Name: <input name="j_username"/></p>
        <p>Pass: <input name="j_password"/></p>
        <input type="submit"/>
    </form>
    <a href="/registration">Sign up</a>
</body>
</html>
