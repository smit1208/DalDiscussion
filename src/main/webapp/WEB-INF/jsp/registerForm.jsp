<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Registration form</title>

    <script>
        function validateform() {
            var emailReg = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
            var fname = document.forms["myform"]["fname"];
            var lname = document.forms["myform"]["lname"];
            var email = document.forms["myform"]["email"];
            var password = document.forms["myform"]["password"];
            if (fname.value == null || fname.value == "") {
                document.getElementById('firstname').style.borderColor = "red";
                document.getElementById('firstname').innerHTML = "First name is required";
                return false;
            } else {
                document.getElementById('fname').style.borderColor = "green";
            }
            if (lname.value == null || lname.value == "") {
                document.getElementById('lname').style.borderColor = "red";
                document.getElementById('lastname').innerHTML = "Last name is required";
                return false;
            } else {
                document.getElementById('lname').style.borderColor = "green";
            }
            if (email.value == "") {
                document.getElementById("emailvalue").innerHTML = "Enter the email";
                return false;
            } else if (!emailReg.test(email)) {
                document.getElementById("emailvalue").innerHTML = "enter the valid email";
            }
            if (password.value.length < 6) {
                document.getElementById('passwordvalue').innerHTML = "Password is required";
                return false;
            }
        }
    </script>
    <style type="text/css">
        body{
            background-image: url("https://www.news957.com/wp-content/blogs.dir/sites/5/2015/10/25/dalhousie-sign.jpg");
            background-size: cover;
        }
    </style>
</head>

<body>
<div class="d-flex flex-row-reverse">
    <div class="col-md-5 ">
        <div class="container">
            <div class="row">
                <div class="col mr-auto">
                    <div class="card shadow-lg p-3 mb-5 bg-white rounded " style="max-width: 30rem; margin-top: 20%;">
                        <div class="card-header text-info ">
                            <h4>Register</h4>
                        </div>
                        <div class="card-body text-info">
                            <form action="/register" onsubmit="return validateform()" method="post" name="myform">
                                <div class="form-group">

                                    <label for="fname">First Name: </label>
                                    <input id="fname" class="form-control" maxlength="50" name="fname" type="text"
                                           placeholder="Enter first name"/>
                                    <div id="firstname"></div>
                                </div>
                                <div class="form-group">
                                    <label for="lname">Last Name: </label>
                                    <input id="lname" class="form-control" maxlength="50" name="lname" type="text"
                                           placeholder="Enter last name"/>
                                    <div id="lastname"></div>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email_Address:</label>
                                    <input id="email" maxlength="50" class="form-control" name="email" type="email"
                                           placeholder="Enter email address"/>
                                    <div id="emailvalue"></div>
                                    <span style="color: red;" colspan="2">${message}</span>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <input id="password" class="form-control" maxlength="50" name="password"
                                           type="password"/>
                                    <div id="passwordvalue"></div>
                                </div>
                                <div>
                                    <button class="btn btn-info btn-block" type="submit">Register</button>
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
    </div>
</div>
</body>
</html>