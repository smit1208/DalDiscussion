package com.macs.group6.daldiscussion.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IDashboardService {
    Map<String,Object> getPostsByUserID(int user_id);
    void deletePostById(int post_id);
}
