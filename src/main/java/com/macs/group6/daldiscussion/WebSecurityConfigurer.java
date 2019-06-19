package com.macs.group6.daldiscussion;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class contains security configuration for application.
 * @author Kush Rao
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    /**
     * Configure custom authentication provider for application
     * @param auth is AuthenticationManagerBuilder instance
     * @throws Exception is Exception instance
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AuthenticationProvider());
    }

    /**
     * Configure access controls for URLs of this web application
     * @param http is HttpSecurity instance
     * @throws Exception is Exception instance
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (AppConfig.getInstance().getSecurityConfig()) {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    /** Configure GET access URLs */
                    .antMatchers(HttpMethod.GET,"/forgot-password", "/reset-password", "/logout", "/register", "/login", "/prelogin")
                    .permitAll()
                    /** Configure POST access URLs */
                    .antMatchers(HttpMethod.POST, "/login", "/register", "/forgot-password", "/reset-password")
                    .permitAll()
                    /** Configure HEADER access URLs */
                    .antMatchers(HttpMethod.HEAD, "/logout")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    /** Configure LoginPage of this application which will be redirected to if URL having not access right. */
                    .loginPage("/prelogin")
                    .permitAll();
        } else {
            http.authorizeRequests().anyRequest().permitAll();
        }
    }
}
