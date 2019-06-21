package com.macs.group6.daldiscussion.service;

public interface IServiceFactory {
    IHomeService getHomeService();
    IPostService getPostService();
}
