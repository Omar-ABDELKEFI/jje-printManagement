<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subject List</title>
</head>
<body>
    <h1>Subjects Taught by Teacher (ID: 1)</h1>
    <ul>
        <c:forEach items="${subjectNames}" var="subject">
            <li>${subject}</li>
        </c:forEach>
    </ul>
</body>
</html>