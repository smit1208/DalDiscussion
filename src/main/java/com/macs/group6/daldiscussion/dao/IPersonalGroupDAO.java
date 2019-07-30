package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.exceptions.DAOException;

import java.util.Map;

public interface IPersonalGroupDAO {
    Map<String, Object> getPrivatePostsByGroupID(int groupID) throws DAOException;
}
