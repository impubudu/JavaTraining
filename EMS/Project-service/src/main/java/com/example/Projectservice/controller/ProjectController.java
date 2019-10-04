package com.example.Projectservice.controller;

import com.example.Projectservice.model.Project;
import com.example.Projectservice.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ems")
public class ProjectController {
    @Autowired
    ProjectServiceImpl projectService;

    @RequestMapping(value = "/projects")
    public List<Project> getProjects(){
        return projectService.getProjects();
    }

    @RequestMapping(value = "/projects",method = RequestMethod.POST)
    public Project save(@RequestBody Project project){
        return projectService.save(project);
    }
}
