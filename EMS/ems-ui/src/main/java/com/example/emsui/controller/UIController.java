package com.example.emsui.controller;

import com.example.emsui.model.Employee;
import com.example.emsui.model.Project;
import com.example.emsui.model.Task;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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

    @RequestMapping(value = "/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/employees")
    public String getEmployees(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);
        try {
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://localhost:8080/ems/employees", HttpMethod.GET,employeeHttpEntity,Employee[].class);
            model.addAttribute("employees",responseEntity.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity res = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",res);

        }
        return "allEmployees";
    }

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute Employee employee){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(employee,httpHeaders);

        ResponseEntity<Employee> responseEntity = restTemplate.exchange("http://localhost:8080/ems/employees", HttpMethod.POST,employeeHttpEntity,Employee.class);
        if(responseEntity.getStatusCodeValue()==200){
            return "redirect:employees";
        }
        return "createEmployee";
    }

    @RequestMapping(value = "/employee")
    public String getEmployee(){
        return "employee";
    }

    @RequestMapping(value = "/create-employee")
    public String createEmployee(){
        return "createEmployee";
    }

    @RequestMapping(value = "/projects")
    public String getProjects(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(httpHeaders);
        try {
            ResponseEntity<Project[]> responseEntity = restTemplate.exchange("http://localhost:8081/ems/projects", HttpMethod.GET,projectHttpEntity,Project[].class);
            model.addAttribute("projects",responseEntity.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity res = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",res);

        }
        return "allProjects";
    }

    @RequestMapping(value = "/projects",method = RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(project,httpHeaders);

        ResponseEntity<Project> responseEntity = restTemplate.exchange("http://localhost:8081/ems/projects", HttpMethod.POST,projectHttpEntity,Project.class);
        if(responseEntity.getStatusCodeValue()==200){
            return "redirect:projects";
        }
        return "createProject";
    }

    @RequestMapping(value = "/project")
    public String getProject(){
        return "project";
    }

    @RequestMapping(value = "/create-project")
    public String createProject(){
        return "createProject";
    }

    @RequestMapping(value = "/tasks")
    public String getTasks(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(httpHeaders);
        try {
            ResponseEntity<Task[]> responseEntity = restTemplate.exchange("http://localhost:8082/ems/tasks", HttpMethod.GET,taskHttpEntity,Task[].class);
            model.addAttribute("tasks",responseEntity.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity res = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",res);

        }
        return "allTasks";
    }

    @RequestMapping(value = "/tasks",method = RequestMethod.POST)
    public String saveTask(@ModelAttribute Task task){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(task,httpHeaders);

        ResponseEntity<Task> responseEntity = restTemplate.exchange("http://localhost:8082/ems/tasks", HttpMethod.POST,taskHttpEntity,Task.class);
        if(responseEntity.getStatusCodeValue()==200){
            return "redirect:tasks";
        }
        return "createTask";
    }

    @RequestMapping(value = "/task")
    public String getTask(){
        return "task";
    }

    @RequestMapping(value = "/create-task")
    public String createTask(){
        return "createTask";
    }

}
