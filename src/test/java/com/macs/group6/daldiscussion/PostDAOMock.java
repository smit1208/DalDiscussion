package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.model.Post;

import java.sql.Blob;

public class PostDAOMock implements IPostDAO {
    @Override
    public void create(Post post, int user_id) {

    }

    @Override
    public void createPostWithImage(Post post, Blob postImageBlob) {

    }
}
