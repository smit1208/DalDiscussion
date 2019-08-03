package com.macs.group6.daldiscussion.dao;
/*
@author Sharon Alva
*/
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;

import java.sql.SQLException;
import java.util.List;

public interface IPostDAO {
    int createPost(Post post) throws DAOException;
    List<Post> getAllActivePosts() throws DAOException;
    void updatePostStatus(Post post) throws DAOException;
    Post getPostById(int postId) throws DAOException;
}
