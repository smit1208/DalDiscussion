package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IHomeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("HomeService")
public class HomeService implements IHomeService {

    private static IHomeService iHomeService;
    private IHomeDAO homeDAO;

    @Autowired
    public HomeService(@Qualifier("HomeDAO") IHomeDAO homeDAO){
        this.homeDAO = homeDAO;
    }

    public static IHomeService getInstance(IHomeDAO iHomeDAO){
        if(iHomeService == null){
            iHomeService = new HomeService(iHomeDAO);
        }
        return iHomeService;
    }

    @Override
    public Map<String, Object> getAllPosts() {
        return homeDAO.getAllPosts();
    }
}
