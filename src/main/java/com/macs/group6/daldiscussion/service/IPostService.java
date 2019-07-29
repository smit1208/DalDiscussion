package com.macs.group6.daldiscussion.service;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface IPostService {
    void create(Post post,int user_id);

    void createPostWithImage(Post post, MultipartFile file,int user_id);

    Map<String,Object> getComments(int postId);

    List<Reply> getReplies(int commentId);

    Post getPostById(int postId);

    void addComment(Comment c, int post_id, int user_id,String name);

    void addReply(Reply reply, int comment_id, int user_id, String name, int post_id);

    boolean fileSizeExceeded(MultipartFile file);

    List<String> uploadImageToCloud(MultipartFile files, int post_id) throws IOException;

    void saveImagetoDB(List<String> imageLinks, int post_id);

    List<PostImage> getImageByPostId(int post_id);

    void updatePostMoificationDate(int post_id);

    void updatePostStatus();
}