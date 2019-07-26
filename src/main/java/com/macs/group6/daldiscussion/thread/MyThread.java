package com.macs.group6.daldiscussion.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MyThread extends Thread {
    final long timeInterval = 3600;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyThread.class);
    @Override
    public void run() {
        while (true) {
            // ------- code for task to run
            System.out.println("Hello !!");
            // ------- ends here
            try {
                Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
