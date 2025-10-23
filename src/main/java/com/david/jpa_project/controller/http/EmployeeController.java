package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.advice.exceptions.StoreProcedureException;
import com.david.jpa_project.controller.dto.out.CreateUserOut;
import com.david.jpa_project.controller.dto.request.RequestCreateEmployeeDTO;
import com.david.jpa_project.services.interfaces.IEmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserOut> createEmployee(
            @RequestBody RequestCreateEmployeeDTO requestCreateEmployeeDTO,
            @RequestHeader("user-executor") Long userExecutor
    ) throws StoreProcedureException {
        return ResponseEntity.ok(employeeService.createEmployee(requestCreateEmployeeDTO, userExecutor));
    }
}
