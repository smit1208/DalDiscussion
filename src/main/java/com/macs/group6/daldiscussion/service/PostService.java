package com.macs.group6.daldiscussion.service;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.factory.IServiceFactory;
import com.macs.group6.daldiscussion.factory.ServiceFactory;
import com.macs.group6.daldiscussion.model.Post;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service("PostService")

public class PostService implements IPostService {

    private IDAOFactory idaoFactory;
    private IServiceFactory iServiceFactory;

    public PostService(){
        idaoFactory = new DAOFactory();
        iServiceFactory = new ServiceFactory();
    }

    @Override
    public void createPost(Post post)throws DAOException {
        idaoFactory.createPostDAO().createPost(post);
    }

    @Override
    public void createPostWithImage(Post post) throws DAOException {
            int post_id = idaoFactory.createPostDAO().createPost(post);
            List<String> imageUrls = iServiceFactory.createImageService().uploadImageToCloud(post.getFiles(), post_id);
            if(imageUrls.size()>0){
                iServiceFactory.createImageService().saveImagetoDB(imageUrls, post_id);
            }
    }

    @Override
    public Post getPostById(int postId) throws DAOException {
        return idaoFactory.createPostDAO().getPostById(postId);
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

    @Override
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
