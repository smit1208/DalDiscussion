package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.model.LoginRequest;
import com.macs.group6.daldiscussion.model.LoginResponse;
import com.macs.group6.daldiscussion.service.LoginService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * This class contains custom authentication for this web application.
 * @author Kush Rao
 */
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    /**
     * This method receive username/password, then call LoginService, throw exception if results has error.
     * @param authentication is requested Authentication instance
     * @return a authorized Authentication instance
     * @throws AuthenticationException is thrown if LoginService failed
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        
        String password = "";
        if (authentication.getCredentials() != null) {
            password = authentication.getCredentials().toString();
        }
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        LoginResponse loginResponse = LoginService.getInstance().run(loginRequest);
        if (loginResponse.getIsError()) {
            throw new AuthenticationServiceException("[" + loginResponse.getErrorCode() + "] " + loginResponse.getErrorMessage());
        }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("online", "true");
        session.setAttribute("userid", loginResponse.getUserId() + "");
        return new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword(), new ArrayList<>());
    }

    /**
     *
     * @param aClass is Class instance of Authentication which needs to be checked
     * @return a true if Authentication is valid for this provider
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
