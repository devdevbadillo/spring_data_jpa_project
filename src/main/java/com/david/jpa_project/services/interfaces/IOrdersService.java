package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.dto.out.OrderInfoOut;

public interface IOrdersService {

    OrderInfoOut findOrderByIdWithInformation(Long orderId);

}
