package com.example.smsui.controller;

import com.example.smsui.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


@Controller
@EnableOAuth2Sso
public class UIController extends WebSecurityConfigurerAdapter{

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

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

    @RequestMapping(value = "/profiles")
    public String loadprof(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfigurer.getToken());
        HttpEntity<Student>  studentHttpEntity = new HttpEntity<Student>(httpHeaders);
        try {
            ResponseEntity<Student[]> responseEntity = restTemplate.exchange("http://localhost:8080/sms/students", HttpMethod.GET,studentHttpEntity,Student[].class);
            model.addAttribute("students",responseEntity);
        }catch (HttpStatusCodeException se){
            ResponseEntity res = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",res);

        }

        return "secure";
    }

}
