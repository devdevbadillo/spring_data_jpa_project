package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.dto.out.CreateCustomerOut;
import com.david.jpa_project.controller.dto.out.CustomerStatsOut;
import com.david.jpa_project.controller.dto.request.RequestCreateCustomerDTO;
import com.david.jpa_project.services.interfaces.ICustomerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/stats")
    public ResponseEntity<CustomerStatsOut> getCustomerStats() {
        return ResponseEntity.ok(customerService.getCustomerStats());
    }

    @PostMapping(value = "/register", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCustomerOut> createCustomer(
            @RequestBody RequestCreateCustomerDTO requestCreateCustomerDTO
    ) {
        return ResponseEntity.ok(customerService.createCustomer(requestCreateCustomerDTO));
    }
}
