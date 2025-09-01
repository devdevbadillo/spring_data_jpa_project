package com.david.jpa_project.model.repositories;

import com.david.jpa_project.model.entities.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
