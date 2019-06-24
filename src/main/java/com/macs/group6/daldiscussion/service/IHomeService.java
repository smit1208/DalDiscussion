package com.macs.group6.daldiscussion.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IHomeService {
    Map<String,Object> getAllPosts();
}
