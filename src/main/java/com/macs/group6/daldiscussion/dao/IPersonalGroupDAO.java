package com.macs.group6.daldiscussion.dao;

import java.util.Map;

public interface IPersonalGroupDAO {
    Map<String, Object> getPrivatePostsByGroupID(int groupID);
}
