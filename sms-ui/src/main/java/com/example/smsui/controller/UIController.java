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
                antMatchers("/").
                permitAll().
                anyRequest().
                authenticated();
    }



    @RequestMapping(value = "/")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/create")
    public String create(){
        return "create";
    }

    @RequestMapping(value = "/update")
    public String update(){
        return "update";
    }

    @RequestMapping(value = "/fetchone")
    public String findOne(){
        return "fetchById";
    }

    @RequestMapping(value = "/fetchall")
    public String findAll(){
        return "fetchAll";
    }

    @RequestMapping(value = "/report")
    public String welcomereport(){
        return "home";
    }

}
