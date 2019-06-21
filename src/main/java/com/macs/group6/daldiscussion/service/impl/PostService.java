package com.macs.group6.daldiscussion.service.impl;

import com.macs.group6.daldiscussion.dao.IPostDao;
import com.macs.group6.daldiscussion.dao.impl.PostDao;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.service.IPostService;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class PostService implements IPostService {

    IPostDao IPostDao = new PostDao();

    @Override
    public void create(Post post) {
        IPostDao.create(post);
    }

    @Override
    public void createPostWithImage(Post post, MultipartFile file) {
        byte[] imageBytes;

        try{
            imageBytes = file.getBytes();
            Blob postImageBlob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
            IPostDao.createPostWithImage(post, postImageBlob);
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Comment> getComments(int postId) {
        return null;
    }

    @Override
    public List<Reply> getReplies(int commentId) {
        return null;
    }

    @Override
    public Post getPostById(int postId) {
        return null;
    }

    @Override
    public void addComment(Comment c, int post_id) {

    }

    @Override
    public void addReply(Reply reply, int comment_id) {

    }
}
