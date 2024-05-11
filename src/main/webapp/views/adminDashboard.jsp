<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests"> 

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
    <div style="display: flex; justify-content: space-between;" class="px-5">
        <h3>User List</h3>
        <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#addUserModal">
            Add User
        </button>
    </div>
    <div class="modal fade" id="addUserModal" data-backdrop="false" tabindex="-1" role="dialog" aria-labelledby="addUserModalLabel" aria-hidden="true">
        <%-- Include editUser.jsp here --%>
        <%@ include file="addUser.jsp" %>
    </div>

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
                        <c:choose>
                            <c:when test="${user.status.equals('active')}">
                                <input type="checkbox" id="statusSwitch${user.id}" 
                                onchange="statusSwitch(${user.id}, this.checked)"
                                checked> 
                                
                            <label for="statusSwitch${user.id}" style="width:58px">${user.status}</label>
                            </c:when>    
                            <c:otherwise>
                                <input type="checkbox" id="statusSwitch${user.id}" 
                                onchange="statusSwitch(${user.id}, this.checked)"
                                > 
                                
                                <label for="statusSwitch${user.id}">${user.status}</label>
                            </c:otherwise>
                        </c:choose>                        
                    </td>

                    <!-- Add edit and delete buttons -->
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal${user.id}">
                            Edit
                        </button>
                        
                    </td>
                </tr>

                <!-- Include edit modal -->
                <div class="modal fade" data-backdrop="false" id="editModal${user.id}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script>
        function statusSwitch(userId, isChecked) {
            var status = isChecked ? "active" : "inactive";
            var label = document.querySelector("label[for='statusSwitch" + userId + "']");
                    if (isChecked) {
                        label.innerText = "active";
                    } else {
                        label.innerText = "inactive";
                    }

            $.ajax({
                url: "UpdateStatusServlet",
                type: "POST",
                data: { userId: userId, status: status },
                success: function(response) {
                    

                }
            });
        }
    </script>
    
</body>
</html>
