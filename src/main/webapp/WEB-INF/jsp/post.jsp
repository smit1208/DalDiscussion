<%--
  Created by
  User: Sharon Alva
  Date: 2019-06-05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Title</title>
</head>
<body>
<div style="text-align: right"> <span>Welcome User</span></div>

<div style="text-align: center">
    <form class="example" action="">
        <input type="text" placeholder="Search.." name="search">
        <button type="submit"><i class="fa fa-search"></i></button>
    </form>
</div>
<div style="text-align: right; margin-right: 50px">
    <button type="button" style="color: white;background-color: red; ">Post to private group</button>
</div>
<div>
    <form method="post" action ="/savePost">
        Add a post<br>
        <input type="text" name="postTitle"><br>
        Add a post<br>
        <input type="text" name="postDesc"><br>
        <h2>Post title : ${postTitle}</h2>
        <h2>Post title : ${postDesc}</h2>
        <input style="color: white; background-color: darkcyan" type="submit" value="POST">
    </form>
</div>
</body>
</html>
