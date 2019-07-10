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

            <h3 style="text-align: center">Welcome to Dal Discussion</h3>
            <div class="d-flex flex-row-reverse">
                <c:if test="${user  != 'admin'}">
                 <h4>
                     Welcome ${user}
                 </h4>
                </c:if>
                
            </div>
            <div class="container" style="text-align: center; margin-top: 3%">

                <form id="myForm">
                    <input type="text" placeholder="Search your posts" class="col-sm-5 input-lg">
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
                <form id="addPost" action="/addPost">
                    <button type="submit" class="btn btn-primary">Add Post</button>
                </form>

                <c:forEach items="${posts}" var="post">
                    <div class="card" style="margin-top: 3%">
                        <a class="card-header" href="/getPosts/${post.id}" >${post.post_title}</a>
                        <div class="card-text">${post.post_description}</div>
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
