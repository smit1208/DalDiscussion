package com.macs.group6.daldiscussion;
import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.model.Post;

public  class PostDAOMock implements IPostDAO {

    @Override
    public int create(Post post, int user_id) {
        return 0;
    }
    @Override
    public void updatePostModificationDate(int post_id) {

    }

}
