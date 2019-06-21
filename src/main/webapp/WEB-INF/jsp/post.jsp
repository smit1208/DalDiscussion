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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Title</title>
    <style>
        .post-title{
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }
    </style>
</head>
<body>

<div style="text-align: right"> </div>
<section id ="header" class = "section-padding" style="padding-top: 20px">
    <div class="container">
        <div style="text-align: right">
            <span>Welcome User</span>
        </div>

    </div>
</section>
<section id ="search-box" class = "section-padding">
<div class="container">
    <form class="search" style = " text-align: center;" action="">
        <input type="text" placeholder="Search.." name="search">
        <button type="submit"><i class="fa fa-search"></i></button>
    </form>
</div>
</section>
<section id ="add-post" class = "section-padding">
    <div class="container">

        <div>
            <form method="post" action ="/savePost" enctype="multipart/form-data">
                <span>Post Title</span><br>
                <input type="text" name="postTitle" class="post-title" required><br>
                <span>Post Description</span><br>

                <textarea rows="4" cols="50" name="postDesc"required> </textarea><br>
                <span>Category</span><br>
                <select name="category">
                    <option value="1">Discussion</option>
                    <option value="2">Poll</option>
                </select><br>
                <span>Group</span><br>
                <select name="group">
                    <option value="General" selected >General Discussion</option>
                    <option value="Group 2" disabled>Group 1</option>
                    <option value="Group 2" disabled>Group 2</option>
                </select><br>
                <span>Add an image</span><br>
                <input type="file" name="image" accept="image/*">
                <input style="color: white; background-color: darkcyan" type="submit" value="POST">
            </form>
        </div>
    </div>
</section>
<div style="text-align: center">

</div>


</body>
</html>
