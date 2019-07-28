package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.dao.IPostImageDAO;
import com.macs.group6.daldiscussion.dao.IReplyDAO;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;
import com.macs.group6.daldiscussion.model.Reply;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class PostServiceTest {
    private ICommentDAO commentDAOMock;
    private IPostDAO postDAOMock;
    private IReplyDAO replyDAOMock;
    private IPostImageDAO postImageDAOMock;

    @Before
    public void setUp() throws Exception {
        commentDAOMock = new CommentDAOMock();
        replyDAOMock = new ReplyDAOMock();
        postDAOMock = new PostDAOMock();
        postImageDAOMock = new PostImageDAOMock();

    }

    @Test
    public void getComments() {
        Map<String, Object> commentMap = new HashMap<>();
        commentMap = commentDAOMock.getComments(1);


        Comment comment = (Comment) commentMap.get("1");
        assertEquals(1, comment.getId());
        assertEquals("Testing of Comment Service", comment.getComment_description());
        assertEquals(11, comment.getCommentUp());
        assertEquals(10, comment.getCommentDown());

        Comment comment1 = (Comment) commentMap.get("2");
        assertEquals(2, comment1.getId());
        assertEquals("Testing of Comment Service 1", comment1.getComment_description());
        assertEquals(12, comment1.getCommentUp());
        assertEquals(122, comment1.getCommentDown());

    }

    @Test
    public void getRepliesTest() {
        List<Reply> replyList = new ArrayList<>();
        for (Reply reply : replyList) {
            reply = (Reply) replyDAOMock.getReplies(1);
            assertEquals(1, reply.getId());
            assertEquals("Testing for replies", reply.getReply_description());
        }

    }

    @Test
    public void getImagesTest() {
        List<PostImage> images = new ArrayList<>();
        images = postImageDAOMock.getImageByPostId(1);
        for (PostImage image : images) {
            assertEquals(1, image.getId());
            assertEquals("https://daldiscussion.s3.ca-central-1.amazonaws.com/dev/1/image1", image.getImageLink());
        }
    }
    @Test
    public void getPostByIdTest(){
        Post post = commentDAOMock.getPostById(1);
        assertEquals(1,post.getId());
    }

}