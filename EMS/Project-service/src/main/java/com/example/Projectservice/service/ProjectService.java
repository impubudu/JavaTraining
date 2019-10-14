package com.example.Projectservice.service;

import com.impubudu.emscloud.commons.model.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    public List<Project> getProjects();

    public Page<Project> getProjectsPage(Pageable pageable);

    public Project save(Project project);

    public Project getProject(Integer id);

    public List<Project> getProjectsList(List<Integer> ids);
}
