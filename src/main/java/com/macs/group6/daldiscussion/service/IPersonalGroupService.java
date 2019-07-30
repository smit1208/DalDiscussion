package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IPersonalGroupService {
    Map<String, Object> getPrivatePostsByGroupID(int groupID) throws DAOException;
}
