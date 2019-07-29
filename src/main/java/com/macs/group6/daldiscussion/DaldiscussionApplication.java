package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.service.CheckPostStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }, scanBasePackages = {"com.macs.group6.daldiscussion","com.macs.group6.daldiscussion.controller","com.macs.group6.daldiscussion.dao","com.macs.group6.daldiscussion.database","com.macs.group6.daldiscussion.entities","com.macs.group6.daldiscussion.model","com.macs.group6.daldiscussion.service"})
public class DaldiscussionApplication {
    final static Logger logger = Logger.getLogger(DaldiscussionApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DaldiscussionApplication.class, args);
        PropertyConfigurator.configure("src/main/resources/log4j.properties");

        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
        executor.scheduleWithFixedDelay(CheckPostStatus.getInstance(), 0, 2, TimeUnit.DAYS);
    }

}
