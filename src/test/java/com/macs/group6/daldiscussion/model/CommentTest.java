package com.macs.group6.daldiscussion.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommentTest {
    private static Comment comment;

    @Before
    public void setUp() throws Exception {
        comment = new Comment();
    }

    @Test
    public void getReplies() {
    }

    @Test
    public void getId() {
        comment.setId(2);
        assertEquals(2,comment.getId());
    }
    @Test
    public void getComment_description() {
        comment.setComment_description("This is comment test demo");
        assertEquals("This is comment test demo",comment.getComment_description());
    }

    @Test
    public void getCommentUp() {
        comment.setCommentUp(1234);
        assertEquals(1234,comment.getCommentUp());
    }

    @Test
    public void getCommentDown() {
        comment.setCommentDown(222);
        assertEquals(222,comment.getCommentDown());
    }

}