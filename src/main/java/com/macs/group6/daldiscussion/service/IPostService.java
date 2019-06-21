package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface IPostService {
    public void create(Post post);
    public void createPostWithImage(Post post, MultipartFile file);

}
