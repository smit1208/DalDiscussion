package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;

import java.sql.Blob;

public interface IPostDAO {
    public void create(Post post, int user_id);
    public void createPostWithImage(Post post, Blob postImageBlob);
}
