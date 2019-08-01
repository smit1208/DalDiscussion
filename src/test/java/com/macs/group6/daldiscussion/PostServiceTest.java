package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.ValidationTest.AmazonClientMock;
import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.dao.IPostImageDAO;
import com.macs.group6.daldiscussion.dao.IReplyDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;
import com.macs.group6.daldiscussion.model.Reply;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
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
    private AmazonClientMock amazonClientMock;

    @Before
    public void setUp() throws Exception {
        commentDAOMock = new CommentDAOMock();
        replyDAOMock = new ReplyDAOMock();
        postDAOMock = new PostDAOMock();
        postImageDAOMock = new PostImageDAOMock();
        amazonClientMock = new AmazonClientMock();
    }

    @After
    public void tearDown() throws Exception{
        commentDAOMock = null;
        replyDAOMock = null;
        postDAOMock = null;
        postImageDAOMock = null;
        amazonClientMock = null;
    }

    @Test
    public void getComments() throws DAOException {
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
    public void getRepliesTest() throws DAOException {
        List<Reply> replyList = new ArrayList<>();
        for (Reply reply : replyList) {
            reply = (Reply) replyDAOMock.getReplies(1);
            assertEquals(1, reply.getId());
            assertEquals("Testing for replies", reply.getReply_description());
        }

    }

    @Test
    public void getImagesTest() throws DAOException {
        List<PostImage> images = new ArrayList<>();
        images = postImageDAOMock.getImageByPostId(1);
        for (PostImage image : images) {
            assertEquals(1, image.getId());
            assertEquals("https://daldiscussion.s3.ca-central-1.amazonaws.com/dev/1/image1", image.getImageLink());
        }
    }
    @Test
    public void getPostByIdTest() throws DAOException {
        Post post = commentDAOMock.getPostById(1);
        assertEquals(1,post.getId());
    }

    @Test
    public void createPostTest() throws DAOException {
        Post postMock = new Post();
        postMock.setPost_title("Which is the best book for java");
        postMock.setPost_description("I am a beginner. Any suggestions are helpful");
        postMock.setUser_id(1);
        postMock.setCategory(0);
        postMock.setGroup(5);
        postMock.setIsImage(0);
        postMock.setReport(0);

    assertEquals(1, postDAOMock.createPost(postMock));

    }

    @Test
    public void createPostWithImage() throws DAOException {
        Post postMock = new Post();
        postMock.setPost_title("Which is the best book for java");
        postMock.setPost_description("I am a beginner. Any suggestions are helpful");
        postMock.setUser_id(1);
        postMock.setCategory(0);
        postMock.setGroup(5);
        postMock.setIsImage(0);
        postMock.setReport(0);

        assertEquals(1, postDAOMock.createPost(postMock));

    }

}