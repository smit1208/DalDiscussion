package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.ValidationTest.AmazonClientMock;
import com.macs.group6.daldiscussion.dao.IPostImageDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.PostImage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ImageServiceTest {

    private IPostImageDAO postImageDAOMock;
    private AmazonClientMock amazonClientMock;

    @Before
    public void setUp() throws Exception {
        postImageDAOMock = new PostImageDAOMock();
        amazonClientMock = new AmazonClientMock();
    }

    @After
    public void tearDown() {
        postImageDAOMock = null;
        amazonClientMock = null;
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
}
