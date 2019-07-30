package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IHomeDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;

public class HomeServiceTest {

    private IHomeDAO homeDAOMock;

    @Before
    public void setUp() throws Exception {
        homeDAOMock = new HomeDAOMock();
    }

    @After
    public void tearDown() {
        homeDAOMock =null;
    }

    @Test
    public void getAllPostsTest() throws DAOException {
        Map<String,Object> postMap = new HashMap<>();
        postMap = homeDAOMock.getAllPosts();

        Post post1 = (Post) postMap.get("1");

        assertEquals(1,post1.getId());
        assertEquals("This is Post 1 title",post1.getPost_title());
        assertEquals("This is post 1 description",post1.getPost_description());
        assertEquals(12,post1.getUpVote());
        assertEquals(10,post1.getDownVote());
        assertEquals(1,post1.getIsAlive());
        assertNotNull(post1.getDate());

        Post post2 = (Post)postMap.get("2");
        assertEquals(2,post2.getId());
        assertEquals("This is Post 2 title",post2.getPost_title());
        assertEquals("This is post 2 description",post2.getPost_description());
        assertEquals(121,post2.getUpVote());
        assertEquals(102,post2.getDownVote());
        assertEquals(1,post2.getIsAlive());
        assertNotNull(post2.getDate());

        Post post3 = (Post)postMap.get("3");
        assertEquals(3,post3.getId());
        assertEquals("This is new post title",post3.getPost_title());
        assertEquals("This is new post description",post3.getPost_description());
        assertEquals(129,post3.getUpVote());
        assertEquals(12,post3.getDownVote());
        assertEquals(1,post3.getIsAlive());
        assertNotNull(post3.getDate());

    }

    @Test
    public void getSearchedPostTest() throws DAOException {
        List<Post> postList = new ArrayList<>();
        postList = homeDAOMock.getSearchedPost("This is Post 1 title");
        assertNotNull(postList);
    }

    @Test
    public void getPostByGroupIdTest() throws DAOException {
        List<Post> postList = new ArrayList<>();
        postList = homeDAOMock.getPostsByGroupId(5);
        assertNotNull(postList);
    }

}