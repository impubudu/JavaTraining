package com.example.Projectservice.service;

import com.example.Projectservice.exception.ProjectNotFoundException;
import com.example.Projectservice.model.Project;
import com.example.Projectservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl {
    @Autowired
    ProjectRepository projectRepository;

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public Page<Project> getProjectsPage(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Project save(Project project){
        return projectRepository.save(project);
    }

    public Project getProject(Integer id){
        Optional<Project> optionalStudent = projectRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new ProjectNotFoundException("Project can't find.Id is wrong");
        }
        return optionalStudent.get();
    }

    public List<Project> getProjectsList(List<Integer> ids){
        return projectRepository.findByIdIn(ids);
    }
}
