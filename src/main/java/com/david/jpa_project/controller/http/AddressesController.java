package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.doc.APIAddressesDocumentation;
import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.services.interfaces.IAddressesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(value = "/v1/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressesController implements APIAddressesDocumentation {

    private final IAddressesService addressesService;

    public AddressesController(IAddressesService addressesService) {
        this.addressesService = addressesService;
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<PageOut<AddressOut>> getAddressesByCity(
            @PathVariable("city") String city,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(addressesService.getAddressesByCity(city, page, size));
    }

    @GetMapping("/country/{pattern}")
    public ResponseEntity<PageOut<AddressOut>> getAddressesByCountry(
            @PathVariable("pattern") String pattern,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("orderByCity") String orderByCity
    ) {
        return ResponseEntity.ok(addressesService.getAddressesByCountryLike(pattern, page, size, orderByCity));
    }
    
}
