package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.advice.exceptions.StoreProcedureException;
import com.david.jpa_project.controller.dto.out.CreateUserOut;
import com.david.jpa_project.controller.dto.request.RequestCreateEmployeeDTO;

public interface IEmployeeService {
    CreateUserOut createEmployee(RequestCreateEmployeeDTO requestCreateEmployeeDTO, Long userExecutor) throws StoreProcedureException;
}
