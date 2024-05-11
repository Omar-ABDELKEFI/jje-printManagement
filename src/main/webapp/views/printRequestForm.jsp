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
            <!-- Bootstrap Multiselect CSS -->
            <link
                href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css"
                rel="stylesheet">
        </head>

        <body>
            <h1 class="text-center">Print Request Form</h1>
            <form id="printRequestForm" action="printRequest" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="groupList">Choose Group:</label>
                    <select id="groupList" name="groupIds" class="form-control" multiple required>
                        <c:forEach var="group" items="${groups}">
                            <option data-num-students="${group.numStudents}" value="${group.id}">${group.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="matierList">Choose Matiers:</label>
                    <select id="matierList" name="matierIds" class="form-control" multiple required disabled>
                        <option value="">Select Group First</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="dateInput">Dat Dynamique:</label>
                    <input type="datetime-local" id="dateInput" name="arrivalDateTime" class="form-control" required />
                </div>

                <div class="form-group">
                    <label for="documentUpload">document</label>
                    <input type="file" id="documentUpload" name="document" class="form-control" required />
                </div>

                <div class="form-group">
                    <label for="numStudents">student number:</label>
                    <input type="number" id="numStudents" name="numStudents" class="form-control" required />
                </div>

                <button type="submit" class="btn btn-primary btn-block">Valider</button>
            </form>

            <!-- Bootstrap JS (required) -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            <!-- Bootstrap Multiselect JS -->
            <script
                src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.min.js"></script>

            <!-- Custom Script -->
            <script>
                $(document).ready(function () {
                    // Initialize Bootstrap Multiselect for group list
                    $('#groupList').multiselect({
                        buttonClass: 'btn btn-secondary',
                        nonSelectedText: 'Select Group',
                        enableFiltering: true,
                        enableCaseInsensitiveFiltering: true,
                        maxHeight: 300
                    });

                    $('#groupList').change(function () {
                        var groupId = $(this).val();
                        console.log(groupId, "groupId")
                        if (groupId) {
                            $('#matierList').prop('disabled', false);
                            $.ajax({
                                traditional: true,
                                url: 'getMatiers',
                                type: 'GET',
                                data: { groupIds: groupId },
                                success: function (data) {
                                    $('#matierList').html(data);
                                },
                                error: function () {
                                    $('#matierList').html('<option value="">Error Fetching Matiers</option>');
                                }
                            });
                        } else {
                            $('#matierList').prop('disabled', true);
                            $('#matierList').html('<option value="">Select Group First</option>');
                        }
                    });
                });
                $('#printRequestForm').submit(function (event) {
                    event.preventDefault(); // Prevent default form submission

                    var selectedGroup = $('#groupList').val();
                    var selectedStudents = parseInt($('#numStudents').val());
                    var maxStudents = getMaxStudents(selectedGroup);
                    console.log(selectedStudents, 'selectedStudents')
                    console.log(maxStudents, 'maxStudents')
                    if (selectedStudents > maxStudents) {
                        alert('Number of students exceeds the maximum for the selected group');
                        event.preventDefault(); // Prevent form submission
                    }
                    var formData = new FormData(this); // Create FormData object from form data

                    $.ajax({
                        url: 'printRequest', // Servlet URL for form submission
                        type: 'POST',
                        data: formData,
                        processData: false, // Prevent jQuery from processing data
                        contentType: false, // Prevent jQuery from setting content type
                        success: function (response) {
                            alert('Print request submitted successfully!');
                            $('#printRequestForm')[0].reset();
                            $('#groupList').multiselect('deselectAll', false);
                            $('#groupList').multiselect('updateButtonText');
                            $('#matierList').empty().prop('disabled', true);
                        },
                        error: function (xhr, status, error) {
                            alert('Error occurred while submitting print request: ' + error);
                        }
                    });
                });

                function getMaxStudents() {
                    var maxStudents = 0;
                    console.log('eeeeeeeeeeeeee');

                    // Retrieve the numStudents property of the selected group
                    $('#groupList option:selected').each(function () {
                        var groupId = $(this).val();
                        console.log(groupId, 'eeeeeeeeeeeeee');
                        console.log(parseInt($(this).data('num-students')), 'eeeeeeeeeeeeee');

                        maxStudents += parseInt($(this).data('num-students'));
                    });
                    return maxStudents;
                }

            </script>
        </body>

        </html>