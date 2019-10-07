package com.example.emsui.controller;

import com.example.emsui.model.AssignTask;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


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
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "allEmployees";
    }

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute Employee employee,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(employee,httpHeaders);

        try {
            ResponseEntity<Employee> responseEntity = restTemplate.exchange("http://localhost:8080/ems/employees", HttpMethod.POST,employeeHttpEntity,Employee.class);
        } catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "redirect:employees";
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
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "allProjects";
    }

    @RequestMapping(value = "/projects",method = RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(project,httpHeaders);

        try {
            ResponseEntity<Project> responseEntity = restTemplate.exchange("http://localhost:8081/ems/projects", HttpMethod.POST,projectHttpEntity,Project.class);
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "redirect:projects";
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
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "allTasks";
    }

    @RequestMapping(value = "/tasks",method = RequestMethod.POST)
    public String saveTask(@ModelAttribute Task task,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(task,httpHeaders);
        try {
            ResponseEntity<Task> responseEntity = restTemplate.exchange("http://localhost:8082/ems/tasks", HttpMethod.POST,taskHttpEntity,Task.class);
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "redirect:tasks";
    }

    @RequestMapping(value = "/create-task")
    public String createTask(){
        return "createTask";
    }

    @RequestMapping(value = "/operations")
    public String doOperations(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);
        try {
            ResponseEntity<Employee[]> responseEntity1 = restTemplate.exchange("http://localhost:8080/ems/employees", HttpMethod.GET,employeeHttpEntity,Employee[].class);
            model.addAttribute("employees",responseEntity1.getBody());
            HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(httpHeaders);
            ResponseEntity<Project[]> responseEntity2 = restTemplate.exchange("http://localhost:8081/ems/projects", HttpMethod.GET,projectHttpEntity,Project[].class);
            model.addAttribute("projects",responseEntity2.getBody());
            HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(httpHeaders);
            ResponseEntity<Task[]> responseEntity3 = restTemplate.exchange("http://localhost:8082/ems/tasks", HttpMethod.GET,taskHttpEntity,Task[].class);
            model.addAttribute("tasks",responseEntity3.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "operations";
    }

    @RequestMapping(value = "/assign",method = RequestMethod.POST)
    public String assignProject(@ModelAttribute AssignTask assignTask,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<AssignTask> assignTaskHttpEntity = new HttpEntity<AssignTask>(assignTask,httpHeaders);
        try {
            ResponseEntity<AssignTask> responseEntity = restTemplate.exchange("http://localhost:8080/ems/assign", HttpMethod.POST,assignTaskHttpEntity,AssignTask.class);
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "redirect:operations";
    }

    @RequestMapping(value = "/employees/{id}")
    public String getEmployee(@PathVariable Integer id ,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);

        try {
            ResponseEntity<Employee> responseEntity1 = restTemplate.exchange("http://localhost:8080/ems/employees/{id}", HttpMethod.GET,employeeHttpEntity,Employee.class,id);
            model.addAttribute("employee",responseEntity1.getBody());
            ResponseEntity<Project[]> responseEntity2 = restTemplate.exchange("http://localhost:8080/ems/employees/{id}/projects", HttpMethod.GET,employeeHttpEntity,Project[].class,id);
            model.addAttribute("projects",responseEntity2.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "view";
    }

    @RequestMapping(value = "/employees/{id}/projects")
    public String getEmployeeProjects(@PathVariable Integer id ,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);
        try {
            ResponseEntity<Project[]> responseEntity = restTemplate.exchange("http://localhost:8080/ems/employees/{id}/projects", HttpMethod.GET,employeeHttpEntity,Project[].class,id);
            model.addAttribute("projects",responseEntity.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "viewProjects";
    }

    @RequestMapping(value = "/employees/{eid}/projects/{pid}/tasks")
    public String getEmployeeProjects(@PathVariable Integer eid ,@PathVariable Integer pid,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);

        try {
            ResponseEntity<Task[]> responseEntity = restTemplate.exchange("http://localhost:8080/ems/employees/{eid}/projects/{pid}/tasks", HttpMethod.GET,employeeHttpEntity,Task[].class,eid,pid);
            model.addAttribute("tasks",responseEntity.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "viewTasks";
    }
}
