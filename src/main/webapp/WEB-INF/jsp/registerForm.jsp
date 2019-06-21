<html>
<head>
    <title>Registration form</title>
</head>

<h2 ALIGN="CENTER">Registration form</h2>

<form action="/user/details" method="post" name="myform">
    <table border="0" align="center">
        <tbody>

        <tr>
            <td><label for="fname">First Name: </label></td>
            <td><input id="fname" maxlength="50" name="fname" type="text" required/></td>
        </tr>

        <tr>
            <td><label for="lname">Last Name: </label></td>
            <td><input id="lname" maxlength="50" name="lname" type="text" required/></td>
        </tr>


        <tr>
            <td><label for="email">Email_Address:</label></td>
            <td><input id="email" maxlength="50" name="email" type="text" required/></td>
        </tr>

        <tr>
            <td><label for="password">Password:</label></td>
            <td><input id="password" maxlength="50" name="password"
                       type="password" /></td>
        </tr>

        <tr>
            <td align="right"><input name="Submit" type="Submit" value="Submit" /></td>
        </tr>

        </tbody>
    </table>
</form>

<script>
    function validateform(){
        var fname=document.myform.fname.value;
        var lname=document.myform.lname.value;
        var password=document.myform.password.value;

        if (fname==null || fname=="") {
            alert(" First name can't be blank");
            return false;
        }
        else if (lname==null || lname==""){
                alert(" Last name can't be blank");
                return false;
        }else if(password.length<6){
            alert("Password must be at least 6 characters long.");
            return false;
        }
    }
</script>




</html>




<%--
!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{fragments/main_layout}">
<head>
    <title>Register Form</title>
</head>
<body>

<div layout:fragment="content" class="container mySpace">
    <div class="alert alert-info" th:if="${exist}">
        <p class="text text-center">
            User with this Email already Exist
        </p>
    </div>
    <form th:action="@{/register}" th:object="${user}" method="post">

        <div class="form-group">

            <label for="firstname" class="form-control-label">firstname</label> <input
                type="text" class="form-control" th:field="*{email}" id="firstname" />
            <div class="text text-danger" th:if="${#fields.hasErrors('email')}"
                 th:errors="*{email}"></div>
        </div>

        <div class="form-group">

            <label for="lastname" class="form-control-label">firstname</label> <input
                type="text" class="form-control" th:field="*{email}" id="lastname" />
            <div class="text text-danger" th:if="${#fields.hasErrors('email')}"
                 th:errors="*{email}"></div>
        </div>

        <div class="form-group">

            <label for="email" class="form-control-label">Email</label> <input
                type="text" class="form-control" th:field="*{email}" id="email" />
            <div class="text text-danger" th:if="${#fields.hasErrors('email')}"
                 th:errors="*{email}"></div>
        </div>
        <div class="form-group">
            <label for="password" class="form-control-label">Password</label> <input
                type="password" class="form-control" th:field="*{password}"
                id="password" />
            <div class="text text-danger"
                 th:if="${#fields.hasErrors('password')}" th:errors="*{password}"></div>

        </div>
        <div class="form-group">
            <label for="name" class="form-control-label">name</label> <input
                type="text" class="form-control" th:field="*{name}" id="name" />
            <div class="text text-danger" th:if="${#fields.hasErrors('name')}"
                 th:errors="*{name}"></div>

        </div>
        <input type="submit" value="Submit" class="btn btn-primary" />

    </form>
</div>
</body>
</html>--%>
