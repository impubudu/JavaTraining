package com.example.emsui.controller;

import com.example.emsui.config.AccessTokenConfigurer;
import com.example.emsui.model.*;
import com.impubudu.emscloud.commons.model.employee.AssignedOperation;
import com.impubudu.emscloud.commons.model.employee.Employee;
import com.impubudu.emscloud.commons.model.project.Project;
import com.impubudu.emscloud.commons.model.task.Task;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


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
                antMatchers("/create**").access("hasRole('ROLE_manager')").
                anyRequest().
                authenticated().and().
                logout().logoutSuccessUrl("/").
                deleteCookies("KS","JSESSIONID").invalidateHttpSession(true);
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
    public String getEmployees(Model model, @RequestParam("page") Optional<Integer> page){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);
        try {
            ResponseEntity<HashMap> responseEntity = restTemplate.exchange("http://employee-service:8080/ems/employees-page?page={page}", HttpMethod.GET,employeeHttpEntity,HashMap.class,page.isPresent()?page.get():1);
            model.addAttribute("employees",responseEntity.getBody().get("content"));
            model.addAttribute("employeesPage",responseEntity.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "NewallEmployees";
    }

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute Employee employee,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(employee,httpHeaders);

        try {
            ResponseEntity<Employee> responseEntity = restTemplate.exchange("http://employee-service:8080/ems/employees", HttpMethod.POST,employeeHttpEntity,Employee.class);
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
    public String getProjects(Model model,@RequestParam("page") Optional<Integer> page){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(httpHeaders);
        try {
            ResponseEntity<HashMap> responseEntity = restTemplate.exchange("http://project-service:8081/ems/projects-page?page={page}", HttpMethod.GET,projectHttpEntity,HashMap.class,page.isPresent()?page.get():1);
            model.addAttribute("projects",responseEntity.getBody().get("content"));
            model.addAttribute("projectsPage",responseEntity.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "NewallProjects";
    }

    @RequestMapping(value = "/projects",method = RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(project,httpHeaders);

        try {
            ResponseEntity<Project> responseEntity = restTemplate.exchange("http://project-service:8081/ems/projects", HttpMethod.POST,projectHttpEntity,Project.class);
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "redirect:projects";
    }

    @RequestMapping(value = "/create-project")
    public String createProject(Model model){
        model.addAttribute("project",new Project());
        return "createProject";
    }

    @RequestMapping(value = "/tasks")
    public String getTasks(Model model,@RequestParam("page") Optional<Integer> page){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(httpHeaders);
        try {
            ResponseEntity<HashMap> responseEntity = restTemplate.exchange("http://task-service:8082/ems/tasks-page?page={page}", HttpMethod.GET,taskHttpEntity,HashMap.class,page.isPresent()?page.get():1);
            model.addAttribute("tasks",responseEntity.getBody().get("content"));
            model.addAttribute("tasksPage",responseEntity.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "NewallTasks";
    }

    @RequestMapping(value = "/tasks",method = RequestMethod.POST)
    public String saveTask(@ModelAttribute Task task,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(task,httpHeaders);
        try {
            ResponseEntity<Task> responseEntity = restTemplate.exchange("http://task-service:8082/ems/tasks", HttpMethod.POST,taskHttpEntity,Task.class);
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "redirect:tasks";
    }

    @RequestMapping(value = "/create-task")
    public String createTask(Model model){
        model.addAttribute("project",new Project());
        return "createTask";
    }

    @RequestMapping(value = "/create-operation")
    public String doOperations(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);
        try {
            ResponseEntity<Employee[]> responseEntity1 = restTemplate.exchange("http://employee-service:8080/ems/employees", HttpMethod.GET,employeeHttpEntity,Employee[].class);
            model.addAttribute("employees",responseEntity1.getBody());
            HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(httpHeaders);
            ResponseEntity<Project[]> responseEntity2 = restTemplate.exchange("http://project-service:8081/ems/projects", HttpMethod.GET,projectHttpEntity,Project[].class);
            model.addAttribute("projects",responseEntity2.getBody());
            HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(httpHeaders);
            ResponseEntity<Task[]> responseEntity3 = restTemplate.exchange("http://task-service:8082/ems/tasks", HttpMethod.GET,taskHttpEntity,Task[].class);
            model.addAttribute("tasks",responseEntity3.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "createOperation";
    }

    @RequestMapping(value = "/assignedOperation",method = RequestMethod.POST)
    public String assignProject(@ModelAttribute AssignedOperationList assignedOperationList, Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        List<AssignedOperation> assignedOperations = new ArrayList<>();
        assignedOperationList.getTid().forEach((t)->{
            AssignedOperation operation = new AssignedOperation();
            AssignedOperation.OperationCompositeKey composeKey = new AssignedOperation.OperationCompositeKey();
            composeKey.setEid(assignedOperationList.getEid());
            composeKey.setPid(assignedOperationList.getPid());
            composeKey.setTid(t);
            operation.setOperationCompositeKey(composeKey);
            assignedOperations.add(operation);
        });

        HttpEntity<List> assignTaskHttpEntity = new HttpEntity<List>(assignedOperations,httpHeaders);
        try {
            ResponseEntity<AssignedOperation[]> responseEntity = restTemplate.exchange("http://employee-service:8080/ems/assignedOperation", HttpMethod.POST,assignTaskHttpEntity,AssignedOperation[].class);
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "redirect:create-operation";
    }

    @RequestMapping(value = "/employees/{id}")
    public String getEmployee(@PathVariable Integer id ,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);

        try {
            ResponseEntity<Employee> responseEntity1 = restTemplate.exchange("http://employee-service:8080/ems/employees/{id}", HttpMethod.GET,employeeHttpEntity,Employee.class,id);
            model.addAttribute("employee",responseEntity1.getBody());
            ResponseEntity<Project[]> responseEntity2 = restTemplate.exchange("http://employee-service:8080/ems/employees/{id}/projects", HttpMethod.GET,employeeHttpEntity,Project[].class,id);
            model.addAttribute("projects",responseEntity2.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "Newview";
    }

    @RequestMapping(value = "/employees/{id}/projects")
    public String getEmployeeProjects(@PathVariable Integer id ,Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<Employee>(httpHeaders);
        try {
            ResponseEntity<Project[]> responseEntity = restTemplate.exchange("http://employee-service:8080/ems/employees/{id}/projects", HttpMethod.GET,employeeHttpEntity,Project[].class,id);
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
            ResponseEntity<Project[]> responseEntity1 = restTemplate.exchange("http://project-service:8081/ems/projects/{pid}", HttpMethod.GET,employeeHttpEntity,Project[].class,pid);
            model.addAttribute("project",responseEntity1.getBody()[0]);
            ResponseEntity<Task[]> responseEntity2 = restTemplate.exchange("http://employee-service:8080/ems/employees/{eid}/projects/{pid}/tasks", HttpMethod.GET,employeeHttpEntity,Task[].class,eid,pid);
            model.addAttribute("tasks",responseEntity2.getBody());
        }catch (HttpStatusCodeException se){
            ResponseEntity responseEntity = ResponseEntity.status(se.getRawStatusCode()).headers(se.getResponseHeaders()).body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "NewviewTasks";
    }
}
