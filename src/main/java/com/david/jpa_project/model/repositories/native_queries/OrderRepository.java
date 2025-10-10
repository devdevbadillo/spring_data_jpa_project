package com.david.jpa_project.model.repositories.native_queries;

import com.david.jpa_project.model.entities.entity.Order;
import com.david.jpa_project.model.projections.OrderInfo;
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
            p.name as product_name,
            p.price,
            ta.street,
            ta.city,
            ta.state,
            ta.country,
            ta.zip_code,
            tc.phone_number as contact
        FROM demo.t_orders o
            JOIN demo.t_order_products op ON op.order_id = o.id
            JOIN demo.t_products p ON op.product_id = p.id
            JOIN demo.t_addresses ta ON ta.id = o.address_id
            JOIN demo.t_customers tc on tc.id  = ta.customer_id
        WHERE o.id = :orderId
        """, nativeQuery = true)
    List<OrderInfo> findOrderByIdWithInformation(Long orderId);
}
