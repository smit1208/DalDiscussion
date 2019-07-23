<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <title>Dal Discussion</title>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="../css/sb-admin.css" rel="stylesheet">
    <style type="text/css">
        <%@include file="../css/search.css"%>
    </style>
</head>

<body id="page-top">

<%--https://startbootstrap.com/templates/sb-admin/--%>
<jsp:include page="navbar.jsp"></jsp:include>

<div id="wrapper">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div id="content-wrapper">

        <div class="container-fluid">

            <h2 style="text-align: center">Welcome to Dal Discussion</h2>
            <div class="container" style="text-align: center; margin-top: 3%">

                <form id="myForm" action="/home" method="post">
                    <input type="text" name="search" placeholder="Search..">
                </form>
                <form id="addPost" action="/addPost">
                    <button type="submit" class="btn btn-outline-info shadow-sm p-3 mb-5 rounded">Add Post</button>
                </form>
                <c:forEach items="${posts}" var="post">
                    <div class="card shadow p-3 mb-5 bg-white rounded" style="margin-top: 3%">
                        <a class="card-header text-info" href="/getPosts/${post.id}">${post.post_title}</a>
                        <div class="card-text">${post.post_description}</div>
                        <div class="d-flex flex-row-reverse">
                            <button type="button" class="btn btn-outline-secondary" data-toggle="modal"
                                    data-target="#report${post.id}">
                                <i class="fas fa-exclamation"></i>
                            </button>
                        </div>
                    </div>
                    <div class="modal fade" id="report${post.id}" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">
                                        <p>Please select the reason to report the post : </p>
                                            ${post.post_title}
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/report/${post.id}" method="post">
                                        <button type="submit" class="btn-block btn-outline-danger">Inappropriate
                                        </button>
                                    </form>
                                    <form action="/report/${post.id}" method="post">
                                        <button type="submit" class="btn-block btn-outline-danger">Spam</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>

</body>
<script src="../js/sb-admin.min.js"></script>
</html>
