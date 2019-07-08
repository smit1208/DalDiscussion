<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="../css/sb-admin.css" rel="stylesheet">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>

<div id="wrapper">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div id="content-wrapper">

        <div class="container-fluid">


           <c:if test="${empty(approvedSubscription)}">
               <h3>
                   Sorry you don't have any subscriptions yet!!!
                   <a href="/subscriptionDetails">Click Here to join a group</a>

               </h3>
           </c:if>
           <c:if test="${approvedSubscription.size()<=4}">
               <c:forEach items="${approvedSubscription}" var="sub">
                   <div class="card" style="margin-top: 3%">
                       <a class="card-header" href="/subscriptionDetails/${sub.group_id}" >${sub.groupName}</a>
                   </div>
               </c:forEach>

           </c:if>
           <c:if test="${approvedSubscription.size()<4}">
               <a href="/subscriptionDetails">Click Here to join a group</a>
           </c:if>
              ${error}

        </div>
    </div>
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="../js/sb-admin.min.js"></script>
</div>
</body>
</html>
