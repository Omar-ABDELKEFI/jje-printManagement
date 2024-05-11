<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agent Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Agent Dashboard</h1>
        <table>
            <thead>
                <tr>
                    <th>Teacher Name</th>
                    <th>Number of Copies</th>
                    <th>Date of Reception</th>
                    <th>Document</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tasks}" var="task" varStatus="loop">
                    <tr>
                        <td>${task.teacherName}</td>
                        <td>${task.numCopies}</td>
                        <td>${task.receptionDate}</td>
                        <td id="document${loop.index + 1}">Document ${loop.index + 1}</td>
                        <td><button onclick="printDocument(${loop.index + 1})">Print Document</button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        function printDocument(documentNumber) {
            // Assuming you have some logic to fetch and print the document
            alert("Printing Document " + documentNumber);
        }
    </script>
</body>
</html>
