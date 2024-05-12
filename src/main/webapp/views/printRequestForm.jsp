<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="fr">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Formulaire de demande d'impression</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                    crossorigin="anonymous">
                <link
                    href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css"
                    rel="stylesheet">
                <style>
                    body {
                        background-color: #f8f9fa;
                    }

                    #printRequestForm>div:nth-child(1)>div>ul>li.multiselect-item.filter {
                        margin-bottom: 5px;
                    }

                    #printRequestForm>div:nth-child(1)>div>ul>li.multiselect-item.filter>div>span.input-group-btn {
                        display: none;
                    }

                    #printRequestForm>div:nth-child(1)>div>ul>li.multiselect-item.filter>div {
                        margin: 0;
                    }

                    ul.multiselect-container {
                        padding: 5px;
                    }

                    .container {
                        max-width: 600px;
                        margin: 0 auto;
                        padding: 2rem;
                        background-color: #fff;
                        border-radius: 0.5rem;
                        box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
                    }

                    h1 {
                        text-align: center;
                        margin-bottom: 2rem;
                    }

                    .form-group {
                        margin-bottom: 1.5rem;
                    }

                    label {
                        font-weight: bold;
                    }

                    #printRequestForm>div:nth-child(1)>div>button {
                        background-color: #007bff;
                        border-color: #007bff;
                    }

                    input#file-upload-button {
                        background-color: #0062cc !important;
                    }

                    .btn-primary {
                        background-color: #007bff;
                        border-color: #007bff;
                    }

                    .multiselect-container>li>a>label {
                        padding-left: 2px;
                    }

                    .btn-primary:hover {
                        background-color: #0069d9;
                        border-color: #0062cc;
                    }
                </style>
            </head>

            <body>
                <div class="container">
                    <h1>Formulaire de demande d'impression</h1>
                    <form id="printRequestForm" action="printRequest" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="groupList">Choisissez les groupes :</label>
                            <select id="groupList" name="groupIds" class="form-control" multiple required>
                                <c:forEach var="group" items="${groups}">
                                    <option data-num-students="${group.numStudents}" value="${group.id}">${group.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="matierList">Choisissez les matières :</label>
                            <div class="input-group">
                                <input type="text" id="matierSearch" name="matierSearch" class="form-control"
                                    placeholder="Rechercher des matières">
                            </div>
                            <select id="matierList" name="matierIds" class="form-control" multiple required disabled>
                                <option value="">Sélectionnez un groupe</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="dateInput">Date de réception :</label>
                            <input type="datetime-local" id="dateInput" name="arrivalDateTime" class="form-control"
                                required />
                        </div>

                        <div class="form-group">
                            <label for="documentUpload">Document :</label>
                            <input type="file" id="documentUpload" name="document" class="form-control" required />
                        </div>

                        <div class="form-group" style="margin-top: 10px;">
                            <label for="numStudents">Nombre d'étudiants :</label>
                            <input type="number" id="numStudents" name="numStudents" class="form-control" required />
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">Valider</button>
                    </form>
                </div>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
                <script
                    src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.min.js"></script>

                <script>
                    function updateMatierSearchAvailability() {
                        var options = document.querySelectorAll("#matierList option");
                        var matierSearch = document.getElementById("matierSearch");
                        var containsSelection = Array.from(options).some(option => option.textContent.includes('Sélectionnez un groupe'));
                        if (containsSelection) {
                            matierSearch.disabled = true;
                        } else {
                            matierSearch.disabled = false;
                        }
                    }

                    document.getElementById("matierSearch").addEventListener("input", function () {
                        var searchKeyword = document.getElementById("matierSearch").value.toLowerCase();
                        var options = document.getElementById("matierList").getElementsByTagName("option");

                        for (var i = 0; i < options.length; i++) {
                            var optionText = options[i].textContent.toLowerCase();
                            if (optionText.includes(searchKeyword)) {
                                options[i].style.display = "";
                            } else {
                                options[i].style.display = "none";
                            }
                        }
                    });

                    document.addEventListener("DOMContentLoaded", function () {
                        var now = new Date();
                        var formattedDate = now.getFullYear() + '-' +
                            ('0' + (now.getMonth() + 1)).slice(-2) + '-' +
                            ('0' + now.getDate()).slice(-2) + 'T' +
                            ('0' + now.getHours()).slice(-2) + ':' +
                            ('0' + now.getMinutes()).slice(-2);
                        document.getElementById("dateInput").setAttribute('min', formattedDate);
                    });

                    $(document).ready(function () {
                        $('#groupList').multiselect({
                            buttonClass: 'btn btn-secondary',
                            nonSelectedText: 'Sélectionnez les groupes',
                            enableFiltering: true,
                            enableCaseInsensitiveFiltering: true,
                            maxHeight: 300
                        });

                        $('#groupList').change(function () {
                            var groupId = $(this).val();
                            if (groupId) {
                                $('#matierList').prop('disabled', false);
                                $.ajax({
                                    traditional: true,
                                    url: 'getMatiers',
                                    type: 'GET',
                                    data: {
                                        groupIds: groupId
                                    },
                                    success: function (data) {
                                        updateMatierSearchAvailability()
                                        $('#matierList').html(data);
                                        var options = $('#matierList option');
                                        var arrOptions = options.map(function () {
                                            return { value: $(this).val(), text: $(this).text() };
                                        }).get();
                                        arrOptions.sort(function (a, b) {
                                            return a.text.localeCompare(b.text);
                                        });
                                        var sortedOptions = arrOptions.map(function (option) {
                                            return '<option value="' + option.value + '">' + option.text + '</option>';
                                        });
                                        $('#matierList').html(sortedOptions.join(''));
                                        updateMatierSearchAvailability()
                                    },
                                    error: function () {
                                        $('#matierList').html('<option value="">Erreur lors de la récupération des matières</option>');
                                    }
                                });
                            } else {
                                $('#matierList').prop('disabled', true);
                                $('#matierList').html('<option value="">Sélectionnez un groupe</option>');
                            }
                        });
                    });

                    $('#printRequestForm').submit(function (event) {
                        event.preventDefault();

                        var selectedGroup = $('#groupList').val();
                        var selectedStudents = parseInt($('#numStudents').val());
                        var maxStudents = getMaxStudents(selectedGroup);
                        if (selectedStudents > maxStudents) {
                            console.log(maxStudents, 'ccccccc');
                            alert("Le nombre d'étudiants dépasse le maximum autorisé pour le groupe sélectionné. maxStudents :" + maxStudents.toString());
                            return; // Added a semicolon to end the statement
                        }

                        var formData = new FormData(this);
                        var fileInput = $('#documentUpload')[0];
                        var file = fileInput.files[0];
                        formData.append('fileName', file.name);
                        $.ajax({
                            url: 'printRequest',
                            type: 'POST',
                            data: formData,
                            processData: false,
                            contentType: false,
                            success: function (response) {
                                alert('La demande d\'impression a été soumise avec succès !');
                                $('#printRequestForm')[0].reset();
                                $('#groupList').multiselect('deselectAll', false);
                                $('#groupList').multiselect('updateButtonText');

                                $('#matierList').empty().prop('disabled', true);
                                $('#matierList').html('<option value="">Sélectionnez un groupe</option>');
                                location.reload();

                            },
                            error: function (xhr, status, error) {
                                alert('Une erreur s\'est produite lors de la soumission de la demande d\'impression : ' + error);
                            }
                        });
                    });

                    function getMaxStudents() {
                        var maxStudents = 0;
                        $('#groupList option:selected').each(function () {
                            maxStudents += parseInt($(this).data('num-students'));
                        });
                        return maxStudents;
                    }
                    updateMatierSearchAvailability();
                </script>
            </body>

            </html>