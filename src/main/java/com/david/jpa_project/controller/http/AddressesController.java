package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.services.interfaces.IAddressesService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/addresses")
public class AddressesController {

    private final IAddressesService addressesService;

    public AddressesController(IAddressesService addressesService) {
        this.addressesService = addressesService;
    }

    @GetMapping("/{city}")
    public PageOut<AddressOut> getAddressesByCity(
            @PathVariable("city") String city,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return addressesService.getAddressesByCity(city, page, size);
    }
}
