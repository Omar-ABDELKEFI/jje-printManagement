<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Matier Groupe</title>
    <!-- Link Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit Matier Groupe</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Add your edit form here -->
                <form action="EditMatierGroupeServlet" method="post">
                    <input type="hidden" name="matierGroupeId" value="${matierGroupe.id}">
                    <div class="form-group">
                        <label for="matierId">Matier:</label>
                        <select class="form-control" id="matierId" name="matierId">
                            <c:forEach var="matier" items="${matierList}">
                                <option value="${matier.id}" ${matier.id == matierGroupe.matierId ? 'selected' : ''}>${matier.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="groupeId">Group:</label>
                        <select class="form-control" id="groupeId" name="groupeId">
                            <c:forEach var="groupe" items="${groupeList}">
                                <option value="${groupe.id}" ${groupe.id == matierGroupe.groupeId ? 'selected' : ''}>${groupe.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </form>
            </div>
        </div>
    </div>
    <!-- Link Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
