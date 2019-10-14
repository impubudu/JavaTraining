package com.example.Projectservice.repository;

import com.impubudu.emscloud.commons.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    List<Project> findByIdIn(List<Integer> id);
}
