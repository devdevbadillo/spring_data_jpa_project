package com.david.jpa_project.model.repositories.derived_queries;

import com.david.jpa_project.model.entities.entity.Employee;
import com.david.jpa_project.model.entities.enums.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeById(Long id);

    void deleteEmployeeById(Long id);

    List<Employee> findEmployeesByJobTitle(JobTitle jobTitle);

    List<Employee> findEmployeesByJobTitleIn(List<JobTitle> jobTitles);
}
