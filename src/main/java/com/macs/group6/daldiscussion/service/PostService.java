package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.AppConfig;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("PostService")

public class PostService implements IPostService, ISubject {
    private static final String CLOUD_URL = "https://daldiscussion.s3.ca-central-1.amazonaws.com/";
    private static final int maxFileSize = 65535;

    private static int karmaPoints;
    private static int commentSize;
    private static int postIDforNotify;
    private ArrayList<IObserver> observers;
    private AmazonClient amazonClient = AmazonClient.getInstance();
    AppConfig appConfig = AppConfig.getInstance();
    private IDAOFactory idaoFactory;

    public PostService(){
        idaoFactory = new DAOFactory();
        observers = new ArrayList<IObserver>();
    }

    @Override
    public void create(Post post,int user_id) {
        idaoFactory.createPostDAO().create(post, user_id);
    }

    @Override
    public void createPostWithImage(Post post, List<MultipartFile> files, int user_id) {
            int post_id = idaoFactory.createPostDAO().createPostWithImage(post, user_id);
            List<String> imageUrls = uploadImageToCloud(files, post_id);
            saveImagetoDB(imageUrls, post_id);
    }

    @Override
    public Map<String, Object> getComments(int postId) {
        return idaoFactory.createCommentDAO().getComments(postId);
    }

    @Override
    public List<Reply> getReplies(int commentId) {
        return idaoFactory.createReplyDAO().getReplies(commentId);
    }

    @Override
    public Post getPostById(int postId) {
        return idaoFactory.createCommentDAO().getPostById(postId);
    }

    @Override
    public void addComment(Comment c, int post_id, int user_id,String name) {
        idaoFactory.createCommentDAO().addComment(c,post_id,user_id,name);
        commentSize = getCommentSize(post_id);
        int limit = AppConfig.getInstance().get_postCommentSize();
        if(isLimitReached(commentSize,limit)){
            this.karmaPoints = 100;
            this.postIDforNotify = post_id;
            notifyObserver();
        }
    }

    @Override
    public void addReply(Reply reply, int comment_id, int user_id, String name) {
        idaoFactory.createReplyDAO().addReply(reply,comment_id,user_id,name);
    }

    @Override
    public boolean fileSizeExceeded(MultipartFile file) {
        if(file.getSize() > maxFileSize){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<String> uploadImageToCloud(List<MultipartFile> files, int post_id) {
        List<String> imageUrls = new ArrayList<String>();
        try {
            imageUrls = amazonClient.uploadImage(files,post_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageUrls;
    }

    @Override
    public void saveImagetoDB(List<String> imageLinks, int post_id) {
        for(String imageLink: imageLinks){
            idaoFactory.createPostImageDAO().addImage(CLOUD_URL+imageLink, post_id);
        }
    }

    @Override
    public List<PostImage> getImageByPostId(int post_id) {
         return idaoFactory.createPostImageDAO().getImageByPostId(post_id);
    }

    private int getCommentSize(int post_id){
        Map<String,Object> commentMap = new HashMap<>();
        commentMap = idaoFactory.createCommentDAO().getComments(post_id);
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");
        return commentList.size();
    }

    private boolean isLimitReached(int commentSize, int limit){
        if(commentSize == limit){
            return true;
        }
        return false;
    }
    @Override
    public void attach(IObserver newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void detach(IObserver deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver() {
        for(IObserver observer : observers){
            observer.update(karmaPoints, postIDforNotify);
        }

    }
}
