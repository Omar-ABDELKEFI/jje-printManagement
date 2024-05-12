<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests"> 

    <title>Group Management</title>
    <!-- Link Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add custom CSS -->
    <style>
        /* Custom styles */
        .table th, .table td {
            vertical-align: middle;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">Group Management</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="AdminDashboardServlet">Admin Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="MatierManagementServlet">Course Management</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="MatierGroupeManagementServlet">Group/Course Management</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0" action="LogoutServlet" method="get"> 
                    <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Logout</button>
                </form>
            </div>
        </div>
    </nav>
    <div style="display: flex; justify-content: space-between;" class="px-5">
        <h3>Group List</h3>
        <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#addGroup">
            Add Group
        </button>
    </div>
    <div class="modal fade" id="addGroup" data-backdrop="false" tabindex="-1" role="dialog" aria-labelledby="addGroupLabel" aria-hidden="true">
        <%-- Include addGroupe.jsp here --%>
        <%@ include file="addGroupe.jsp" %>
    </div>

    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Number of Students</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over the list of groups -->
            <c:forEach var="groupe" items="${groupeList}">
                <tr>
                    <td>${groupe.id}</td>
                    <td>${groupe.name}</td>
                    <td>${groupe.numStudents}</td>
                    <!-- Add edit and delete buttons if needed -->
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal${groupe.id}">
                            Edit
                        </button>
                        
                    </td>
                
                </tr>
                <div class="modal fade" data-backdrop="false" id="editModal${groupe.id}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                    <%-- Include editGroupe.jsp here --%>
                    <%@ include file="editGroupe.jsp" %>
                </div>

            </c:forEach>
        </tbody>
    </table>

    <!-- Link Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</body>
</html>
