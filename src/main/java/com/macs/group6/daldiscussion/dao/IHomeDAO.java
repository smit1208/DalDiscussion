package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.ReportedPost;

import java.util.List;
import java.util.Map;

public interface IHomeDAO {
    Map<String,Object> getAllPosts();
    void addReportingPost(int user_id, int post_id);
    List<ReportedPost> fetchReportedPostByUserId(int reportedUser_id);
}
