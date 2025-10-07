package com.david.jpa_project.model.repositories.derived_queries;

import com.david.jpa_project.model.entities.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomersByPhoneNumberStartingWith(String start);

}
