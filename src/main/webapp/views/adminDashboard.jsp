<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
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
    <h2>Welcome Admin!</h2>
    
    <!-- Add a section to display the list of users -->
    <h3>User List</h3>
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over the list of users -->
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>
                        <input type="checkbox" id="statusSwitch${user.id}" 
                               onchange="toggleStatus(${user.id}, this.checked)"
                               <% if(user.getStatus().equals("Active")) { %>checked<% } %>>
                        <label for="statusSwitch${user.id}">${user.status}</label>
                    </td>
    
                    <!-- Add edit and delete buttons -->
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal${user.id}">
                            Edit
                        </button>
                        
                    </td>
                </tr>

                <!-- Include edit modal -->
                <div class="modal fade" id="editModal${user.id}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                    <%-- Include editUser.jsp here --%>
                    <%@ include file="editUser.jsp" %>
                </div>
                </div>

            </c:forEach>
        </tbody>
    </table>

    <!-- Link Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function toggleStatus(userId, isChecked) {
            var status = isChecked ? "Active" : "Inactive";
            $.ajax({
                url: "UpdateStatusServlet",
                type: "POST",
                data: { userId: userId, status: status },
                success: function(response) {
                    // Optionally handle the response here
                }
            });
        }
    </script>
    
</body>
</html>
