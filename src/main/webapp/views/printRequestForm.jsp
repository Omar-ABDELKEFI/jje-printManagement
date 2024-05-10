<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Print Request Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        /* Add your custom CSS styles here */
        body {
            padding: 20px;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
        }
        label {
            margin-top: 10px;
            font-weight: bold;
        }
        .form-group {
            margin-bottom: 20px;
        }
        button[type="submit"] {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1 class="text-center">Print Request Form</h1>
    <form action="printRequest" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="groupList">List Groupe:</label>
            <select id="groupList" name="groupIds" class="form-control" multiple>
                <c:forEach var="group" items="${groups}">
                    <option value="${group.id}">${group.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="matierList">List Matier:</label>
            <select id="matierList" name="matierIds" class="form-control" multiple>
                <c:forEach var="matier" items="${matiers}">
                    <option value="${matier.id}">${matier.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="dateInput">Dat Dynamique:</label>
            <input type="datetime-local" id="dateInput" name="arrivalDateTime" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="documentUpload">Develt:</label>
            <input type="file" id="documentUpload" name="document" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="numStudents">videlev:</label>
            <input type="number" id="numStudents" name="numStudents" class="form-control" required />
        </div>

        <button type="submit" class="btn btn-primary btn-block">Valider</button>
    </form>
    <!-- Bootstrap JS (optional) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
