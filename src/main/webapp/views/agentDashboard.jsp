<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Dashboard</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.9.359/pdf.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

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
        canvas {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-between w-100">
        <div class="container ">
            <a class="navbar-brand" href="#">Agent Dashboard</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <form class="form-inline my-2 my-lg-0" action="LogoutServlet" method="get"> 
                    <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Logout</button>
                </form>
            </div>
        </div>
    </nav>
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
                        <td>
                            <canvas id="pdf-render-${loop.index}" width="59" height="84">></canvas>
                            <div style="text-align: center; margin-left:-13px ;">${task.fileName}</div>
                            <script>
                                var pdfData = atob("${task.document}");
                                var canvas = document.getElementById("pdf-render-${loop.index}");

                                pdfjsLib.getDocument({ data: pdfData }).promise.then(pdf => {
                                    pdf.getPage(1).then(page => {
                                        const viewport = page.getViewport({ scale: 0.1 });
                                        const context = canvas.getContext("2d");
                                        canvas.height = viewport.height;
                                        canvas.width = viewport.width;

                                        const renderContext = {
                                            canvasContext: context,
                                            viewport: viewport
                                        };

                                        page.render(renderContext);
                                    });
                                });
                            </script>
                        </td>
                        <td>
                            <button onclick="printPdf('${task.document}')">Print</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        function printPdf(base64PdfData) {
            const pdfUrl = 'data:application/pdf;base64,' + base64PdfData;
            const scale = 4; 
            const windowContent = '<!DOCTYPE html><html><head><title>Print PDF</title></head><body><object data="' + pdfUrl + '" type="application/pdf" style="width: 100%; height: 100vh;"></object></body></html>';
    
            const printWin = window.open('', '', 'width=800,height=600');
            printWin.document.open();
            printWin.document.write(windowContent);
            printWin.document.close();
            printWin.onload = function() {
                printWin.print();
            };
        }
    </script>
    
    
</body>
</html>
