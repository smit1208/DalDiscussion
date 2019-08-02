package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.ReportedPost;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Smit Saraiya
 */
@Service("HomeService")
public class HomeService implements IHomeService {

    private IDAOFactory idaoFactory;

    public HomeService(){
       idaoFactory = new DAOFactory();
    }
    @Override
    public Map<String, Object> getAllPosts() throws DAOException {
        return idaoFactory.createHomeDAO().getAllPosts();
    }

    @Override
    public void addReportingPost(int user_id, int post_id) throws DAOException {
        idaoFactory.createHomeDAO().addReportingPost(user_id, post_id);
    }

    @Override
    public List<ReportedPost> fetchReportedPostByUserId(int reportedUser_id) throws DAOException {
        return idaoFactory.createHomeDAO().fetchReportedPostByUserId(reportedUser_id);
    }

    @Override
    public List<Post> getSearchedPost(String search) throws DAOException {
        return idaoFactory.createHomeDAO().getSearchedPost(search);
    }

    @Override
    public List<Post> getPostsByGroupId(int group_id) throws DAOException {
        return idaoFactory.createHomeDAO().getPostsByGroupId(group_id);
    }
}
