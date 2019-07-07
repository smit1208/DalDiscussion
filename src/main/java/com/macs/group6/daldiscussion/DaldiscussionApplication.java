package com.macs.group6.daldiscussion;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DaldiscussionApplication {
    final static Logger logger = Logger.getLogger(DaldiscussionApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DaldiscussionApplication.class, args);
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

}
