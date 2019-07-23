package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IPostImageDAO;
import com.macs.group6.daldiscussion.model.PostImage;

import java.util.ArrayList;
import java.util.List;

public class PostImageDAOMock implements IPostImageDAO {
    @Override
    public void addImage(String imageLink, int post_id) {

    }

    @Override
    public List<PostImage> getImageByPostId(int post_id) {
        List<PostImage> images = new ArrayList<>();
        PostImage image1 = new PostImage();
        image1.setId(1);
        image1.setImageLink("https://daldiscussion.s3.ca-central-1.amazonaws.com/dev/1/image1");
        images.add(image1);

        return images;
    }
}
