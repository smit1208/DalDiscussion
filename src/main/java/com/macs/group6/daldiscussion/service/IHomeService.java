package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.model.Post;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface IHomeService {
    List<Post> getAllPosts();
}
