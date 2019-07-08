package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IDashboardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("DashboardService")
public class DashboardService implements IDashboardService {

    private IDashboardDAO dashboardDAO;

        @Autowired
        public DashboardService(@Qualifier("DashboardDAO") IDashboardDAO dashboardDAO){
            this.dashboardDAO = dashboardDAO;
        }
        @Override
        public Map<String, Object> getPostsByUserID(int user_id) {
            return dashboardDAO.getPostsByUserID(user_id);
        }

        public void deletePostById(int post_id){
            dashboardDAO.deletePostById(post_id);
        }
}


