package com.david.jpa_project.model.repositories.pagination_and_sort;

import com.david.jpa_project.model.entities.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
