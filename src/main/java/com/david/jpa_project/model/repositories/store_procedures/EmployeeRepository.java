package com.david.jpa_project.model.repositories.store_procedures;

import com.david.jpa_project.model.entities.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Procedure(name = "registerEmployee")
    Map<String, Object> registerEmployee(
            @Param("p_executor_user_id") Long executorUserId,
            @Param("p_name") String name,
            @Param("p_last_name") String lastName,
            @Param("p_email") String email,
            @Param("p_password") String password,
            @Param("p_job_title") String jobTitle
    );

}
