package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.CommentDAO;
import com.macs.group6.daldiscussion.dao.DAOFactory;
import com.macs.group6.daldiscussion.dao.ReplyDAO;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;

import java.util.List;

public class PostService implements IPostService {

    CommentDAO commentDAO = (CommentDAO) DAOFactory.getInstance().getCommentDAO();
    ReplyDAO replyDAO = (ReplyDAO) DAOFactory.getInstance().getReplyDAO();

    private static IPostService iPostService;

    public static IPostService getInstance(){
        if(iPostService == null){
            iPostService = new PostService();
        }
        return iPostService;
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
