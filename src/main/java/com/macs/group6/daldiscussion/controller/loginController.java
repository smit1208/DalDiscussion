package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*public class loginController {

 *//*  @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
*/
@Controller
public class loginController {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String init(Model model) {
        model.addAttribute("msg", "Please Enter Your Login Details");
        return "login.jsp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Model model,
                         @ModelAttribute("login") User login) {
        if (login != null && login.getUserName() != null
                & login.getPassword() != null) {
            if (login.getUserName().equals("vivek")
                    && login.getPassword().equals("vivek")) {
                model.addAttribute("msg", login.getUserName());
                return "Login  Successful";
            } else {
                model.addAttribute("error", "Invalid Details");
                return "Login Failed";
            }
        } else {
            model.addAttribute("error", "Please enter Details");
            return "login.jsp";
        }
    }
}
