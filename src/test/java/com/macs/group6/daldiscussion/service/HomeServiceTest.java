package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.HomeDAOMock;
import com.macs.group6.daldiscussion.model.Post;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.*;

public class HomeServiceTest {

    private HomeDAOMock homeDAOMock;
    private HomeService homeService;

    @Before
    public void setUp() throws Exception {
        homeDAOMock = new HomeDAOMock();
        homeService = (HomeService) ServiceFactory.getInstance().getHomeService(homeDAOMock);
    }

    @Test
    public void getAllPostsTest() {
        Map<String,Object> postMap = new HashMap<>();
        postMap = homeService.getAllPosts();
        assertTrue(postMap.containsKey("3"));

        Post post1 = (Post) postMap.get("1");

        assertEquals(1,post1.getId());
        assertEquals("This is Post 1 title",post1.getPost_title());
        assertEquals("This is post 1 description",post1.getPost_description());
        assertEquals(12,post1.getUpVote());
        assertEquals(10,post1.getDownVote());
        assertTrue(post1.isAlive());
        assertFalse(post1.isReport());
        assertNotNull(post1.getDate());

        Post post2 = (Post)postMap.get("2");
        assertEquals(2,post2.getId());
        assertEquals("This is Post 2 title",post2.getPost_title());
        assertEquals("This is post 2 description",post2.getPost_description());
        assertEquals(121,post2.getUpVote());
        assertEquals(102,post2.getDownVote());
        assertTrue(post2.isAlive());
        assertFalse(post2.isReport());
        assertNotNull(post2.getDate());

        Post post3 = (Post)postMap.get("3");
        assertEquals(3,post3.getId());
        assertEquals("This is new post title",post3.getPost_title());
        assertEquals("This is new post description",post3.getPost_description());
        assertEquals(129,post3.getUpVote());
        assertEquals(12,post3.getDownVote());
        assertTrue(post3.isAlive());
        assertFalse(post3.isReport());
        assertNotNull(post3.getDate());

    }
}