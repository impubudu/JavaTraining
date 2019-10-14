package com.example.Projectservice.controller;

import com.example.Projectservice.service.ProjectServiceImpl;
import com.impubudu.emscloud.commons.model.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ems")
public class ProjectController {
    @Autowired
    ProjectServiceImpl projectService;

    @RequestMapping(value = "/projects")
    @PreAuthorize("hasAuthority('read_profile')")
    public List<Project> getProjects(){
        return projectService.getProjects();
    }

    @RequestMapping(value = "/projects-page")
    @PreAuthorize("hasAuthority('read_profile')")
    public Page<Project> getProjectsPage(@RequestParam("page") Optional<Integer> page,
                                         @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        return projectService.getProjectsPage(PageRequest.of(currentPage - 1, pageSize));
    }

    @RequestMapping(value = "/projects",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('write_profile')")
    public Project save(@RequestBody Project project){
        return projectService.save(project);
    }

    @RequestMapping(value = "/projects/{ids}",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public List<Project> getTask(@PathVariable List<Integer> ids){
        return projectService.getProjectsList(ids);
    }
}
