package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.PostImage;

import java.util.List;

public interface IPostImageDAO {

     void addImage(String imageLink, int post_id) throws DAOException;
     List<PostImage> getImageByPostId(int post_id) throws DAOException;
}
