<%-- 
    Document   : main
    Created on : Oct 21, 2019, 11:13:43 AM
    Author     : taran
--%>

<%@page import="java.util.ArrayList"%>
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
    <jsp:useBean id="user" class="com.javabeans.Client"/>
    <%user.retrieve((Integer) session.getAttribute("ClientID"));
        String check = "none";
        String save = "none";
        if(user.checking_Exists()){
            check = "$" + String.valueOf(user.checkings.get_balance());
        }
        if(user.savings_Exists()){
            save = "$" + String.valueOf(user.savings.get_balance());
        }
        
        ArrayList<String> transactions = user.getTransactions(3);
    %>
    
    
    
    <!----------------------------------------------------------------------------------------------------------------->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Natareel National Bank</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="main.jsp">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Checking.jsp">Checking</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Savings.jsp">Savings</a>
                </li>
                <li class="nav-item">
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
        <% out.println(user.get_First());%>
        <div class="row">
            <div class="col-sm-6">
                <div class="card text-left">
                    <div class="card-header">
                        Account Balances
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Savings: <% out.print(save);%></li>        
                        <li class="list-group-item">Checking: <% out.print(check);%></li>
                        <li class="list-group-item"><a href="AddAccount?request=1">Add an Account </a></li>
                    </ul>
                    <div class="card-footer text-muted">
                        <a href="ListAllAccounts" >View All Accounts</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card text-left">
                    <div class="card-header">
                        Transactions
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><% out.print(transactions.get(0));%></li>
                        <li class="list-group-item"><% out.print(transactions.get(1));%></li>
                        <li class="list-group-item"><% out.print(transactions.get(2));%></li>
                    </ul>
                    <div class="card-footer text-muted">
                        <a href="Spending.jsp">View All Transactions</a>
                    </div>
                </div>
            </div>
        </div>
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