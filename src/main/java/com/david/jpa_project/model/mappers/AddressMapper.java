package com.david.jpa_project.model.mappers;

import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.model.entities.entity.Address;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    default PageOut<AddressOut> toPageOut(Page<Address> page) {
        return PageOut.<AddressOut>builder()
                .content(toAddressOutList(page.getContent()))
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    List<AddressOut> toAddressOutList(List<Address> addresses);

    AddressOut toAddressOut(Address address);
}
