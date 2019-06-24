<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login | DAL Discussion</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

    <div class="container">
    <div class="card">
        <div class="card-header">
            <h5 class="mb-0">Login</h5>
        </div>
        <div class="card-body">
            <form action="/login" method="POST">
            <div class="form-group">

                <label>Email</label>
                <input type="text" class="form-control"name="username" value="${username}"/>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" name="password" value="${password}" />
            </div>
            <div class="form-group text-center text-danger">
                ${message}
            </div>
            <div class="form-group text-center">
                <input type="submit" class="btn btn-primary" value="Login" />
                &nbsp;
                <a href="/forgot-password">Forgot Password</a>
            </div>
                <div class="text-center" >
                    <a href="/register">Register</a>
                </div>
            </form>
        </div>
    </div>
    </div>

</body>
</html>
