package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.services.interfaces.IAddressesService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(value = "/v1/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressesController {

    private final IAddressesService addressesService;

    public AddressesController(IAddressesService addressesService) {
        this.addressesService = addressesService;
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<PageOut<AddressOut>> getAddressesByCity(
            @PathVariable("city") String city,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return ResponseEntity.ok(addressesService.getAddressesByCity(city, page, size));
    }

    @GetMapping("/country/{pattern}")
    public ResponseEntity<PageOut<AddressOut>> getAddressesByCountry(
            @PathVariable("pattern") String pattern,
            @RequestParam("page") @Min(value = 0, message = "El valor de la pagina tiene que ser mayor o igual a 0") int page,
            @RequestParam("size") @Min(value = 1, message = "El valor del tamaño tiene que ser mayor o igual a 1") int size,
            @RequestParam("orderByCity") @Pattern(regexp = "asc|desc", message = "El valor de orderByCity tiene que ser asc o desc") String orderByCity
    ) {
        return ResponseEntity.ok(addressesService.getAddressesByCountryLike(pattern, page, size, orderByCity));
    }
    
}
