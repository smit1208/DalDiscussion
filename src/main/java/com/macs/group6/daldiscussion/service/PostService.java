package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.AppConfig;
import com.macs.group6.daldiscussion.dao.*;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("PostService")

public class PostService implements IPostService, ISubject {

    private static final int maxFileSize = 65535;
    private static int karmaPoints;
    private static int commentSize;
    private static int userIDforNotify;

    private IReplyDAO iReplyDAO;
    private ICommentDAO iCommentDAO;
    private IPostDAO iPostDAO;
    private ArrayList<IObserver> observers;
    AppConfig appConfig = AppConfig.getInstance();

    @Autowired
    public PostService(@Qualifier("CommentDAO") ICommentDAO iCommentDAO, @Qualifier("PostDAO") IPostDAO iPostDAO, @Qualifier("ReplyDAO")IReplyDAO iReplyDAO ){
        this.iCommentDAO = iCommentDAO;
        this.iPostDAO = iPostDAO;
        this.iReplyDAO = iReplyDAO;
        observers = new ArrayList<IObserver>();
    }
    @Override
    public void create(Post post,int user_id) {
        iPostDAO.create(post,user_id);
    }

    @Override
    public void createPostWithImage(Post post, MultipartFile file) {
       double size = (double) file.getSize();
        byte[] imageBytes;

        try{
            imageBytes = file.getBytes();
            post.setFile(imageBytes);
            Blob postImageBlob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
            iPostDAO.createPostWithImage(post, postImageBlob);
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            this.userIDforNotify = user_id;
            notifyObserver();
        }
    }

    @Override
    public void addReply(Reply reply, int comment_id, int user_id) {
        iReplyDAO.addReply(reply,comment_id,user_id);
    }

    @Override
    public boolean fileSizeExceeded(MultipartFile file) {
        if(file.getSize() > maxFileSize){
            return true;
        }else{
            return false;
        }
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
            observer.update(karmaPoints, userIDforNotify);
        }

    }
}
