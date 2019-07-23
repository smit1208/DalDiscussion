<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Registration form</title>

    <script>
        function validateform(){
            var emailReg = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
            var fname=document.forms["myform"]["fname"];
            var lname=document.forms["myform"]["lname"];
            var email=document.forms["myform"]["email"];
            var password=document.forms["myform"]["password"];

            if (fname.value == null || fname.value == "") {
                document.getElementById('firstname').style.borderColor = "red";
                document.getElementById('firstname').innerHTML = "First name is required";
                return false;
            }
            else{
                document.getElementById('fname').style.borderColor = "green";
            }
            if (lname.value == null || lname.value == ""){
                document.getElementById('lname').style.borderColor = "red";
                document.getElementById('lastname').innerHTML = "Last name is required";
                return false;
            }
            else{
                document.getElementById('lname').style.borderColor = "green";
            }

            if (email.value == ""){
                document.getElementById("emailvalue").innerHTML = "Enter the email";
                return false;
            }else if(!emailReg.test(email)){
                document.getElementById("emailvalue").innerHTML = "enter the valid email";
            }

            if(password.value.length<6){
                document.getElementById('passwordvalue').innerHTML = "Password is required";
                return false;
            }
        }

    </script>


</head>

<h2 ALIGN="CENTER">Registration form</h2>

<%--<form action="/register" onsubmit="//return validateform()" method="post" name="myform">--%>
<form:form action="/register " method="post" modelAttribute="registrationForm" name="myform">
    <table border="0" align="center">
        <tbody>

        <tr>
            <spring:bind path="firstName">
                <div class="form-group">
                    <td><label>First Name: </label></td>
                    <td><form:input type="text" path="firstName" name = "firstName" class="form-control"
                                    placeholder="First Name" autofocus="true" ></form:input>

                        <div id = "firstName"></div></td>
                <tr>
            <td><td><form:errors path="firstName"></form:errors></td></td>
                </tr>
                </div>
            </spring:bind>


        <tr>
            <spring:bind path="lastName">
                <div class="form-group">
                    <td><label>Last Name: </label></td>
                    <td><form:input type="text" path="lastName" name = "lastName" class="form-control"
                                    placeholder="Last Name" autofocus="true" ></form:input>

                        <div id = "lastName"></div></td>
                    <tr>
                    <td><td><form:errors path="lastName"></form:errors></td></td>
                    </tr>
                </div>
            </spring:bind>
        </tr>
            <spring:bind path="email">
                <div class="form-group">
                    <td><label>Email: </label></td>
                    <td><form:input type="text" path="email" name = "email" class="form-control"
                                    placeholder="Email" autofocus="true" ></form:input>

                        <div id = "email"></div></td>
                    <tr>
                  <td><td>  <form:errors path="email"></form:errors></td></td>
                    </tr>
                </div>
            </spring:bind>

        <tr>

        </tr>
        <tr><td style="color: red;" colspan="2">${errorMessage}</td></tr>
        <tr>
            <spring:bind path="password">
                <div class="form-group">
                    <td><label>Password: </label></td>
                    <td><form:input type="password" path="password" name = "password" class="form-control"
                                    placeholder="Password" autofocus="true" ></form:input>

                        <div id = "password"></div></td>
                    <tr>
                    <td><td><form:errors path="password"></form:errors></td></td>
                    </tr>
                </div>
            </spring:bind>
        </tr>
        <tr>
            <td align="center"><input name="Submit" type="Submit" value="Submit" /></td>
        </tr>
        </tbody>
    </table>
</form:form>
</html>
