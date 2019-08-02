package com.macs.group6.daldiscussion.service;
/**
 * @author Sharon Alva
 * 
 */

import com.macs.group6.daldiscussion.dao.PostDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

public class CheckPostStatus implements Runnable{
    private static final Logger logger = Logger.getLogger(CheckPostStatus.class);

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

    /*Deactives all posts that have no activity for a specified period*/
    @Override
    public void run() {
        try {
            iPostService.updatePostStatus();
        } catch (DAOException e) {
            logger.error(e.getMessage(), e.getCause());
        }
    }
}
