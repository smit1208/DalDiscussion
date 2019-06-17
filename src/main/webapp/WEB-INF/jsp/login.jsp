<%--
<link href='https://fonts.googleapis.com/css?family=Open+Sans:700,600' rel='stylesheet' type='text/css'>

<form method="post" action="index.html">
    <div class="box">
        <h1>Dashboard</h1>

        <input type="email" name="email" value="email" onFocus="field_focus(this, 'email');" onblur="field_blur(this, 'email');" class="email" />

        <input type="password" name="email" value="email" onFocus="field_focus(this, 'email');" onblur="field_blur(this, 'email');" class="email" />

        <a href="#"><div class="btn">Sign In</div></a> <!-- End Btn -->

        <a href="#"><div id="btn2">Register</div></a> <!-- End Btn2 -->

    </div> <!-- End Box -->

</form>

<p>Forgot your password? <u style="color:#f1c40f;">Click Here!</u></p>

<script src="//ajjquery.min.js" type="text/javascript"></script>ax.googleapis.com/ajax/libs/jquery/1.9.0/--%>


<!DOCTYPE html>
<head>
    <title> Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body style="background-color:#ededed;">
<div style="background-color:#337ab7;height:50px;"></div>
<div class="container-fluid">
    <div class="row col-lg-4 col-lg-offset-4" style="margin-top: 80px;background-color:#fff;padding:20px;border:solid 1px #ddd;">
        <!-- <img th:src="@{/images/login.jpg}" class="img-responsive center-block" width="300" height="300" alt="Logo" /> -->
        <form th:action="@{/login}" method="POST" class="form-signin">
            <h3 class="form-signin-heading" th:text="Login"></h3>
            <label>Login name</label>
            <br /> <input type="text" id="email" name="email" th:placeholder="Email" class="form-control" /> <br />
            <label>Password</label>
            <input type="password" th:placeholder="Password" id="password" name="password" class="form-control" /> <br />

            <div align="center" th:if="${param.error}">
                <p style="font-size: 20; color: #FF1C19;">Email or Password is invalid.</p>
            </div>
            <button class="btn btn-lg btn-primary" name="Submit" value="Login" type="Submit" th:text="Login" style="margin-right:10px;"></button>
            <a href="/recover-password">Forgot password?</a>
        </form>
    </div>
</div>
</body>
</html>