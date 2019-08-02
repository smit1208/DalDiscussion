package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.PostImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    List<String> uploadImageToCloud(List<MultipartFile> files, int post_id) throws DAOException;

    void saveImagetoDB(List<String> imageLinks, int post_id) throws DAOException;

    List<PostImage> getImageByPostId(int post_id) throws DAOException;
}
