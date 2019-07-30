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
    void create(Post post) throws DAOException;

    void createPostWithImage(Post post, MultipartFile file,int user_id) throws DAOException;

    Map<String,Object> getComments(int postId) throws DAOException;

    List<Reply> getReplies(int commentId) throws DAOException;

    Post getPostById(int postId) throws DAOException;

    void addComment(Comment c, int post_id, int user_id,String name) throws DAOException;

    void addReply(Reply reply, int comment_id, int user_id, String name, int post_id) throws DAOException;

    boolean fileSizeExceeded(MultipartFile file);

    List<String> uploadImageToCloud(MultipartFile files, int post_id) throws DAOException;

    void saveImagetoDB(List<String> imageLinks, int post_id) throws DAOException;

    List<PostImage> getImageByPostId(int post_id) throws DAOException;

    void updatePostMoificationDate(int post_id) throws DAOException;

    void updatePostStatus() throws DAOException;

    List<Post> getAllActivePosts() throws DAOException;
}