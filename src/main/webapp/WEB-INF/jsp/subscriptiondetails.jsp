<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <link href="../css/sb-admin.css" rel="stylesheet">
</head>
<body>
<div class="container" style="text-align: center; margin-top: 3%">

    <c:forEach items="${subscription}" var="subscription">
        <div class="card" style="margin-top: 3%">
            <a class="card-header" href="#">${subscription.name}</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
