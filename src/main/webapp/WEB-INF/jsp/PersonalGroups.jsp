<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <link href="../css/sb-admin.css" rel="stylesheet">

    <title>Title</title>
</head>
<body>
<div class="container">
    <c:forEach items="${privatePosts}" var="post">
        <div class="card" style="margin-top: 3%">
            <a class="card-header" href="/getPosts/${post.id}" >${post.post_title}</a>
            <div class="card-text">${post.post_description}</div>
        </div>
    </c:forEach>
</div>

</body>
</html>
