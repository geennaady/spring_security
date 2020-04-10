<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>List all users</title>
</head>
<style>
    #grid {
        display: grid;
        grid-template-rows: repeat(8, 1fr);
        grid-template-columns: repeat(8, 1fr);
        grid-gap: 5px;
    }
    .user {
        font-family: Arial;
        font-size: 18px;
        padding: .5em;
        background: gold;
        text-align: center;
    }
    button, .butt {
        font-family: Arial;
        background-color: darkorange;
        border: none;
        color: black;
        padding: 5px 8px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 14px;
    }
    a {
        font-family: Arial;
        background-color: orange;
        text-decoration: none;
        font-size: 24px;
    }
</style>
<body>
        <a href="/logout">Logout</a>
        <div id="grid">
            <c:forEach var="user" items="${users}" >
                    <span class="user">
                        <p>Id: ${user.getId()}</p>
                        <p>Name: ${user.getUsername()}</p>
                        <p>Password: ${user.getPassword()}</p>

                        <form action="admin/delete" method="post">
                            <input type="hidden" name="id" value="${user.getId()}">
                            <input class="butt" type="submit" value="Delete">
                        </form>
                <%--убрать поля--%>
                        <form action="admin/update" method="get">
                            <input type="hidden" name="id" value="${user.getId()}">
                            <input type="hidden" name="name" value="${user.getUsername()}">
                            <input type="hidden" name="pass" value="${user.getPassword()}">
                            <input class="butt" type="submit" value="Change">
                        </form>
                    </span>
            </c:forEach>
        <span class="user">
            <form:form action="admin/add" method="post" modelAttribute="user">
                    <span>
                        <p>New User</p>
                        <p>Name: <form:input path="username"/></p>
                        <br>
                        <p>Password: <form:input path="password"/></p>
                        <br>
                        <button type="submit">Add me</button>
                    </span>
            </form:form>
        </span>
    </div>
</body>
</html>