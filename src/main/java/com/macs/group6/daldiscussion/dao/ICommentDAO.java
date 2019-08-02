package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Comment;

import java.util.Map;

/**
 * @author Smit Saraiya
 */
public interface ICommentDAO {
    Map<String,Object> getComments(int postId) throws DAOException;
    void addComment(Comment comment, int post_id, int user_id, String name ) throws DAOException;
}
