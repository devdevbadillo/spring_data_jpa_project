package com.david.jpa_project.model.mappers;

import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.model.entities.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends  BaseMapper<AddressOut, Address> {

}
