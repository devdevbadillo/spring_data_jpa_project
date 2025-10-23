package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.dto.out.CreateCustomerOut;
import com.david.jpa_project.controller.dto.out.CustomerStatsOut;
import com.david.jpa_project.controller.dto.request.RequestCreateCustomerDTO;

public interface ICustomerService {
    CustomerStatsOut getCustomerStats();

    CreateCustomerOut createCustomer(RequestCreateCustomerDTO requestCreateCustomerDTO);
}
