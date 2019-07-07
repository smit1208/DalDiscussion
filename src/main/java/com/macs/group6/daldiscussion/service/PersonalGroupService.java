package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IPersonalGroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("PersonalGroupService")
public class PersonalGroupService implements IPersonalGroupService{

    private IPersonalGroupDAO iPersonalGroupDAO;
    @Autowired
    public PersonalGroupService(@Qualifier("PersonalGroupDAO")IPersonalGroupDAO iPersonalGroupDAO){
        this.iPersonalGroupDAO = iPersonalGroupDAO;
    }

    @Override
    public Map<String, Object> getPrivatePostsByGroupID(int groupID) {
        return iPersonalGroupDAO.getPrivatePostsByGroupID(groupID);
    }
}
