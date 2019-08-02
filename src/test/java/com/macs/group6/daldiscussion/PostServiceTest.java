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
import com.macs.group6.daldiscussion.service.AmazonClient;
import com.macs.group6.daldiscussion.service.IPostService;
import com.macs.group6.daldiscussion.service.PostService;
import javafx.geometry.Pos;
import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class PostServiceTest {

    private IPostDAO postDAOMock;
    private IPostService postService;

    @Before
    public void setUp() throws Exception {
        postDAOMock = new PostDAOMock();
        postService = new PostService();
    }


    @After
    public void tearDown() {
        postDAOMock = null;
    }
    @Test
    public void getPostByIdTest() throws DAOException {
        Post post = postDAOMock.getPostById(1);
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
        postMock.setIsImage(1);
        postMock.setReport(0);
        assertEquals(1, postDAOMock.createPost(postMock));

    }

    @Test
    public void getAllActivePosts() throws  DAOException{
        List<Post> activePosts = postDAOMock.getAllActivePosts();

        assertEquals(2,activePosts.size());

        Post post1 = activePosts.get(0);
        assertEquals(1,post1.getId());
        assertEquals("First Post",post1.getPost_title());
        assertEquals("First Post description",post1.getPost_description());
        assertEquals(1,post1.getIsAlive());

        Post post2 = activePosts.get(1);
        assertEquals(2,post2.getId());
        assertEquals("Second Post",post2.getPost_title());
        assertEquals("Second Post description",post2.getPost_description());
        assertEquals(1,post2.getIsAlive());

    }

    /* This test checks that the
     Posts that have been last modified 3 days before current date are marked as inactive
     */
    @Test
    public void  getInactivePostsLastUpdateIsDaysBefore(){
        List<Post> postList = new ArrayList<>();

        Post post = new Post();
        post.setId(1);
        post.setCreationDate(DateUtils.addDays(new Date(), -4));
        post.setLastModificationDate(DateUtils.addDays(new Date(), -3));
        postList.add(post);

        List<Post> inactivePosts = postService.getInactivePosts(postList);
        assertEquals(1,inactivePosts.size());
    }

    /*Test to check that the post that has been last modified on current date
            is not marked as inactive*/
    @Test
    public void  getInactivePostsLastUpdateIsCurrentDte(){
        List<Post> postList = new ArrayList<>();

        Post post = new Post();
        post.setId(1);
        post.setCreationDate(DateUtils.addDays(new Date(), -4));
        post.setLastModificationDate(new Date());
        postList.add(post);

        List<Post> inactivePosts = postService.getInactivePosts(postList);
        assertEquals(0,inactivePosts.size());
    }
}
