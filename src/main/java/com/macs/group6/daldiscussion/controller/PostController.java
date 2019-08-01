
package com.macs.group6.daldiscussion.controller;
/**
 * @author Sharon Alva
 */
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.service.AmazonClient;
import com.macs.group6.daldiscussion.service.IPostService;
import com.macs.group6.daldiscussion.service.ISubscriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller

public class PostController {
    private static final Logger logger = Logger.getLogger(PostController.class);
    private ISubscriptionService iSubscriptionService;
    private IPostService postService;
    private AmazonClient amazonClient;

    @Autowired
    public PostController(@Qualifier("PostService") IPostService iPostService, @Qualifier("SubscriptionService")ISubscriptionService iSubscriptionService){
        this.postService = iPostService;
        this.iSubscriptionService = iSubscriptionService;
        this.amazonClient = amazonClient;
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.GET)
    public String postView(Model model, HttpSession session) {
        Map<String,Object> displaySubscriptionMap = new HashMap<>();
        int userID = (Integer)session.getAttribute("id");
        String name = (String) session.getAttribute("firstName");
        model.addAttribute("name",name);
        try {
            displaySubscriptionMap = iSubscriptionService.approvedSubscriptions(userID);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        List<Subscription> subscriptions = (List<Subscription>) displaySubscriptionMap.get("displayApprovedSubscriptions");
        model.addAttribute("approvedSubscription",subscriptions);
        return Views.VIEWPOST;
    }

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public String savePost(@RequestParam("postTitle") String postTitle,
                           @RequestParam("postDesc") String postDesc,
                           @RequestParam("category") Integer category,
                           @RequestParam("group") Integer group,
                           @RequestParam(value = "image", required = false) MultipartFile file, Model model, HttpSession session) {

        Post post = new Post();
        int user_id = (Integer) session.getAttribute("id");
        post.setUser_id(user_id);

        if(postTitle!=null && postTitle.length()>0){
            post.setPost_title(postTitle);
        }
        if(postDesc!=null && postDesc.length()>0 ){
            post.setPost_description(postDesc);
        }
        if(category!=null && category>0){
            post.setCategory(category);
        }
        if(group!=null){
            post.setGroup(group);
        }
            if (null != file && file.getSize() > 0)
            {
                post.setIsImage(1);
                try{
                    postService.createPostWithImage(post,file, user_id);
                } catch (DAOException e) {
                    logger.error(e.getMessage());
                    return "customError";
                }

            }
        else{
            post.setIsImage(0);
                try {
                    postService.createPost(post);
                } catch (DAOException e) {
                    logger.error(e.getMessage());
                    return "customError";
                }
            }
        logger.info("Post added successfully");
        return "redirect:/home";
    }
}
