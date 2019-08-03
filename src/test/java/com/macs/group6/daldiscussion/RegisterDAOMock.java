package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IRegisterDAO;
import com.macs.group6.daldiscussion.entities.User;

/**
 * @author Vivek Shah
 */

public class RegisterDAOMock implements IRegisterDAO {

    @Override
    public int create(User userMock) {
        if(userMock.getFirstName().isEmpty() && userMock.getLastName().isEmpty() && userMock.getEmail().isEmpty() && userMock.getPassword().isEmpty() && userMock.getId()==0){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public boolean userExists(User user) {

        if(!user.getEmail().isEmpty()){
            return true;
        }
        else{
            return false;
        }

    }

}