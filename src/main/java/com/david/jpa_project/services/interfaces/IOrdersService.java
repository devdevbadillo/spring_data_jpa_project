package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.advice.exceptions.BusinessException;
import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.PageOut;

import java.util.List;

public interface IOrdersService {

    OrderInfoOut findOrderById(Long orderId) throws ResourceNotFoundException;

    PageOut<OrderInfoOut> findOrdersByUser(Long userId, int page, int size);

    OrderInfoOut createOrder(List<Long> productIds, Long addressId) throws  ResourceNotFoundException, BusinessException;
}
