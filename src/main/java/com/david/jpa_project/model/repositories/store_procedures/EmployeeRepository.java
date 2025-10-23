package com.david.jpa_project.model.repositories.store_procedures;

import com.david.jpa_project.model.entities.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
