<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="../css/sb-admin.css" rel="stylesheet">
<script type="text/javascript">


function validateform(){
    var emailReg = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
    var fname=document.forms["myform"]["fname"];
    var lname=document.forms["myform"]["lname"];
    var email=document.forms["myform"]["email"];
    var password=document.forms["myform"]["password"];
    var confirmpassword=document.forms["myform"]["confirmPassword"];
	var status=true;
    
    if (fname.value == null || fname.value == "") {
        document.getElementById('firstname').style.borderColor = "red";
        document.getElementById('firstname').innerHTML = "First name is required";
        status=false;
    }
    else{
        document.getElementById('firstname').style.borderColor = "green";
    }
    if (lname.value == null || lname.value == ""){
        document.getElementById('lname').style.borderColor = "red";
        document.getElementById('lastname').innerHTML = "Last name is required";
        status=false;
        
    }
    else{
        document.getElementById('lname').style.borderColor = "green";
    }

    if (email.value == ""){
        document.getElementById("emailvalue").innerHTML = "Enter the email";
        status=false;
    }
	
    if(password.value!="")
    {
    	if(password.value.length<6){
        	document.getElementById('passwordvalue').innerHTML = "Password must contain atleast 6 characters";
        	status=false;
    	}
    	
    	if(confirmpassword.value != password.value){
        	document.getElementById('confirmpasswordvalue').innerHTML = "ConfirmPassword do not match password";
        	status=false;
    	}
    }
    
	
	
	return status;
}

function showEditForm(){
	$('#info').hide();
	$('#editform').show();
}

function hideEditForm(){
	$('#info').show();
	$('#editform').hide();
}


</script>
</head>

<body id="page-top">

<%--https://startbootstrap.com/templates/sb-admin/--%>
<jsp:include page="navbar.jsp"></jsp:include>

<div id="wrapper">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div id="content-wrapper">

        <div class="container-fluid">

            <h3 style="text-align: center">Update profile</h3>

            <div class="container" id="editform" style="text-align: center; margin-top: 3%; display:none;">
            <form id="myForm" action="/updateprofile"  onsubmit="return validateform()" method="post" name="myform">
         
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-12">
                <div class="form-label-group">
                 ${message}                 
                </div>
              </div>
              
            </div>
          </div>
         
         <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
             
                  <input type="text" id="fname" name ="fname" class="form-control" placeholder="First name"  autofocus="autofocus" value="${firstName}">
                  <div id = "firstname"></div>
              
              </div>
              <div class="col-md-6">
               
                  <input type="text" id="lname" name="lname" class="form-control" placeholder="Last name" value="${lastName}">
                  <div id = "lastname"></div>
                </div>
            
            </div>
          </div>
      
      
      <div class="form-group">
            
              <input type="email" id="email" name="email" class="form-control" placeholder="Email address" value="${email}">
              <div id = "emailvalue"></div>
          
      </div>
      
	<div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
               
                  <input type="password" id="password" name="password" class="form-control" placeholder="Enter Password (leave blank if dont want to update)" >
                  <div id = "passwordvalue"></div>
              </div>
              <div class="col-md-6">
                
                  <input type="password" id="confirmPassword" name="confirmpassword" class="form-control" placeholder="Confirm password">
                 <div id = "confirmpasswordvalue"></div>
              </div>
            </div>
          </div>

 <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
             
                 <input name="Submit" class="btn btn-primary btn-block"  type="Submit" value="Update Profile" />
              
              </div>
              <div class="col-md-6">
               
                 <input name="Cancel" class="btn btn-danger btn-block"  type="button" value="Cancel Update" onclick="hideEditForm()" />
                </div>
            
            </div>
          </div>

		
		
       
 </form>



      </div>
      
      <div class="container" id="info" style="display:block;">
 
 <div class="form-group text-right">
  <input type="button" class="btn btn-primary" value="Edit Profile" onclick="showEditForm()"/>
   </div>
   <div class="form-group text-center">
    ${message} 
   </div>
   
    <p style="text-align: center;"><strong>My Profile</strong></p>
	<p style="padding-left: 30%; text-align: left;"><strong>Contact Information</strong></p>
	<p style="padding-left: 34%; text-align: left;">Email Address: ${email}</p>
	<p style="text-align: center;">&nbsp;</p>
	<p style="padding-left: 30%; text-align: left;"><strong>General Information:</strong></p>
	<p style="padding-left: 34%; text-align: left;">First Name: ${firstName}</p>
	<p style="padding-left: 34%; text-align: left;">Last Name: ${lastName}</p>
	<p style="padding-left: 34%; text-align: left;">Karma Points: ${karma}</p>
	<p style="padding-left: 30%; text-align: left;"><strong>Additional Information:</strong></p>
	
    
    <c:if test="${not empty groups}">
		<p style="padding-left: 35%;text-align: left;">Subscribed topics</p>
		
			<c:forEach var="listValue" items="${groups}">
				<p style="padding-left: 40%;text-align: left;">${listValue}</p>
				
			</c:forEach>
		

	</c:if>   
    
    
    
    

</div>

        </div>
</div>




<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="../js/sb-admin.min.js"></script>

</div>

</body>

</html>
