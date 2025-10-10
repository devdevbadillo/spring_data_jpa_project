package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.services.interfaces.IOrdersService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {

    private final IOrdersService ordersService;

    public OrdersController(IOrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderInfoOut> findOrderByIdWithInformation(@PathVariable Long orderId) {
        return ResponseEntity.ok(ordersService.findOrderByIdWithInformation(orderId));
    }
}
