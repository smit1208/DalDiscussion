package com.macs.group6.daldiscussion.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PostTest {
    Post post = new Post();
    List<Comment> commentList = new ArrayList<>();
    Comment comment = new Comment();

    @Test
    public void getComments() {
    }


    @Test
    public void getId() {
        post.setId(1);
        assertEquals(1,post.getId());
    }


    @Test
    public void getPost_title() {
        post.setPost_title("This is First Post title");
        assertEquals("This is First Post title",post.getPost_title());
    }

    @Test
    public void getPost_description() {
        post.setPost_description("This is First Post desc");
        assertEquals("This is First Post desc",post.getPost_description());
    }


    @Test
    public void getDate() {
        post.setDate(new Date());
        assertNotNull(post.getDate());
    }

    @Test
    public void isAlive() {
        post.setAlive(true);
        assertEquals(true,post.isAlive());
    }


    @Test
    public void isReport() {
       post.setReport(false);
       assertEquals(false,post.isReport());
    }

    @Test
    public void getUpVote() {
        post.setUpVote(4);
        assertEquals(4,post.getUpVote());
    }
    @Test
    public void getDownVote() {
        post.setDownVote(5);
        assertEquals(5,post.getDownVote());
    }

}