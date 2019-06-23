package com.macs.group6.daldiscussion.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplyTest {
    Reply reply = new Reply();
    @Test
    public void getId() {
        reply.setId(1);
        assertEquals(1,reply.getId());
    }

    @Test
    public void getReply_description() {
        reply.setReply_description("This is reply");
        assertEquals("This is reply",reply.getReply_description());
    }
}