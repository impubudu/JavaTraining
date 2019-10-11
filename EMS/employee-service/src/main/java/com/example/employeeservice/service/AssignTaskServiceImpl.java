package com.example.employeeservice.service;

import com.example.employeeservice.config.AccessTokenConfigurer;
import com.example.employeeservice.repository.AssignTaskRepository;
import com.impubudu.emscloud.commons.model.AssignTask;
import com.impubudu.emscloud.commons.model.Project;
import com.impubudu.emscloud.commons.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignTaskServiceImpl implements AssignTaskService{
    @Autowired
    AssignTaskRepository assignTaskRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplateAssignTask(){
        return new RestTemplate();
    }

    public List<AssignTask> saveAssignTask(List<AssignTask> assignTasks){
        return assignTaskRepository.saveAll(assignTasks);
    }

    public List<Project> getProjects(Integer eid){
        List<AssignTask> assignTasks = assignTaskRepository.findByComposeKey_Eid(eid);

        String projectIds = assignTasks.stream().map(s->String.valueOf(s.getComposeKey().getPid())).collect(Collectors.joining(","));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(httpHeaders);

        if(!projectIds.equals("")){
            ResponseEntity<List> responseEntity = restTemplate.exchange("http://project-service:8081/ems/projects/{ids}", HttpMethod.GET, projectHttpEntity, List.class, projectIds);
            return responseEntity.getBody();
        }

        return null;
    }

    public List<Task> getTasks(Integer eid, Integer pid){
        List<AssignTask> assignTasks = assignTaskRepository.findByComposeKey_EidAndComposeKey_Pid(eid,pid);
        String taskIds = assignTasks.stream().map(s->String.valueOf(s.getComposeKey().getTid())).collect(Collectors.joining(","));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(httpHeaders);
        if(!taskIds.equals("")) {
            ResponseEntity<List> responseEntity = restTemplate.exchange("http://task-service:8082/ems/tasks/{ids}", HttpMethod.GET, taskHttpEntity, List.class, taskIds);
            return responseEntity.getBody();
        }
        return null;
    }
}
