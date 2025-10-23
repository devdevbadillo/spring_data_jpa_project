package com.david.jpa_project.model.repositories.store_procedures;

import com.david.jpa_project.model.entities.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Procedure(name = "Customer.getStats")
    Map<String, Object> getCustomerStatistics();

}
