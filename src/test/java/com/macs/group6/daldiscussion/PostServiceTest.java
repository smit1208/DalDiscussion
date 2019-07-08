package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.service.PostService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class PostServiceTest {
    private CommentDAOMock commentDAOMock;
    private PostDAOMock postDAOMock;
    private ReplyDAOMock replyDAOMock;
    private PostService postService;

    @Before
    public void setUp() throws Exception {
        commentDAOMock = new CommentDAOMock();
        replyDAOMock = new ReplyDAOMock();
        postDAOMock = new PostDAOMock();
        postService = new PostService(commentDAOMock,postDAOMock,replyDAOMock);
    }

    @Test
    public void getComments() {
        Map<String,Object> commentMap = new HashMap<>();
        commentMap = postService.getComments(1);


        Comment comment = (Comment) commentMap.get("1");
        assertEquals(1,comment.getId());
        assertEquals("Testing of Comment Service",comment.getComment_description());
        assertEquals(11,comment.getCommentUp());
        assertEquals(10,comment.getCommentDown());

        Comment comment1 = (Comment)commentMap.get("2");
        assertEquals(2,comment1.getId());
        assertEquals("Testing of Comment Service 1",comment1.getComment_description());
        assertEquals(12,comment1.getCommentUp());
        assertEquals(122,comment1.getCommentDown());

    }

    @Test
    public void getReplies(){
        List<Reply> replyList = new ArrayList<>();
        for(Reply reply: replyList){
            reply = (Reply) postService.getReplies(1);
            assertEquals(1,reply.getId());
            assertEquals("Testing for replies",reply.getReply_description());
        }

    }

    public static class SubscriptionServiceTest {

        @Test
        public void getAllSubscriptions() {
        }

        @Test
        public void addSubscriptionRequest() {
        }

        @Test
        public void fetchSubscriptionByUserID() {
        }

        @Test
        public void approvedSubscriptions() {
        }

        @Test
        public void fetchSubscriptionByID() {
        }
    }
}