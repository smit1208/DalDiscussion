package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

public class CheckPostStatus implements Runnable{
    private static CheckPostStatus instance;
    private IPostService iPostService;

    public static CheckPostStatus getInstance() {
        if (instance == null) {
            instance = new CheckPostStatus(new PostService());

        }
        return instance;
    }

    @Autowired
    private CheckPostStatus(@Qualifier("PostService") IPostService iPostService) {
        this.iPostService = iPostService;
    }


    @Override
    public void run() {
        iPostService.updatePostStatus();
    }
}
