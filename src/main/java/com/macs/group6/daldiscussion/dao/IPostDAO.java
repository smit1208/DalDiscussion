package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;

import java.util.List;

public interface IPostDAO {
    int createPost(Post post, int user_id);
    List<Post> getAllActivePosts();
    void updatePostModificationDate(int post_id);
    void updatePostStatus(Post post);

}
