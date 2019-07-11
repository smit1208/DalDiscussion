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
    <table class="table table-striped">

        <tr>
            <th>User ID</th>
            <th>Group ID</th>
            <th>Status</th>
            <th>Approve</th>
            <th>Reject</th>

        </tr>
        <c:forEach items="${subscriptions}" var="subscriptions">
            <tr>
                <td>
                    <c:out value="${subscriptions.user_id}" />
                </td>
                <td>
                    <c:out value="${subscriptions.group_id}" />
                </td>
                <td>
                    <c:out value="${subscriptions.status}" />
                </td>
                <td>
                    <form class="form" action="/admin/allRequests/${subscriptions.id}" method="post">
                        <button type="submit" class="btn btn-success">Approve</button>

                    </form>
                </td>
                <td>
                    <form class="form" action="#" method="post">
                        <button type="submit" class="btn btn-danger">Reject</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="d-flex flex-row-reverse">
        <a href="/logout">
            Logout
        </a>
    </div>
</div>


</body>
</html>
