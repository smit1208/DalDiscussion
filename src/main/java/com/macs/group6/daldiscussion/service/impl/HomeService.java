package com.macs.group6.daldiscussion.service.impl;

import com.macs.group6.daldiscussion.dao.DAOFactory;
import com.macs.group6.daldiscussion.dao.impl.HomeDAO;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.IHomeService;

import java.util.List;

public class HomeService implements IHomeService {

    private static IHomeService iHomeService;

    public static IHomeService getInstance(){
        if(iHomeService == null){
            iHomeService = new HomeService();
        }
        return iHomeService;
    }

    HomeDAO homeDAO = (HomeDAO) DAOFactory.getInstance().getHomeDAO();

    @Override
    public List<Post> getAllPosts() {
        return homeDAO.getAllPosts();
    }
}
