package com.macs.group6.daldiscussion.service;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public interface IPostService {
    void createPost(Post post) throws DAOException;

    void createPostWithImage(Post post) throws DAOException;

    Post getPostById(int postId) throws DAOException;

    void updatePostStatus() throws DAOException;

    List<Post> getAllActivePosts() throws DAOException;

    List<Post> getInactivePosts(List<Post> postList);
}