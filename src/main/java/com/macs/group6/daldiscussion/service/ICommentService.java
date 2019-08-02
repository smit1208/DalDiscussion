package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Comment;

import java.util.Map;

/**
 * @author Smit Saraiya
 */
public interface ICommentService {
    Map<String,Object> getComments(int postId) throws DAOException;

    void addComment(Comment c, int post_id, int user_id, String name) throws DAOException;

}
