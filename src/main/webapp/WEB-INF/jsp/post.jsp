<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div id="wrapper">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div id="content-wrapper">

        <div class="container-fluid">

            <div class="d-flex justify-content-center">
                <div class="col-md-5 ">
                    <div class="container">
                        <div class="row">
                            <div class="col mr-auto">
                                <div class="card shadow-lg p-3 mb-5 bg-white rounded "
                                     style="max-width: 30rem; margin-top: 20%;">
                                    <div class="card-header text-info ">
                                        <h4>Add Post</h4>
                                    </div>
                                    <div class="card-body text-info">
                                        <form method="post" action="/savePost"  enctype="multipart/form-data">
                                            <div class="form-group">
                                                <label>Post Title</label>
                                                <input type="text" name="postTitle" class="form-control" required><br>
                                            </div>
                                            <div class="form-group">
                                                <label>Post Description</label>
                                                <textarea rows="4" cols="50" name="postDesc" class="form-control"
                                                          required> </textarea>
                                            </div>
                                            <div class="form-group">
                                                <label>Category</label>
                                                <select name="category" class="form-control">
                                                    <option value="1" selected>Discussion</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Group</label>
                                                <select name="group" class="form-control">
                                                    <c:forEach items="${approvedSubscription}" var="sub">
                                                        <option value=${sub.group_id}>${sub.groupName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <div style="color: red;">
                                                    ${message}
                                                </div>
                                                <label>Add an image</label>
                                                <input type="file" class="form-control" name="image" accept="image/*" multiple>
                                            </div>
                                            <div class="form-group">
                                                <button class="btn btn-info btn-block" type="submit">POST</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../js/sb-admin.min.js"></script>
</div>

</body>
</html>
