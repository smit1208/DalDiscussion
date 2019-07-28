<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <div class="card shadow p-3 mb-5 bg-white rounded" style="margin-top: 3%">
                        <div class="card-header text-info">${post.post_title}</div>
                        <div class="card-text">${post.post_description}</div>
                        <div class="d-flex">
                            <div style="margin-right: 2%">
                                <a href="/getPosts/${post.id}"><i class="far fa-comment"></i></a>
                            </div>
                            <div class="mr-auto">
                                <form class="form" method="post" action="/dashboard/delete/${post.id}">
                                    <button class="btn btn-danger" type="submit"><i class="fas fa-trash"></i></button>
                                </form>
                            </div>
                            <div>
                                <form class="form" method="post" action="/update/${post.id}">
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#updated${post.id}">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </form>
                            </div>
                            <!-- Button trigger modal -->
                            <!-- Modal -->
                            <div class="modal fade" id="updated${post.id}" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">
                                                Update: ${post.post_title}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="/updatedData/${post.id}" method="post">
                                            <div class="form-group">

                                                <input type="text" class="form-control" id="title" name="title"
                                                       aria-describedby="emailHelp"  value = ${post.post_title} placeholder="Post_Title" required>

                                            </div>
                                            <div class="form-group">

                                                <input type="text" class="form-control" id="desc" name="desc" value = ${post.post_description}
                                                       placeholder="Post_Description" required>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Update</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>

        </div>
    </div>
    <script src="../js/sb-admin.min.js"></script>

</div>

</body>

</html>
