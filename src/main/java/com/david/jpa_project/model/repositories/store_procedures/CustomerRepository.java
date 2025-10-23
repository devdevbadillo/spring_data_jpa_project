package com.david.jpa_project.model.repositories.store_procedures;

import com.david.jpa_project.controller.dto.out.CustomerStatsOut;
import com.david.jpa_project.model.entities.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM demo.fn_get_customer_stats()", nativeQuery = true)
    CustomerStatsOut getCustomerStatistics();

}
