
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="../css/sb-admin.css" rel="stylesheet">
    <style type="text/css">
        #username{
            color: #abdde5;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

<a class="navbar-brand mr-1" href="/home">Dal Discussion</a>

<button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
    <i class="fas fa-bars"></i>
</button>
<%
    String username = (String) session.getAttribute("firstName");
%>
    <div id="username" class="pt-3 row no-gutters w-100 justify-content-end align-bottom">
       Welcome <%=username%>
    </div>
</nav>
</body>
</html>
