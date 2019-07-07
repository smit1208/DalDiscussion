package com.macs.group6.daldiscussion.service;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IPostService {
    void create(Post post,int user_id);

    void createPostWithImage(Post post, MultipartFile file);

    Map<String,Object> getComments(int postId);

    List<Reply> getReplies(int commentId);

    Post getPostById(int postId);

    void addComment(Comment c, int post_id, int user_id);

    void addReply(Reply reply, int comment_id, int user_id);

    boolean fileSizeExceeded(MultipartFile file);
}