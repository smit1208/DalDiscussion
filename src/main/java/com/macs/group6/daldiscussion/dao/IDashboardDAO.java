package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.exceptions.DAOException;

import java.util.Map;

public interface IDashboardDAO {
    Map<String,Object> getPostsByUserID(int user_id) throws DAOException;

    void deletePostById(int post_id) throws DAOException;

    void updatePostById(String post_title, String post_description, int id) throws DAOException;
}
