package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IHomeDAO;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.ReportedPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("HomeService")
public class HomeService implements IHomeService {

    private IHomeDAO homeDAO;

    @Autowired
    public HomeService(@Qualifier("HomeDAO") IHomeDAO homeDAO){
        this.homeDAO = homeDAO;
    }
    @Override
    public Map<String, Object> getAllPosts() {
        return homeDAO.getAllPosts();
    }

    @Override
    public void addReportingPost(int user_id, int post_id) {
        homeDAO.addReportingPost(user_id,post_id);
    }

    @Override
    public List<ReportedPost> fetchReportedPostByUserId(int reportedUser_id) {
        return homeDAO.fetchReportedPostByUserId(reportedUser_id);
    }

    @Override
    public List<Post> getSearchedPost(String search) {
        return homeDAO.getSearchedPost(search);
    }
}
