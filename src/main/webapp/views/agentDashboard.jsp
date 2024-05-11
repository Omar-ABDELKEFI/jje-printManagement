<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
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
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f0f0f0;
        }
        button {
            padding: 8px 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
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
                        <td>${task.fileName}</td>
                        <td><button onclick="">Print Document</button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        function printDocument(pdfBlob) {
            const pdfUrl = URL.createObjectURL(pdfBlob);
            const pdfWindow = window.open(pdfUrl);
            pdfWindow.onload = function() {
                pdfWindow.print();
            };
        }
    </script>
</body>
</html>
