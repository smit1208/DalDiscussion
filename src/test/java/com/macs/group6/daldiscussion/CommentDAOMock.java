package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.dao.IPostImageDAO;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDAOMock implements ICommentDAO {
   private Map<String,Object> commentMap;
   Post post = new Post();


    public CommentDAOMock(){
        commentMap = new HashMap<>();
        comments();
    }

    private void comments(){
        Comment comment = new Comment();

        comment.setId(1);
        comment.setComment_description("Testing of Comment Service");
        comment.setCommentUp(11);
        comment.setCommentDown(10);

        Comment comment1 = new Comment();
        comment1.setId(2);
        comment1.setComment_description("Testing of Comment Service 1");
        comment1.setCommentUp(12);
        comment1.setCommentDown(122);

        commentMap.put("1",comment);
        commentMap.put("2",comment1);

    }


    @Override
    public Map<String, Object> getComments(int postId) {
        return commentMap;
    }

    @Override
    public Post getPostById(int postId) {
        return post;
    }

    @Override
    public void addComment(Comment comment, int post_id, int user_id) {

    }

}
