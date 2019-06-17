package com.macs.group6.daldiscussion;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if ("yes".equalsIgnoreCase(AppConfig.instance().securityConfig)) {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/forgot-password", "/reset-password", "/logout", "/register", "/login", "/prelogin")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/login", "/register", "/forgot-password", "/reset-password")
                    .permitAll()
                    .antMatchers(HttpMethod.HEAD, "/logout")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/prelogin")
                    .permitAll();
        } else {
            http.authorizeRequests().anyRequest().permitAll();
        }
    }
}
