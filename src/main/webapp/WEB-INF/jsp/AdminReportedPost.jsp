<%@ page import="java.util.List" %>
<%@ page import="com.macs.group6.daldiscussion.entities.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.macs.group6.daldiscussion.model.Post" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="../css/sb-admin.css" rel="stylesheet">
<html>
<head>

</head>
<body>
<div class="container">
    <table class="table table-striped">
        <%
            List<User> users = new ArrayList<>();
            users = (List<User>) request.getAttribute("userList");
            List<Post> posts = new ArrayList<>();
            posts = (List<Post>) request.getAttribute("postList");
        %>
        <tr>
            <th>User Name</th>
            <th>Post Title</th>
            <th>Report Count</th>
        </tr>

        <%
            for (int i = 0; i < users.size(); i++) {%>
       <tr>
           <td>
               <%=users.get(i).getFirstName()%>
           </td>

           <td>
               <%=posts.get(i).getPost_title()%>
           </td>

           <td>
               <%=posts.get(i).getReport()%>
           </td>
           <td>
               <form action="/admin/reported/<%=users.get(i).getId()%>/<%=posts.get(i).getPost_title()%>" method="get">
                   <button class="btn btn-info " type="submit">Send Mail</button>
               </form>
           </td>
       </tr>
        <% }%>


    </table>
    <div class="d-flex flex-row-reverse">
        <a href="/logout">
            Logout
        </a>
    </div>
</div>
</body>
</html>
