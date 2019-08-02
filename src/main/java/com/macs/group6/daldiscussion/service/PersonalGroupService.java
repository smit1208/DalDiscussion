package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Smit Saraiya
 */
@Service("PersonalGroupService")
public class PersonalGroupService implements IPersonalGroupService{

    private IDAOFactory idaoFactory;

    public PersonalGroupService(){
        idaoFactory = new DAOFactory();
    }

    @Override
    public Map<String, Object> getPrivatePostsByGroupID(int groupID) throws DAOException {
        return idaoFactory.createPersonalGroupDAO().getPrivatePostsByGroupID(groupID);
    }
}
