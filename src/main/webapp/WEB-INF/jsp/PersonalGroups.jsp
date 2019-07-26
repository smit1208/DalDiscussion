<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="../css/sb-admin.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:forEach items="${privatePosts}" var="post">
        <div class="card" style="margin-top: 3%">
            <a class="card-header" href="/getPosts/${post.id}" >${post.post_title}</a>
            <div class="card-text">${post.post_description}</div>
        </div>
<%--        <div class="card shadow p-3 mb-5 bg-white rounded" style="margin-top: 3%">--%>
<%--            <div class="card-header text-info">${post.post_title}</div>--%>
<%--            <div class="card-text">${post.post_description}</div>--%>
<%--            <div class="d-flex">--%>
<%--                <div class="mr-auto">--%>
<%--                    <a href="/getPosts/${post.id}"><i class="far fa-comment"></i></a>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <button type="button" class="btn btn-outline-secondary" data-toggle="modal"--%>
<%--                            data-target="#report${post.id}">--%>
<%--                        <i class="fas fa-exclamation"></i>--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="modal fade" id="report${post.id}" tabindex="-1" role="dialog"--%>
<%--             aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--            <div class="modal-dialog" role="document">--%>
<%--                <div class="modal-content">--%>
<%--                    <div class="modal-header">--%>
<%--                        <h5 class="modal-title" id="exampleModalLabel">--%>
<%--                            <p>Please select the reason to report the post : </p>--%>
<%--                                ${post.post_title}--%>
<%--                        </h5>--%>
<%--                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                            <span aria-hidden="true">&times;</span>--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                    <div class="modal-body">--%>
<%--                        <form action="/report/${post.id}" method="post">--%>
<%--                            <button type="submit" class="btn-block btn-outline-danger">Inappropriate--%>
<%--                            </button>--%>
<%--                        </form>--%>
<%--                        <form action="/report/${post.id}" method="post">--%>
<%--                            <button type="submit" class="btn-block btn-outline-danger">Spam</button>--%>
<%--                        </form>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
    </c:forEach>
</div>

</body>
</html>
