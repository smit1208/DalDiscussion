package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.CommentDAO;
import com.macs.group6.daldiscussion.dao.DAOFactory;
import com.macs.group6.daldiscussion.dao.IPostDao;
import com.macs.group6.daldiscussion.dao.ReplyDAO;
import com.macs.group6.daldiscussion.dao.impl.PostDao;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class PostService implements IPostService {

    CommentDAO commentDAO = (CommentDAO) DAOFactory.getInstance().getCommentDAO();
    ReplyDAO replyDAO = (ReplyDAO) DAOFactory.getInstance().getReplyDAO();
    com.macs.group6.daldiscussion.dao.IPostDao IPostDao = new PostDao();
    private static IPostService iPostService;

    public static IPostService getInstance(){
        if(iPostService == null){
            iPostService = new PostService();
        }
        return iPostService;
    }

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
        return commentDAO.getComments(postId);
    }

    @Override
    public List<Reply> getReplies(int commentId) {
        return replyDAO.getReplies(commentId);
    }

    @Override
    public Post getPostById(int postId) {
        return commentDAO.getPostById(postId);
    }

    @Override
    public void addComment(Comment c, int post_id) {
        commentDAO.addComment(c,post_id);
    }

    @Override
    public void addReply(Reply reply, int comment_id) {
        replyDAO.addReply(reply,comment_id);
    }
}
