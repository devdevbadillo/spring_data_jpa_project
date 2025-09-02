package com.david.jpa_project.services.customer.orders;

import com.david.jpa_project.model.repositories.pagination_and_sort.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements IOrdersService {

    private final OrderRepository orderRepository;

    public OrdersServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

}
