package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.advice.exceptions.StoreProcedureException;
import com.david.jpa_project.controller.dto.out.CreateUserOut;
import com.david.jpa_project.controller.dto.out.CustomerStatsOut;
import com.david.jpa_project.controller.dto.request.RequestCreateCustomerDTO;

public interface ICustomerService {
    CustomerStatsOut getCustomerStats();

    CreateUserOut createCustomer(RequestCreateCustomerDTO requestCreateCustomerDTO) throws StoreProcedureException;
}
