<%-- 
    Document   : Transfers
    Created on : Oct 21, 2019, 12:16:35 PM
    Author     : taran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!-----------------------------------------Bootstrap 4 css-------------------------------------------------------->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-----------------------------------------Bootstrap 4 css-------------------------------------------------------->
</head>

<body>
    
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Natareel National Bank</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="main.jsp">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Checking.jsp">Checking</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Savings.jsp">Savings</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="Transfers.jsp">Transfers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Spending.jsp">Spending</a>
                </li>
            </ul>

        </div>
    </nav>

    <div id="Page_Content" class="container mt-3">
        <!----------------------------------------Body---------------------------------------->
        <form action="Transfers" method="post">
            <div class="form-group">
                <label>From:</label>
                <div class="input-group">
                    <input name="from" type="text" class="form-control" aria-label="Text input">
                </div>
            </div>
            <div class="form-group">
                <label>To:</label>
                <div class="input-group">
                    <input type="text" name="to" class="form-control" aria-label="Text input">
                </div>
            </div>
            <div class="form-group">
                <label>Amount</label>
                <input type="number" name ="amount" class="form-control" placeholder="$0.00">
            </div>
            <button type="submit" class="btn btn-primary">Transfer</button>
        </form>
        <!----------------------------------------Body---------------------------------------->
    </div>

    <!-------------------------------Bootstrap 4 Plugins---------------------------------->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <!-------------------------------Bootstrap 4 Plugins---------------------------------->
</body>

</html>