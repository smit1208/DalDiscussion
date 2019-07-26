package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;

public interface IPostDAO {
    int create(Post post, int user_id);
    void updatePostModificationDate(int post_id);

}
