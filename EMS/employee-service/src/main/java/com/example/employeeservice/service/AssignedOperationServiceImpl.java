package com.example.employeeservice.service;

import com.example.employeeservice.config.AccessTokenConfigurer;
import com.example.employeeservice.repository.AssignedOperationRepository;
import com.impubudu.emscloud.commons.model.employee.AssignedOperation;
import com.impubudu.emscloud.commons.model.project.Project;
import com.impubudu.emscloud.commons.model.task.Task;
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
public class AssignedOperationServiceImpl implements AssignedOperationService {
    @Autowired
    AssignedOperationRepository assignedOperationRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplateAssignedOperation(){
        return new RestTemplate();
    }

    public List<AssignedOperation> saveAssignedOperation(List<AssignedOperation> assignedOperations){
        return assignedOperationRepository.saveAll(assignedOperations);
    }

    public List<Project> getProjects(Integer eid){
        List<AssignedOperation> assignedOperations = assignedOperationRepository.findByOperationCompositeKey_Eid(eid);

        String projectIds = assignedOperations.stream().map(s->String.valueOf(s.getOperationCompositeKey().getPid())).collect(Collectors.joining(","));

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
        List<AssignedOperation> assignedOperations = assignedOperationRepository.findByOperationCompositeKey_EidAndOperationCompositeKey_Pid(eid,pid);
        String taskIds = assignedOperations.stream().map(s->String.valueOf(s.getOperationCompositeKey().getTid())).collect(Collectors.joining(","));
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
