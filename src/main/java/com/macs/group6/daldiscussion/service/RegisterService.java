package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IRegisterDAO;
import com.macs.group6.daldiscussion.dao.RegisterDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vivek Shah
 */

@Service("RegisterService")
public class RegisterService implements IRegisterService {
    private ISubscriptionService iSubscriptionService;
    private IUserService iUserService;
    private IRegisterDAO registerDAO;

    @Autowired
    public RegisterService(@Qualifier("RegisterDAO")IRegisterDAO registerDAO, @Qualifier("SubscriptionService")ISubscriptionService subscriptionService,
                           @Qualifier("UserService")IUserService iUserService){
        this.registerDAO = registerDAO;
        this.iSubscriptionService = subscriptionService;
        this.iUserService = iUserService;
    }

    @Override
    public void create(User userRegister) throws DAOException {
        int result = registerDAO.create(userRegister);
        if(result>0){

           List<User> userList= iUserService.getUserByEmail(userRegister.getEmail());
            for (User user: userList) {
                iSubscriptionService.addDefaultSubscriptionRequest(user.getId());
            }
       }
    }

    @Override
    public boolean userExists(User userRegister) throws DAOException {
       return registerDAO.userExists(userRegister);
    }
}
