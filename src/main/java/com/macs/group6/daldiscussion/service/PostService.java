package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.*;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PostService implements IPostService {

    private static IPostService iPostService;
    private IReplyDAO iReplyDAO;
    private ICommentDAO iCommentDAO;
    private IPostDAO iPostDAO;
    public static IPostService getInstance(IPostDAO iPostDAO, ICommentDAO iCommentDAO, IReplyDAO iReplyDAO){
        if(iPostService == null){
            iPostService = new PostService(iPostDAO,iCommentDAO,iReplyDAO);
        }
        return iPostService;
    }
    private PostService(IPostDAO iPostDAO, ICommentDAO iCommentDAO, IReplyDAO iReplyDAO){
        this.iCommentDAO = iCommentDAO;
        this.iPostDAO = iPostDAO;
        this.iReplyDAO = iReplyDAO;
    }
    @Override
    public void create(Post post) {
        iPostDAO.create(post);
    }

    @Override
    public void createPostWithImage(Post post, MultipartFile file) {
        byte[] imageBytes;

        try{
            imageBytes = file.getBytes();
            Blob postImageBlob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
            iPostDAO.createPostWithImage(post, postImageBlob);
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> getComments(int postId) {
        return iCommentDAO.getComments(postId);
    }

    @Override
    public List<Reply> getReplies(int commentId) {
        return iReplyDAO.getReplies(commentId);
    }

    @Override
    public Post getPostById(int postId) {
        return iCommentDAO.getPostById(postId);
    }

    @Override
    public void addComment(Comment c, int post_id) {
        iCommentDAO.addComment(c,post_id);
    }

    @Override
    public void addReply(Reply reply, int comment_id) {
        iReplyDAO.addReply(reply,comment_id);
    }
}
