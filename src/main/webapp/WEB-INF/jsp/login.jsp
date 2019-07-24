<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login | DAL Discussion</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="../css/login.css"%>

        .back_img {
            background-image: url("https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Dalhousie_University_bicentennial_%E2%80%93_Halifax%2C_NS_%E2%80%93_%282018-08-26%29.jpg/1600px-Dalhousie_University_bicentennial_%E2%80%93_Halifax%2C_NS_%E2%80%93_%282018-08-26%29.jpg") !important;
            background-size: cover;
            background-repeat: no-repeat;
        }

    </style>
</head>

<body class="back_img">
<div class="d-flex flex-row-reverse">
    <div class="col-md-5 ">
        <div class="container">
            <div class="row">
                <div class="col mr-auto">
                    <div class="card shadow-lg p-3 mb-5 bg-white rounded " style="max-width: 30rem; margin-top: 20%;">
                        <div class="card-header text-info ">
                            <h4>Login</h4>
                        </div>
                        <div class="card-body text-info">
                            <form action="/login" method="POST">
                                <div class="form-group">

                                    <label>Email</label>
                                    <input type="text" class="form-control" name="username" value="${username}"
                                           />
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control" name="password" value="${password}"
                                           />
                                </div>
                                <div class="form-group text-center text-danger">
                                    ${message}
                                </div>
                                <div>
                                    <button class="btn btn-info btn-block" type="submit" value="Login">Sign in</button>
                                </div>
                                <div style="text-align : center; padding: 2%">
                                    <a href="/forgot-password" class="text-info">Forgot Password</a>
                                </div>

                                <div style="text-align : center " class="text-dark">Not a member?
                                    <a href="/register" class="text-info">Register</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
