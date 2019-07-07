<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="../css/sb-admin.css" rel="stylesheet">
</head>
<body>
<div class="container" style="text-align: center; margin-top: 3%">

    <c:forEach items="${subscription}" var="subscription">
        <div class="card" style="margin-top: 3%">
            <a class="card-header" href="#" data-toggle="modal" data-target="#confirm${subscription.id}">${subscription.name}</a>
            <!-- Modal -->
            <div class="modal fade" id="confirm${subscription.id}" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-body">
                            <h6>
                                Do you want to join ${subscription.name} group?
                            </h6>
                            <p>
                                Your request will go to admin for approval and it will take some time
                            </p>
                        </div>

                        <div class="modal-footer">
                            <form class="form" action="/subscriptionDetails/${subscription.id}" method="post">
                                <button type="submit" class="btn btn-success">Request</button>
                            </form>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </c:forEach>
    <p>
        ${message}
    </p>
    <div class="container d-flex flex-row-reverse" style="text-align: center; margin-top: 3%">
        <a href="/home">Back to Dal Discussion</a>
    </div>

</div>
</body>
</html>
