package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.model.LoginRequest;
import com.macs.group6.daldiscussion.model.LoginResponse;
import com.macs.group6.daldiscussion.service.impl.LoginService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = "";
        if (authentication.getCredentials() != null) {
            password = authentication.getCredentials().toString();
        }
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.username = username;
        loginRequest.password = password;
        LoginResponse loginResponse = LoginService.instance().run(loginRequest);
        if (loginResponse.isError) {
            throw new AuthenticationServiceException("[" + loginResponse.errorCode + "] " + loginResponse.errorMessage);
        }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("online", "true");
        session.setAttribute("usercode", loginResponse.usercode);
        return new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
