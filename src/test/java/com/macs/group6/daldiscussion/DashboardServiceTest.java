package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IDashboardDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * @author Vivek Shah
 */

public class DashboardServiceTest {
    private static final Logger logger = Logger.getLogger(DashboardServiceTest.class);
    private IDashboardDAO dashboardDAOMock;

    @Before
    public void setUp() throws Exception {
        dashboardDAOMock = new DashboardDAOMock();
    }

    @After
    public void tearDown() {
        dashboardDAOMock = null;
    }

    @Test
    public void testGetPosts() throws DAOException {
        Map<String, Object> postMap = new HashMap<>();
        try {
            postMap = dashboardDAOMock.getPostsByUserID(1);
        } catch (DAOException e) {
            logger.error(e.getMessage());
        }
        Post post1 = (Post) postMap.get("1");

        assertEquals(1, post1.getId());
        assertEquals(1, post1.getUser_id());
        assertEquals("TestPost1", post1.getPost_title());
        assertEquals("TestDescription1", post1.getPost_description());

        Post post2 = (Post) postMap.get("2");

        assertEquals(2, post2.getId());
        assertEquals(2, post2.getUser_id());
        assertEquals("TestPost2", post2.getPost_title());
        assertEquals("TestDescription2", post2.getPost_description());

    }
}
