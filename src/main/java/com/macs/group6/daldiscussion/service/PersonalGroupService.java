package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("PersonalGroupService")
public class PersonalGroupService implements IPersonalGroupService{

    private IDAOFactory idaoFactory;

    public PersonalGroupService(){
        idaoFactory = new DAOFactory();
    }

    @Override
    public Map<String, Object> getPrivatePostsByGroupID(int groupID) {
        return idaoFactory.createPersonalGroupDAO().getPrivatePostsByGroupID(groupID);
    }
}
