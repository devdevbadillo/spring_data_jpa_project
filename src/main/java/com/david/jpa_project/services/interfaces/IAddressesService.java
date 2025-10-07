package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.PageOut;

public interface IAddressesService {

    PageOut<AddressOut> getAddressesByCity(String city, int page, int size);

}
