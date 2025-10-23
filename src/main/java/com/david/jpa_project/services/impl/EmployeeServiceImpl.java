package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.advice.exceptions.StoreProcedureException;
import com.david.jpa_project.controller.dto.out.CreateUserOut;
import com.david.jpa_project.controller.dto.request.RequestCreateEmployeeDTO;
import com.david.jpa_project.model.repositories.store_procedures.EmployeeRepository;
import com.david.jpa_project.services.interfaces.IEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public CreateUserOut createEmployee(RequestCreateEmployeeDTO requestCreateEmployeeDTO, Long userExecutor) throws StoreProcedureException {
        var result = employeeRepository.registerEmployee(
                userExecutor,
                requestCreateEmployeeDTO.employee().name(),
                requestCreateEmployeeDTO.employee().lastName(),
                requestCreateEmployeeDTO.employee().email(),
                requestCreateEmployeeDTO.employee().password(),
                requestCreateEmployeeDTO.employee().jobTitle()
        );

        String message = (String) result.get("p_message");
        Long statusCode = (Long) result.get("p_status_code");

        if (statusCode != 200){
            throw new StoreProcedureException(message, statusCode);
        }

        Long p_user_id = (Long) result.get("p_user_id");

        return new CreateUserOut(p_user_id, message);
    }
}
