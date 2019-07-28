package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.IPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("MyThread")
public class MyThread extends Thread {

    private IPostService iPostService;
    @Autowired
    public MyThread(@Qualifier("PostService")IPostService iPostService){
        this.iPostService = iPostService;
    }
    final long timeInterval = 3600;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyThread.class);
    @Override
    public void run() {
        while (true) {
         Post post=   iPostService.getPostById(160);
            System.out.println(post.getPost_title());
            // ------- code for task to run
            System.out.println("Hello !!");
            // ------- ends here
            try {
                Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
