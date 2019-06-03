package com.macs.group6.daldiscussion.controllerTest;

import com.macs.group6.daldiscussion.controller.demoController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class demoControllerTest {
    @Test
    public void testHomeController() {
        demoController dc = new demoController();
        String result = dc.demo();
        assertEquals(result, "Hi Welcome");
    }
}
