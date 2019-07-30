package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IDashboardService {
    Map<String,Object> getPostsByUserID(int user_id) throws DAOException;
    void deletePostById(int post_id) throws DAOException;
    public void updatePostById(String post_title, String post_description, int id) throws DAOException;
}
