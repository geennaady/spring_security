<%--
  Created by IntelliJ IDEA.
  User: Augustin
  Date: 12.03.2020
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Edit User</title>
</head>
<body>
    <span class="user">
            <form action="/admin/update" method="post">
                <p>Change User</p>
                <input type="hidden" name="id" value="${param.id}">
                <p>New Name</p><input type="text" name="username" value="${param.username}" >
                <p>New Pass</p><input type="text" name="password" value="${param.password}" >
                <p>New Role</p><select name="role">
                    <option value="1">Admin</option>
                    <option value="2">User</option>
                </select>
                <input class="butt" type="submit" value="Change">
            </form>
    </span>
</body>
</html>
