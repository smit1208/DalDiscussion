package com.macs.group6.daldiscussion.controller;

/**
 * 
 *
 * @author Kush Rao
 */
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.service.LoginService;
import com.macs.group6.daldiscussion.service.UserService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Controller
public class ProfileController {
	
	private static final Logger LOGGER = LogManager.getLogger(ProfileController.class);
	
	 @GetMapping("/updateprofile")
	    public String getUpdateProfile(HttpServletRequest request, Model model) {
	     

	    	int id = Integer.parseInt((String) request.getSession().getAttribute("userid"));
	    	
	    	
	    	try {
	    	User user=UserDAO.getInstance().findById(id);
	    	List<String> groups= UserDAO.getInstance().getUserGroups(id);
	    		
	    	 model.addAttribute("firstName", user.getFirstName());
			 model.addAttribute("lastName", user.getLastName());
			 model.addAttribute("email", user.getEmail());
			 model.addAttribute("karma", user.getKarmaPoints());
			 model.addAttribute("groups",groups);
		 
		 
		
	    	}catch (Exception e) {
	    		System.out.println("error in getting session values and user values for model");
			}
		 	
	        LOGGER.info("Profile Page displayed successfully");
	        return Views.PROFILE;
	    }

	 
	 @PostMapping(value = "/updateprofile")
	    public String postUpdateProfile( @RequestParam("fname") String fname,
	                           @RequestParam("lname") String lname,	                      
	                           @RequestParam("password") String password,
	                           @RequestParam("email") String email,
	                           HttpSession session, Model model) {
		 String passwd=null;
		 int id = Integer.parseInt((String) session.getAttribute("userid"));
		 UserService updateService= new UserService(new UserDAO());
		 try {
	    	
	    	User user=UserDAO.getInstance().findById(id);
	    	if(password.isEmpty())
	    		passwd=user.getPassword();
	    	else
	    		passwd=password;
		
		
	    	if(updateService.updateUser(id,fname, lname, email, passwd)) {
	    		model.addAttribute("message","profile updated successfully");
	    		 user=UserDAO.getInstance().findById(id);
		    	List<String> groups= UserDAO.getInstance().getUserGroups(id);
		    		
		    	 model.addAttribute("firstName", user.getFirstName());
				 model.addAttribute("lastName", user.getLastName());
				 model.addAttribute("email", user.getEmail());
				 model.addAttribute("karma", user.getKarmaPoints());
				 model.addAttribute("groups",groups);
	    	}
	    	else {
	    		model.addAttribute("message","Profile can not be updated");
	    	}
		 
		 } catch (Exception e) {
	 			e.printStackTrace();
	 		}
	        return "/updateprofile";
		 }
	 
}
