package com.macs.group6.daldiscussion;
/**
 * 
 *
 * @author Kush Rao
 */
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DaldiscussionApplication.class);
    }

}
