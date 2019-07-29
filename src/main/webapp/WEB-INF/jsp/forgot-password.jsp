<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password | DAL Discussion</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">
    <%@include file="../css/forgotpwd.css"%>
</style>
</head>
<body>
        <div class="container">
            <div class="row">
                <div class="col-md-7">
                    <div class="vr">&nbsp;</div>
                    <div id="forgotpwd">
                        <h2>Forgot Your Password??</h2>
                        <br>
                        <p>No Problem, <br>
                            You are just an email away from your new password</p>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="card shadow-lg p-3 mb-5 bg-white rounded " style="max-width: 30rem; margin-top: 20%;">
                        <div class="card-body text-info">
                            <form action="/forgot-password" method="POST">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input type="text" class="form-control" name="email" value="${email}"/>
                                </div>
                                <div class="form-group text-center text-danger">
                                    ${message}
                                </div>
                                <div class="form-group text-center">
                                    <button class="btn btn-info btn-block" type="submit" value="Reset Password">Reset Password</button>
                                </div>
                                <div style="text-align : center; padding: 2%">
                                    <a href="/login" class="text-info">Login</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
