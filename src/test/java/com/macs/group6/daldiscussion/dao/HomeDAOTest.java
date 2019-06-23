package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HomeDAOTest {

    private static HomeFactoryMock homeFactoryMock;
    private static List<Comment> commentList;
    private static List<Reply> replyList;


    @Before
    public void setUp() throws Exception {
        homeFactoryMock = new HomeFactoryMock();
        commentList = new ArrayList<>();
        replyList = new ArrayList<>();

    }

    @Test
    public void getAllPosts() {
        Map<String,Object> postMap = new HashMap<>();
        postMap = homeFactoryMock.getAllPosts();


        Post post1 = (Post) postMap.get("1");

        Comment comment1 = new Comment();
        Reply reply1 = new Reply();

        reply1.setId(1);
        reply1.setReply_description("This is reply 1 desc");
        replyList.add(reply1);

        comment1.setId(1);
        comment1.setComment_description("This is Comment description");
        comment1.setCommentUp(11);
        comment1.setCommentDown(10);
        comment1.setReplies(replyList);
        commentList.add(comment1);

        post1.setComments(commentList);
        assertEquals(1,post1.getId());
        assertEquals("This is Post 1 title",post1.getPost_title());
        assertEquals("This is post 1 description",post1.getPost_description());
        assertEquals(12,post1.getUpVote());
        assertEquals(10,post1.getDownVote());
        assertTrue(post1.isAlive());
        assertFalse(post1.isReport());
        assertNotNull(post1.getDate());
        assertEquals(commentList,post1.getComments());

        Post post2 = (Post) postMap.get("2");

        Comment comment2 = new Comment();
        Reply reply2 = new Reply();
        reply2.setId(1);
        reply2.setReply_description("This is reply 2 desc");
        replyList.add(reply2);

        comment2.setId(2);
        comment2.setComment_description("This is Comment 2 description");
        comment2.setCommentUp(100);
        comment2.setCommentDown(20);
        comment2.setReplies(replyList);
        commentList.add(comment2);

        post2.setComments(commentList);
        assertEquals(2,post2.getId());
        assertEquals("This is Post 2 title",post2.getPost_title());
        assertEquals("This is post 2 description",post2.getPost_description());
        assertEquals(121,post2.getUpVote());
        assertEquals(102,post2.getDownVote());
        assertTrue(post2.isAlive());
        assertFalse(post2.isReport());
        assertNotNull(post2.getDate());
        assertEquals(commentList,post2.getComments());

        Post post3 = (Post) postMap.get("3");
        Comment comment3 = new Comment();
        Reply reply3 = new Reply();
        reply3.setId(1);
        reply3.setReply_description("This is reply 3 desc");
        replyList.add(reply3);

        comment3.setId(1);
        comment3.setComment_description("This is Comment 3 description");
        comment3.setCommentUp(111);
        comment3.setCommentDown(220);
        comment3.setReplies(replyList);
        commentList.add(comment3);

        post3.setComments(commentList);
        assertEquals(3,post3.getId());
        assertEquals("This is new post title",post3.getPost_title());
        assertEquals("This is new post description",post3.getPost_description());
        assertEquals(129,post3.getUpVote());
        assertEquals(12,post3.getDownVote());
        assertTrue(post3.isAlive());
        assertFalse(post3.isReport());
        assertNotNull(post3.getDate());
        assertEquals(commentList,post3.getComments());


    }
}