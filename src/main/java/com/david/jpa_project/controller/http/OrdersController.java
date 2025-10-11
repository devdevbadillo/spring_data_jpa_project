package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.doc.APIOrdersDocumentation;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.services.interfaces.IOrdersService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController implements APIOrdersDocumentation {

    private final IOrdersService ordersService;

    public OrdersController(IOrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderInfoOut> findOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(ordersService.findOrderById(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PageOut<OrderInfoOut>> findOrdersByUser(
            @PathVariable Long userId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(ordersService.findOrdersByUser(userId, page, size));
    }
}
