package com.david.jpa_project.model.repositories.pagination_and_sort;

import com.david.jpa_project.model.entities.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
