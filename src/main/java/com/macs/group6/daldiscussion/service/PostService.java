package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.AppConfig;
import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.exceptions.ErrorCode;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service("PostService")

public class PostService implements IPostService {
    private static final String CLOUD_URL = "https://daldiscussion.s3.ca-central-1.amazonaws.com/";
    private static final int maxFileSize = 65535;

    private static int karmaPoints;
    private static int commentSize;
    private static int postIDforNotify;
    private ArrayList<IObserver> observers;
    private AmazonClient amazonClient = AmazonClient.getInstance();
    private IDAOFactory idaoFactory;

    public PostService(){
        idaoFactory = new DAOFactory();
        observers = new ArrayList<IObserver>();
    }

    @Override
    public void createPost(Post post)throws DAOException {
        idaoFactory.createPostDAO().createPost(post);
    }

    @Override
    public void createPostWithImage(Post post, MultipartFile files, int user_id) throws DAOException {
            int post_id = idaoFactory.createPostDAO().createPost(post);
            List<String> imageUrls = uploadImageToCloud(files, post_id);
            if(imageUrls.size()>0){
                saveImagetoDB(imageUrls, post_id);
            }

    }

    @Override
    public Map<String, Object> getComments(int postId) throws DAOException {
        return idaoFactory.createCommentDAO().getComments(postId);
    }

    @Override
    public List<Reply> getReplies(int commentId) throws DAOException {
        return idaoFactory.createReplyDAO().getReplies(commentId);
    }

    @Override
    public Post getPostById(int postId) throws DAOException {
        return idaoFactory.createCommentDAO().getPostById(postId);
    }

    @Override
    public void addComment(Comment c, int post_id, int user_id,String name) throws DAOException {
        idaoFactory.createCommentDAO().addComment(c,post_id,user_id,name);
        System.out.println();
        commentSize = getCommentSize(post_id);
        int limit = AppConfig.getInstance().get_postCommentSize();
        if(isLimitReached(commentSize,limit)){
            this.karmaPoints = 100;
            this.postIDforNotify = post_id;
            UserDAO.getInstance().updateUserKarmaPoints(karmaPoints, postIDforNotify);
        }
    }

    @Override
    public void addReply(Reply reply, int comment_id, int user_id, String name) throws DAOException {
        idaoFactory.createReplyDAO().addReply(reply,comment_id,user_id,name);

    }

    @Override
    public List<String> uploadImageToCloud(MultipartFile files, int post_id) throws DAOException {
        List<String> imageUrls = new ArrayList<String>();

        try {
            imageUrls = amazonClient.uploadImage(files,post_id);
        } catch (IOException e) {
            throw new DAOException("AMAZON CLIENT - FILE UPLOAD ERROR", e, ErrorCode.FILE_IO_ERROR);
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

    private int getCommentSize(int post_id) throws DAOException {
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
    public void updatePostMoificationDate (int post_id) throws DAOException {
            idaoFactory.createPostDAO().updatePostModificationDate(post_id);
    }

    @Override
    public void updatePostStatus() throws DAOException {
    List<Post> postList = getAllActivePosts();
        List<Post> inactivePostList = new ArrayList<>();
        if(postList.size() > 0){
            inactivePostList = getInactivePosts(postList);
        }
        if(inactivePostList.size()>0){
            for (Post post: inactivePostList){
                idaoFactory.createPostDAO().updatePostStatus(post);
            }
        }

    }

    @Override
    public List<Post> getAllActivePosts() throws DAOException {
        return idaoFactory.createPostDAO().getAllActivePosts();
    }

    public List<Post> getInactivePosts(List<Post> postList){
        List<Post> inactivePostList = new ArrayList<>();
        for(Post post: postList){
            Date currentDate = new Date();
            Date creationDate = post.getCreationDate();
            Date modificationDate = post.getLastModificationDate();

            long diffInMillies = Math.abs(currentDate.getTime() - creationDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff>=2){
                diffInMillies = Math.abs(currentDate.getTime() - modificationDate.getTime());
                diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if(diff>=2){
                    Post inactivePost = new Post();
                    inactivePost.setId(post.getId());
                    inactivePost.setAlive(0);
                    inactivePostList.add(inactivePost);
                }
            }
        }
        return inactivePostList;
    }
}
