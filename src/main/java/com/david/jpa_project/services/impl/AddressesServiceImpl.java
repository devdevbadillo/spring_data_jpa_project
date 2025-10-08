package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.model.entities.entity.Address;
import com.david.jpa_project.model.mappers.AddressMapper;
import com.david.jpa_project.model.repositories.jpql.AddressRepository;
import com.david.jpa_project.services.interfaces.IAddressesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AddressesServiceImpl implements IAddressesService {
    private final AddressRepository addressRepository;
    private final AddressMapper mapper;

    public AddressesServiceImpl(AddressRepository addressRepository, AddressMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public PageOut<AddressOut> getAddressesByCity(String city, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addresses = addressRepository.findAddressesByCity(city, pageable);

        return mapper.toPageOut(addresses);
    }

    @Override
    public PageOut<AddressOut> getAddressesByCountryLike(String pattern, int page, int size, String orderByCity) {
        Sort.Direction direction = orderByCity.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Sort sortByCity = Sort.by(direction, "city");

        Pageable pageable = PageRequest.of(page, size, sortByCity);
        Page<Address> addresses = addressRepository.findAddressesByCountryLike(pattern, pageable);

        return mapper.toPageOut(addresses);
    }
}
