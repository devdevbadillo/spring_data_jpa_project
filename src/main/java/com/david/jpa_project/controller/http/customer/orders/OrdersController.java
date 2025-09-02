package com.david.jpa_project.controller.http.customer.orders;

import com.david.jpa_project.services.customer.orders.IOrdersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final IOrdersService ordersService;

    public OrdersController(IOrdersService ordersService) {
        this.ordersService = ordersService;
    }

}
