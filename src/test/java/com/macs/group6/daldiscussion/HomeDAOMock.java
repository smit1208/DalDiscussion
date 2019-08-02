package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IHomeDAO;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.model.ReportedPost;

import java.util.*;

/**
 * @author Vivek Shah
 */

public class HomeDAOMock implements IHomeDAO {

    private Map<String,Object> postMap;
    private List<Comment> commentList;
    private List<Reply> replyList;
    private List<Post> postList;

    public HomeDAOMock(){
        postMap = new HashMap<>();
        commentList = new ArrayList<>();
        replyList = new ArrayList<>();
        postList = new ArrayList<>();
        addPost();
    }
    @Override
    public Map<String, Object> getAllPosts() {
        return postMap;
    }

    @Override
    public void addReportingPost(int user_id, int post_id) {

    }

    @Override
    public List<ReportedPost> fetchReportedPostByUserId(int reportedUser_id) {
        return null;
    }

    @Override
    public List<Post> getSearchedPost(String search) {
        return postList;
    }

    @Override
    public List<Post> getPostsByGroupId(int group_id) {
        return postList;
    }

    private void addPost(){
        Post post1 = new Post();
        Comment comment1 = new Comment();
        Reply reply1 = new Reply();
        reply1.setId(1);
        reply1.setReply_description("This is reply 1 desc");
        replyList.add(reply1);

        comment1.setId(1);
        comment1.setComment_description("This is Comment description");
        comment1.setCommentUp(11);
        comment1.setCommentDown(10);
        comment1.setReplies(replyList);
        commentList.add(comment1);

        post1.setId(1);
        post1.setPost_title("This is Post 1 title");
        post1.setPost_description("This is post 1 description");
        post1.setUpVote(12);
        post1.setDownVote(10);
        post1.setAlive(1);
        post1.setDate(new Date());
        post1.setGroup(5);
        post1.setComments(commentList);

        Post post2 = new Post();
        Comment comment2 = new Comment();
        Reply reply2 = new Reply();
        reply2.setId(1);
        reply2.setReply_description("This is reply 2 desc");
        replyList.add(reply2);

        comment2.setId(2);
        comment2.setComment_description("This is Comment 2 description");
        comment2.setCommentUp(100);
        comment2.setCommentDown(20);
        comment2.setReplies(replyList);
        commentList.add(comment2);

        post2.setId(2);
        post2.setPost_title("This is Post 2 title");
        post2.setPost_description("This is post 2 description");
        post2.setUpVote(121);
        post2.setDownVote(102);
        post2.setAlive(1);
        post2.setDate(new Date());
        post2.setComments(commentList);

        Post post3 = new Post();
        Comment comment3 = new Comment();
        Reply reply3 = new Reply();
        reply3.setId(1);
        reply3.setReply_description("This is reply 3 desc");
        replyList.add(reply3);

        comment3.setId(1);
        comment3.setComment_description("This is Comment 3 description");
        comment3.setCommentUp(111);
        comment3.setCommentDown(220);
        comment3.setReplies(replyList);
        commentList.add(comment3);
        post3.setId(3);
        post3.setPost_title("This is new post title");
        post3.setPost_description("This is new post description");
        post3.setUpVote(129);
        post3.setDownVote(12);
        post3.setAlive(1);
        post3.setDate(new Date());
        post3.setComments(commentList);

        postMap.put("1",post1);
        postMap.put("2",post2);
        postMap.put("3",post3);

        postList.add(post1);
    }
}
