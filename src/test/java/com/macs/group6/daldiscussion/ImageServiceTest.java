package com.macs.group6.daldiscussion;
/*
@author Sharon Alva
*/
import com.macs.group6.daldiscussion.dao.IPostImageDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.PostImage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageServiceTest  {

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

    @Test
    public void uploadImageToCloud() throws DAOException {
        List<MultipartFile> files = new ArrayList<>();
        MultipartFile file1 = new MockMultipartFile("image1.jpg",new byte[3655]);

        MultipartFile file2 = new MockMultipartFile("image2.jpg", new byte[20]);
        files.add(file1);
        files.add(file2);
     List imageUrls = amazonClientMock.uploadImage(files, 1);
     assertEquals(imageUrls.size(),files.size());
     assertTrue(imageUrls.get(0).equals("prod/1/"));

    }

}
