package com.macs.group6.daldiscussion.model;

import com.macs.group6.daldiscussion.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @After
    public void tearDown(){
        user = null;
    }

    @Test
    public void testID(){
        user.setId(1);
        assertEquals(1,user.getId());
    }

    @Test
    public void testFirstName(){
        user.setFirstName("Vivek");
        assertEquals("Vivek", user.getFirstName());
    }

    @Test
    public void testLastName(){
        user.setLastName("Shah");
        assertEquals("Shah", user.getLastName());
    }

    @Test
    public void testEmail(){
        user.setEmail("vivek.shah@gmail.com");
        assertEquals("vivek.shah@gmail.com",user.getEmail());
    }

    @Test
    public void testPassword(){
        user.setPassword("Abc@123");
        assertEquals("Abc@123",user.getPassword());
    }

    @Test
    public void testConfirmPassword(){
        user.setConfirmPassword("Abc@123");
        assertEquals("Abc@123",user.getConfirmPassword());
    }

    @Test
    public void testKarmaPoints(){
        user.setKarmaPoints(500);
        assertEquals(500, user.getKarmaPoints());
    }

    @Test
    public void testSubscriptionLimit(){
        user.setSubscriptionLimit(4);
        assertEquals(4,user.getSubscriptionLimit());
    }

    @Test
    public void testCurrentStatus(){
        user.setCurrentStatus(1);
        assertEquals(1,user.getCurrentStatus());

    }

    @Test
    public void testRole(){
        user.setRole(1);
        assertEquals(1,user.getRole());
    }
}
