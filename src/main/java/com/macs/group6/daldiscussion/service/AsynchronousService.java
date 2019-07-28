package com.macs.group6.daldiscussion.service;


import com.macs.group6.daldiscussion.controller.MyThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by gkatzioura on 4/26/17.
 */
@Service
public class AsynchronousService {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TaskExecutor taskExecutor;
    public void executeAsynchronously() {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                MyThread myThread = applicationContext.getBean(MyThread.class);
                taskExecutor.execute(myThread);
            }
        });
    }
}