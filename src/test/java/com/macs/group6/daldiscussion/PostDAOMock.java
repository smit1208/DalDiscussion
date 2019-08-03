package com.macs.group6.daldiscussion;
/*
@author Sharon Alva
*/
import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import java.util.ArrayList;
import java.util.List;

public class PostDAOMock implements IPostDAO {

    public PostDAOMock(){

    }

    @Override
    public int createPost(Post post) throws DAOException {
        if(post.getPost_title().isEmpty() && post.getPost_description().isEmpty() && post.getIsImage() == 0
        && post.getCategory() == 0 && post.getGroup() == 5 && post.getReport() == 0 && post.getUser_id() == 1){
            return 0;
        }else{
            return 1;
        }

    }

    @Override
    public List<Post> getAllActivePosts() {
        List<Post> activePostList = new ArrayList<>();

        Post post1 = new Post();
        post1.setId(1);
        post1.setPost_title("First Post");
        post1.setPost_description("First Post description");
        post1.setAlive(1);
        activePostList.add(post1);

        Post post2 = new Post();
        post2.setId(2);
        post2.setPost_title("Second Post");
        post2.setPost_description("Second Post description");
        post2.setAlive(1);
        activePostList.add(post2);
        return activePostList;
    }


    @Override
    public void updatePostStatus(Post post) {
        return;
    }

    @Override
    public Post getPostById(int postId) throws DAOException {
        Post post = new Post();
        post.setId(1);
        post.setPost_title("Post title");
        post.setPost_description("Post Description");
        post.setUser_id(12);
        post.setGroup(5);

        return post;
    }
}
