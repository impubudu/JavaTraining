package com.example.employeeservice.repository;

import com.example.employeeservice.model.AssignTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignTaskRepository extends JpaRepository<AssignTask,Integer> {
    List<AssignTask> findByComposeKey_Eid(Integer eid);
    List<AssignTask> findByComposeKey_EidAndComposeKey_Pid(Integer eid,Integer pid);
}
