package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;

public interface IPostDAO {
    public void create(Post post, int user_id);
    public int createPostWithImage(Post post, int user_id);

}
