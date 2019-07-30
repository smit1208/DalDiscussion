package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;

import java.util.List;

public class PostDAOMock implements IPostDAO {


    @Override
    public int createPost(Post post) throws DAOException {
        if(post.getPost_title().isEmpty() && post.getPost_description().isEmpty() && post.getIsImage() == 0
        && post.getCategory() == 0 && post.getGroup() == 5 && post.getReport() == 0 && post.getUser_id() == 1){
            return 0;
        }else{
            return 1;
        }

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
