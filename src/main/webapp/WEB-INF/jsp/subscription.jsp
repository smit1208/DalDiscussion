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
</head>
<style type="text/css">
    <%@include file="../css/subscription.css"%>
</style>
<body>
<jsp:include page="navbar.jsp"></jsp:include>

<div id="wrapper">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row">
            <c:if test="${approvedSubscription.size()<=5}">
                <c:forEach items="${approvedSubscription}" var="sub">
                    <div class="col-md-4">
                        <div class="card shadow-lg p-3 mb-5 bg-white rounded" id="subCard">
                            <a class="card-body" href="/subscriptionDetails/${sub.group_id}">${sub.groupName}</a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <c:if test="${approvedSubscription.size()<5}">
            <a href="/subscriptionDetails">Click Here to join a group</a>
        </c:if>
        ${error}
    </div>
    <script src="../js/sb-admin.min.js"></script>
</div>
</body>
</html>
