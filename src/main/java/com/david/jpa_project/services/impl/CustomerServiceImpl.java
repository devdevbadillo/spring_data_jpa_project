package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.advice.exceptions.StoreProcedureException;
import com.david.jpa_project.controller.dto.out.CreateUserOut;
import com.david.jpa_project.controller.dto.out.CustomerStatsOut;
import com.david.jpa_project.controller.dto.request.RequestCreateCustomerDTO;
import com.david.jpa_project.model.repositories.store_procedures.CustomerRepository;
import com.david.jpa_project.services.interfaces.ICustomerService;
import com.david.jpa_project.services.utils.store_procedure.CustomerSPs;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerSPs customerSPs;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerSPs customerSPs) {
        this.customerRepository = customerRepository;
        this.customerSPs = customerSPs;
    }

    @Override
    public CustomerStatsOut getCustomerStats() {
        return customerRepository.getCustomerStatistics();
    }

    @Override
    public CreateUserOut createCustomer(RequestCreateCustomerDTO requestCreateCustomerDTO) throws StoreProcedureException {
        StoredProcedureQuery query = customerSPs.setParametersForRegisterCustomer(requestCreateCustomerDTO);

        query.execute();

        String message = (String) query.getOutputParameterValue("p_message");
        Long statusCode = (Long) query.getOutputParameterValue("p_status_code");

        if (statusCode != 200){
            throw new StoreProcedureException(message, statusCode);
        }

        Long userId = (Long) query.getOutputParameterValue("p_user_id");
        return new CreateUserOut(userId, message);
    }

}
