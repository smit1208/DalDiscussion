package com.macs.group6.daldiscussion.dao;

import java.util.Map;

public interface IDashboardDAO {
    Map<String,Object> getPostsByUserID(int user_id);

    void deletePostByUserId(int post_id);
}
