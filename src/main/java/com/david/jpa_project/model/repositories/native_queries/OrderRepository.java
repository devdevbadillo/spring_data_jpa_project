package com.david.jpa_project.model.repositories.native_queries;

import com.david.jpa_project.model.entities.entity.Order;
import com.david.jpa_project.model.projections.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
        SELECT
            o.id as order_id,
            o.status as order_status,
            o.phone_contact as phone_contact,
            p.name as product_name,
            p.price,
            a.street,
            a.city,
            a.state,
            a.country,
            a.zip_code
        FROM demo.t_orders o
            JOIN demo.t_order_products op ON op.order_id = o.id
            JOIN demo.t_products p ON op.product_id = p.id
            JOIN demo.t_addresses a ON a.id = o.address_id
        WHERE o.id = :orderId
        """, nativeQuery = true)
    List<OrderInfo> findOrderById(Long orderId);

    @Query(value = """
        SELECT
             o.*
        FROM demo.t_orders o
        JOIN demo.t_addresses a ON a.id = o.address_id
        WHERE a.customer_id = :userId
    """, nativeQuery = true)
    Page<Order> findOrdersByUser(Long userId, Pageable pageable);
}
