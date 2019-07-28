package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class RegisterServiceTest {

    RegisterService registerService;
    RegisterDAOMock registerDAOMock;
    SubscriptionService subscriptionService;
    SubscriptionDAOMock subscriptionDAOMock;
    UserService userService;


    @Before
    public void setUp() throws Exception {

        registerDAOMock = new RegisterDAOMock();
        subscriptionService = new SubscriptionService();
        userService = new UserService(UserDAO.getInstance());
        registerService = new RegisterService(registerDAOMock, subscriptionService, userService);

    }

    @After
    public void tearDown() {
        registerService = null;
        registerDAOMock = null;
        subscriptionDAOMock = null;
        subscriptionService = null;
        userService = null;
    }

    @Test
    public void testCreateUser() {

        User userMock = new User();
        userMock.setId(1);
        userMock.setEmail("vivek.shah@gmail.com");
        userMock.setFirstName("Vivek");
        userMock.setLastName("Shah");
        userMock.setPassword("Abc@123");

        assertEquals(1, registerDAOMock.create(userMock));
    }

    @Test
    public void testwithEmptyUser(){
        User userMock = new User();
        userMock.setId(0);
        userMock.setEmail("");
        userMock.setFirstName("");
        userMock.setLastName("");
        userMock.setPassword("");
        assertEquals(0, registerDAOMock.create(userMock));
    }

    @Test
    public void testUserExist(){
        User userMock = new User();
        userMock.setEmail("vivek.shah@gmail.com");
        assertTrue(registerDAOMock.userExists(userMock));
    }

    @Test
    public void testUserDoesNotExist(){
        User userMock = new User();
        userMock.setEmail("");
        assertFalse(registerDAOMock.userExists(userMock));
    }
}



