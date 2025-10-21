package com.david.jpa_project.controller;

import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.http.OrdersController;
import com.david.jpa_project.model.entities.enums.OrderStatus;
import com.david.jpa_project.services.interfaces.IOrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrdersController.class)
class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IOrdersService ordersService;

    @Test
    void findOrderById_WhenOrderExists_ReturnsOrder() throws Exception {
        // Arrange
        Long orderId = 1L;
        OrderInfoOut expectedOrder = new OrderInfoOut();
        expectedOrder.setOrderId(orderId);
        expectedOrder.setOrderStatus(OrderStatus.COMPLETED.getValue());
        expectedOrder.setOrderAmount(100.50);

        when(ordersService.findOrderById(orderId)).thenReturn(expectedOrder);

        // Act & Assert
        mockMvc.perform(get("/v1/orders/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId").value(orderId))
                .andExpect(jsonPath("$.orderStatus").value("COMPLETED"))
                .andExpect(jsonPath("$.orderAmount").value(100.50));
    }

    @Test
    void findOrderById_WhenOrderDoesNotExist_ShouldThrowResourceNotFoundException() throws Exception {
        // Arrange
        Long orderId = 100L;
        when(ordersService.findOrderById(orderId)).thenThrow(
                new ResourceNotFoundException("Order not found")
        );

        // Act & Assert
        mockMvc.perform(get("/v1/orders/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}