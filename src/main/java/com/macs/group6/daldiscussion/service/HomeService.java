package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IHomeDAO;

import java.util.Map;

public class HomeService implements IHomeService {

    private static IHomeService iHomeService;
    private IHomeDAO homeDAO;
    public static IHomeService getInstance(IHomeDAO iHomeDAO){
        if(iHomeService == null){
            iHomeService = new HomeService(iHomeDAO);
        }
        return iHomeService;
    }
    private HomeService(IHomeDAO homeDAO){
        this.homeDAO = homeDAO;
    }
    @Override
    public Map<String, Object> getAllPosts() {
        return homeDAO.getAllPosts();
    }
}
