package com.macs.group6.daldiscussion.service.impl;

import com.macs.group6.daldiscussion.dao.PostDao;
import com.macs.group6.daldiscussion.dao.impl.PostDaoImpl;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService {

    PostDao postDao = new PostDaoImpl();

    @Override
    public void create(Post post) {
        postDao.create(post);
    }
}
