package com.david.jpa_project.services.customer.addresses;

import com.david.jpa_project.model.repositories.jpql.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressesServiceImpl implements IAddressesService{
    private final AddressRepository addressRepository;

    public AddressesServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


}
