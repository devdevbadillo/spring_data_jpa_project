package com.david.jpa_project.model.repositories.derived_queries;

import com.david.jpa_project.model.entities.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
