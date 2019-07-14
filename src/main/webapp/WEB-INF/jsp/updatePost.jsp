<%--
  Created by IntelliJ IDEA.
  User: Vivek Shah
  Date: 2019-07-10
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dal Discussion</title>
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <link href="../css/sb-admin.css" rel="stylesheet">


</head>

<body id="page-top">

<%--https://startbootstrap.com/templates/sb-admin/--%>
<jsp:include page="navbar.jsp"></jsp:include>

<div id="wrapper">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div id="content-wrapper">


        <form method="post" action ="/dashboard/updatePost/update">
            <div class="form-group">
                <div class="form-row">
                    <div class="col-md-6">

                        <input type="text" id="post_title" name ="post_title" class="form-control" placeholder="Post Title"  autofocus="autofocus" >
                        <div id = "posttitle"></div>

                    </div>
                    <div class="col-md-6">

                        <input type="text" id="post_desc" name="post_desc" class="form-control" placeholder="Post Description" >
                        <div id = "postdesc"></div>
                    </div>

                        <button class="btn btn-danger" type="submit">Update</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
