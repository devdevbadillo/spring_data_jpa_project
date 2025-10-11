package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.PageOut;

import java.util.List;

public interface IOrdersService {

    OrderInfoOut findOrderById(Long orderId);

    PageOut<OrderInfoOut> findOrdersByUser(Long userId, int page, int size);
}
