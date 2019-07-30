package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.ReportedPost;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IHomeService {
    Map<String,Object> getAllPosts() throws DAOException;
    void addReportingPost(int user_id, int post_id) throws DAOException;
    List<ReportedPost> fetchReportedPostByUserId(int reportedUser_id) throws DAOException;
    List<Post> getSearchedPost(String search) throws DAOException;
    List<Post> getPostsByGroupId(int group_id) throws DAOException;
}
