<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
        <%@include file="../css/PostDetails.css"%>
    </style>
</head>

<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div id="wrapper">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div id="content-wrapper">

        <div class="container-fluid">
            <div id="Posts">
                <div id="titlePost">
                    <h4>
                       Post title:  ${post.post_title}
                    </h4>
                </div>
                <div id="description">
                    <h5>
                        Description: ${post.post_description}
                    </h5>
                </div>
                <c:forEach items="${images}" var="image">
                    <img src="${image.imageLink}" style="height: 100%; width: 100%">
                </c:forEach>
            </div>
            <c:forEach items="${comments}" var="comment">
                <div class="container">
                    <div class="card shadow p-3 mb-5 bg-white rounded">
                        <div class="card-body">
                            <div>
                                <div class="d-flex flex-row-reverse">
                                     Comment By: ${comment.commentBy}
                                </div>

                                <h5>
                                        ${comment.comment_description}
                                </h5>
                            </div>
                            <div class="card-title">
                                <c:forEach items="${comment.replies}" var="reply">
                                    <div class="d-flex flex-row-reverse" >
                                        Reply By: ${reply.replyBy}
                                    </div>
                                    ${reply.reply_description}<br>
                                </c:forEach>
                            </div>
                            <div>
                                <form class="form" method="post" action="/getPosts/${post.id}/${comment.id}"
                                      id="replyForm">
                                    <div class="container" style="margin-top: 3%">
                                        <div class="form-group">
                                            <input id="reply" type="text" name="reply" placeholder="Enter a Reply"
                                                   class="col-sm-9 input-lg form-control" required>
                                            <div class="d-flex flex-row-reverse">
                                                <button class="btn btn-info " type="submit">Reply</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div>

            </div>
            <form class="form" method="post" action="/getPosts/${post.id}" id="myForm">
                <div class="container" style="margin-top: 3%">
                    <div class="form-group">
                        <input id="commentTxt" type="text" name="comment" placeholder="Enter a Comment"
                               class="col-sm-9 input-lg form-control" required>
                        <div class="d-flex flex-row-reverse">
                            <button class="btn-info btn" type="submit">Comment</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Custom scripts for all pages-->
<script src="../js/sb-admin.min.js"></script>
<script>
    $("input").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
</script>
</body>
</html>
