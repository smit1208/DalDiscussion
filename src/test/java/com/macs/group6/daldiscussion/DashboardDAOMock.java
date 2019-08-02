package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IDashboardDAO;
import com.macs.group6.daldiscussion.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vivek Shah
 */

public class DashboardDAOMock implements IDashboardDAO {
    private Map<String,Object> personalPostMap;

    public DashboardDAOMock(){
        personalPostMap = new HashMap<>();
    }

    List<Post> personalPosts = new ArrayList<>();

    public void personalPosts(){


    }

    @Override
    public Map<String, Object> getPostsByUserID(int user_id) {
        Post personalPostMock = new Post();
        personalPostMock.setUser_id(1);
        personalPostMock.setId(1);
        personalPostMock.setPost_title("TestPost1");
        personalPostMock.setPost_description("TestDescription1");
        personalPostMap.put("1",personalPostMock);


        Post personalPostMock1 = new Post();
        personalPostMock1.setId(2);
        personalPostMock1.setUser_id(2);
        personalPostMock1.setPost_title("TestPost2");
        personalPostMock1.setPost_description("TestDescription2");

        personalPostMap.put("2",personalPostMock1);

        return personalPostMap;
    }

    @Override
    public void deletePostById(int post_id){
    }

    @Override
    public void updatePostById(String post_title, String post_description, int id) {
    }
}
