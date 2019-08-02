package com.macs.group6.daldiscussion;


import com.macs.group6.daldiscussion.dao.IReplyDAO;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Reply;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ReplyServiceTest {
    private IReplyDAO replyDAOMock;

    @Before
    public void setUp() throws Exception {
        replyDAOMock = new ReplyDAOMock();
    }

    @After
    public void tearDown() {
        replyDAOMock = null;
    }

    @Test
    public void getRepliesTest() throws DAOException {
        List<Reply> replyList = new ArrayList<>();
        for (Reply reply : replyList) {
            reply = (Reply) replyDAOMock.getReplies(1);
            assertEquals(1, reply.getId());
            assertEquals("Testing for replies", reply.getReply_description());
        }

    }
}
