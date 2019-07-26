package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.AppConfig;
import com.macs.group6.daldiscussion.dao.*;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private IReplyDAO iReplyDAO;
    private ICommentDAO iCommentDAO;
    private IPostDAO iPostDAO;
    private ArrayList<IObserver> observers;
    private IPostImageDAO iPostImageDAO;
    private AmazonClient amazonClient = AmazonClient.getInstance();
    AppConfig appConfig = AppConfig.getInstance();


    @Autowired
    public PostService(@Qualifier("CommentDAO") ICommentDAO iCommentDAO, @Qualifier("PostDAO") IPostDAO iPostDAO, @Qualifier("ReplyDAO")IReplyDAO iReplyDAO,
                       @Qualifier("PostImageDAO")IPostImageDAO iPostImageDAO){
        this.iCommentDAO = iCommentDAO;
        this.iPostDAO = iPostDAO;
        this.iReplyDAO = iReplyDAO;
        this.iPostImageDAO = iPostImageDAO;
        observers = new ArrayList<IObserver>();
    }
    @Override
    public void create(Post post,int user_id) {
        iPostDAO.create(post,user_id);
    }

    @Override
    public void createPostWithImage(Post post, List<MultipartFile> files, int user_id) {
            int post_id =  iPostDAO.create(post,user_id);
            System.out.println("Post id"+post_id);
            if(post_id>0){
                List<String> imageUrls = uploadImageToCloud(files, post_id);
                saveImagetoDB(imageUrls, post_id);
            }

    }

    @Override
    public Map<String, Object> getComments(int postId) {
        return iCommentDAO.getComments(postId);
    }

    @Override
    public List<Reply> getReplies(int commentId) {
        return iReplyDAO.getReplies(commentId);
    }

    @Override
    public Post getPostById(int postId) {
        return iCommentDAO.getPostById(postId);
    }

    @Override
    public void addComment(Comment c, int post_id, int user_id) {
        iCommentDAO.addComment(c,post_id,user_id);
        commentSize = getCommentSize(post_id);
        int limit = AppConfig.getInstance().get_postCommentSize();
        if(isLimitReached(commentSize,limit)){
            this.karmaPoints = 100;
            this.postIDforNotify = post_id;
            notifyObserver();
        }
        updatePostMoificationDate(post_id);
    }

    @Override
    public void addReply(Reply reply, int comment_id, int user_id,int post_id) {
        iReplyDAO.addReply(reply,comment_id,user_id);
        updatePostMoificationDate(post_id);
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
            iPostImageDAO.addImage(CLOUD_URL+imageLink, post_id);
        }
    }

    @Override
    public List<PostImage> getImageByPostId(int post_id) {
         return iPostImageDAO.getImageByPostId(post_id);
    }

    @Override
    public void updatePostMoificationDate(int post_id) {
        iPostDAO.updatePostModificationDate(post_id);
    }

    private int getCommentSize(int post_id){
        Map<String,Object> commentMap = new HashMap<>();
        commentMap = iCommentDAO.getComments(post_id);
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
