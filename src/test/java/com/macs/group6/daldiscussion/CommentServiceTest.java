package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class CommentServiceTest {
    private ICommentDAO commentDAOMock;

    @Before
    public void setUp() throws Exception {
        commentDAOMock = new CommentDAOMock();
    }

    @After
    public void tearDown() {
        commentDAOMock = null;
    }

    @Test
    public void getComments() throws DAOException {
        Map<String, Object> commentMap = new HashMap<>();
        commentMap = commentDAOMock.getComments(1);

        Comment comment = (Comment) commentMap.get("1");
        assertEquals(1, comment.getId());
        assertEquals("Testing of Comment Service", comment.getComment_description());
        assertEquals(11, comment.getCommentUp());
        assertEquals(10, comment.getCommentDown());

        Comment comment1 = (Comment) commentMap.get("2");
        assertEquals(2, comment1.getId());
        assertEquals("Testing of Comment Service 1", comment1.getComment_description());
        assertEquals(12, comment1.getCommentUp());
        assertEquals(122, comment1.getCommentDown());
    }
}
