package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IDashboardDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 * @author Vivek Shah
 */

@Service("DashboardService")
public class DashboardService implements IDashboardService {

    private IDashboardDAO dashboardDAO;

        @Autowired
        public DashboardService(@Qualifier("DashboardDAO") IDashboardDAO dashboardDAO){
            this.dashboardDAO = dashboardDAO;
        }
        @Override
        public Map<String, Object> getPostsByUserID(int user_id) throws DAOException {
            return dashboardDAO.getPostsByUserID(user_id);
        }

        public void deletePostById(int post_id) throws DAOException {

            dashboardDAO.deletePostById(post_id);
        }

        public void updatePostById(String post_title, String post_description, int id) throws DAOException {

        dashboardDAO.updatePostById(post_title,post_description,id);
    }

}


