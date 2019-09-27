package com.example.smsui.controller;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@EnableOAuth2Sso
public class UIController extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/","/img/**","/css/**").
                permitAll().
                anyRequest().
                authenticated();
    }



    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/create-student")
    public String create(){
        return "create";
    }

    @RequestMapping(value = "/update-student")
    public String update(){
        return "update";
    }

    @RequestMapping(value = "/fetch-one-student")
    public String findOne(){
        return "fetchById";
    }

    @RequestMapping(value = "/fetch-all-students")
    public String findAll(){
        return "fetchAll";
    }

}
