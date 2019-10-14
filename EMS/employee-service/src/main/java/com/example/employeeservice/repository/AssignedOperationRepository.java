package com.example.employeeservice.repository;

import com.impubudu.emscloud.commons.model.employee.AssignedOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignedOperationRepository extends JpaRepository<AssignedOperation,Integer> {
    List<AssignedOperation> findByOperationCompositeKey_Eid(Integer eid);
    List<AssignedOperation> findByOperationCompositeKey_EidAndOperationCompositeKey_Pid(Integer eid,Integer pid);
}
