<%--
  Created by IntelliJ IDEA.
  User: newk
  Date: 1/5/21
  Time: 1:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile: ${user.firstName} ${user.lastName}</title>
    <link rel="stylesheet" type="text/css" href="/styles/base.css">
</head>
<body>
    <div class="title">
        <h1>Hello ${user.firstName} ${user.lastName} this is your profile page!</h1>
        <br><hr>
    </div>
    <div class="container">
        <h3>First name:</h3><p>${user.firstName}</p>
        <h3>Last name:</h3><p>${user.lastName}</p>
        <h3>Age:</h3><p>${user.age}</p>
        <h3><abbr title="Date Of Birth">DOB:</abbr></h3><p>${user.dateOfBirth}</p>
    </div>
</body>
</html>
