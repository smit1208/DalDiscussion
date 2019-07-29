package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.model.Post;

import java.util.List;

public class PostDAOMock implements IPostDAO {
    @Override
    public int createPost(Post post, int user_id) {
    return 0;
    }

    @Override
    public List<Post> getAllActivePosts() {
        return null;
    }

    @Override
    public void updatePostModificationDate(int post_id) {

    }

    @Override
    public void updatePostStatus(Post post) {

    }
}
