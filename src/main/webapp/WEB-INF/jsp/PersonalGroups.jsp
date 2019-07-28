<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="../css/sb-admin.css" rel="stylesheet">
<style type="text/css">
    <%@include file="../css/personalGroup.css"%>
</style>
</head>
<body>
<div class="container">
    <c:forEach items="${privatePosts}" var="post">
        <div id="personalGroupTitle">
            <div class="d-flex justify-content-center">
                <h2>
                        ${QA}
                </h2>
            </div>
            <div class="d-flex justify-content-center">
                <h2>
                        ${Data}
                </h2>
            </div>
            <div class="d-flex justify-content-center">
                <h2>
                        ${Cloud}
                </h2>
            </div>
            <div class="d-flex justify-content-center">
                <h2>
                        ${Web}
                </h2>
            </div>
        </div>

        <div class="card shadow p-3 mb-5 bg-white rounded" style="margin-top: 3%; text-align: center">
            <div class="card-header text-info">${post.post_title}</div>
            <div class="card-text">${post.post_description}</div>
            <div class="d-flex">
                <div class="mr-auto">
                    <a href="/getPosts/${post.id}"><i class="far fa-comment"></i></a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
