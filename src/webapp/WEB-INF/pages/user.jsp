<%--
  Created by IntelliJ IDEA.
  User: Augustin
  Date: 31.03.2020
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${authUser.getUsername()}</title>
</head>
<body>
<p>Hello, ${authUser.getUsername()}!</p>
<a href="/logout">Logout</a>
</body>
</html>
