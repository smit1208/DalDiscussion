package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.factory.IServiceFactory;
import com.macs.group6.daldiscussion.factory.ServiceFactory;
import com.macs.group6.daldiscussion.model.PostImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageService implements IImageService {
    private static final String CLOUD_URL = "https://daldiscussion.s3.ca-central-1.amazonaws.com/";
    private AmazonClient amazonClient = AmazonClient.getInstance();

    private IDAOFactory idaoFactory;
    private IServiceFactory iServiceFactory;
    public ImageService(){
        idaoFactory = new DAOFactory();
        iServiceFactory = new ServiceFactory();
    }
    @Override
    public List<String> uploadImageToCloud(List<MultipartFile> files, int post_id) throws DAOException {
        List<String> imageUrls = new ArrayList<String>();

        try {
            imageUrls = amazonClient.uploadImage(files,post_id);
        } catch (IOException e) {
            throw new DAOException("AMAZON CLIENT - FILE UPLOAD ERROR", e);
        }
        return imageUrls;
    }

    @Override
    public void saveImagetoDB(List<String> imageLinks, int post_id) throws DAOException {
        for(String imageLink: imageLinks){
            idaoFactory.createPostImageDAO().addImage(CLOUD_URL+imageLink, post_id);
        }
    }

    @Override
    public List<PostImage> getImageByPostId(int post_id) throws DAOException {
        return idaoFactory.createPostImageDAO().getImageByPostId(post_id);
    }

}
