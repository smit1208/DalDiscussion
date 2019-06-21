package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;

import java.sql.Blob;

public interface IPostDao {
    public void create(Post post);
    public void createPostWithImage(Post post, Blob postImageBlob);
}
