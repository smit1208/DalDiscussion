package com.macs.group6.daldiscussion.service;
git
import com.macs.group6.daldiscussion.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import java.util.List;

public interface IPostService {
    public void create(Post post);

    public void createPostWithImage(Post post, MultipartFile file);

    List<Comment> getComments(int postId);

    List<Reply> getReplies(int commentId);

    Post getPostById(int postId);

    void addComment(Comment c, int post_id);

    void addReply(Reply reply, int comment_id);
}