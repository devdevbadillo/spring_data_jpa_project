package com.david.jpa_project.controller.http.customer.addresses;

import com.david.jpa_project.services.customer.addresses.IAddressesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressesController {

    private final IAddressesService addressesService;

    public AddressesController(IAddressesService addressesService) {
        this.addressesService = addressesService;
    }

}
