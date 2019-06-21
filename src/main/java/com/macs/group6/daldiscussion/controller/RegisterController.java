package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.UserRegister;
import com.macs.group6.daldiscussion.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
    public class RegisterController {

    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Map<String, Object> map) {
        map.put("title", "User Registration");
        return "/registerForm";
    }

    @RequestMapping(value = "/user/details", method = RequestMethod.POST)
    public String details(@RequestParam("fname") String fname,
                          @RequestParam("lname") String lname,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password, Map<String, Object> map) {
        map.put("fname", fname);
        map.put("lname", lname);
        map.put("email", email);
        map.put("password", password);
        UserRegister userReg = new UserRegister();
        userReg.setFname(fname);
        userReg.setLname(lname);
        userReg.setEmail(email);
        userReg.setPassword(password);
  /*      System.out.println("User data");
        logger.info("set reg fields");*/
        RegisterService callService = new RegisterService();
        callService.create(userReg);
        return Views.REGISTER;
    }

    }
        /*    @Autowired
        private UserService userService;

        @GetMapping("/register")
        public String registerForm(Model model) {

            model.addAttribute("user", new User());
            return "views/registerForm";
        }


        @PostMapping("/register")
        public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
            if(bindingResult.hasErrors()) {
                return "views/registerForm";
            }

            userService.createUser(user);

            return "views/success";

        }

    }*/

