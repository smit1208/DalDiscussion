package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.model.Post;
import org.springframework.stereotype.Service;

@Service("postService")
public interface PostService {
    public void create(Post post);
}
