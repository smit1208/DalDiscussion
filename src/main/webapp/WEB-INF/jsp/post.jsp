<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                        <form:form method="post" action="/addPost"   enctype="multipart/form-data" modelAttribute="addPost">
                                            <div class="form-group">
                                                <label>Post Title</label>
                                                <form:input id = "postTitle" type="text" name="postTitle" path="post_title" class="form-control" required="required" ></form:input><br>
                                            </div>
                                            <div class="form-group">
                                                <label>Post Description</label>
                                                <form:textarea rows="4" cols="50" name="postDesc" path="post_description" class="form-control" required="required"></form:textarea>
                                            </div>
                                            <div class="form-group">
                                                <label>Category</label>
                                                <form:select name="category" path="category" class="form-control">
                                                    <form:option value="1">Discussion</form:option>
                                                </form:select>
                                            </div>
                                            <div class="form-group">
                                                <label>Group</label>
                                                <form:select name="group" path="groupId" class="form-control">
                                                    <c:forEach items="${approvedSubscription}" var="sub">
                                                        <form:option value="${sub.group_id}">${sub.groupName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                            <div class="form-group">
                                                <div style="color: red;">
                                                    ${message}
                                                </div>
                                                <label>Add an image</label>
                                                <form:input type="file" path="files" class="form-control" name="image" accept="image/*"  multiple="multiple"></form:input>
                                            </div>
                                            <div class="form-group">
                                                <form:button class="btn btn-info btn-block" type="submit">POST</form:button>
                                            </div>
                                        </form:form>
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
        <script>
            $("input").on("keypress", function(e) {
                if (e.which === 32 && !this.value.length)
                    e.preventDefault();
            });

            $("textarea").on("keypress", function(e) {
                if (e.which === 32 && !this.value.length)
                    e.preventDefault();
            });
        </script>
</div>

</body>
</html>
