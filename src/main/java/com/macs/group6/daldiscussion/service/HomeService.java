package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.HomeDAO;
import com.macs.group6.daldiscussion.model.Post;

import java.util.List;

public class HomeService implements IHomeService {

    HomeDAO homeDAO = new HomeDAO();

    @Override
    public List<Post> getAllPosts() {
        return homeDAO.getAllPosts();
    }
}
