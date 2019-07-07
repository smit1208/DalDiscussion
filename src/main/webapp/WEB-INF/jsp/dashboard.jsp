<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <title>Dal Discussion</title>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <link href="../css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

<%--https://startbootstrap.com/templates/sb-admin/--%>
<jsp:include page="navbar.jsp"></jsp:include>

<div id="wrapper">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div id="content-wrapper">

        <div class="container-fluid">
            <div class="container" style="text-align: center; margin-top: 3%">
                <c:forEach items="${posts}" var="post">
                    <div class="card" style="margin-top: 3%">
                        <a class="card-header" href="" >${post.post_title}</a>
                        <div class="card-text">${post.post_description}</div>
                    </div>
                    <div class="d-flex flex-row-reverse">
                        <form class="form" method="post" action="/dashboard/delete/${post.id}">
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </form>
                    </div>
                </c:forEach>

            </div>

        </div>
    </div>

    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="../js/sb-admin.min.js"></script>

</div>

</body>

</html>
