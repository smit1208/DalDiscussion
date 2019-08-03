package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.AppConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.model.Comment;
import com.sun.org.apache.bcel.internal.generic.IUSHR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Smit Saraiya
 */
public class CommentService implements ICommentService {
    private static int commentSize;
    private static int karmaPoints = 100;
    private IDAOFactory idaoFactory;

    public CommentService(){
        idaoFactory = new DAOFactory();
    }

    @Override
    public Map<String, Object> getComments(int postId) throws DAOException {
        return idaoFactory.createCommentDAO().getComments(postId);
    }

    @Override
    public void addComment(Comment c, int post_id, int user_id,String name) throws DAOException {
        idaoFactory.createCommentDAO().addComment(c,post_id,user_id,name);
        commentSize = getCommentSize(post_id);
        int limit = AppConfig.getInstance().getPostCommentSize();
        if(isLimitReached(commentSize,limit)){
            UserService.getInstance().updateUserKarmaPoints(karmaPoints, post_id);
        }
    }

    private int getCommentSize(int post_id) throws DAOException {
        Map<String,Object> commentMap = new HashMap<>();
        commentMap = idaoFactory.createCommentDAO().getComments(post_id);
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");
        return commentList.size();
    }

    private boolean isLimitReached(int commentSize, int limit){
        if(commentSize == limit){
            return true;
        }
        return false;
    }
}
