package com.david.jpa_project.model.mappers;

import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.model.entities.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderInfoMapper extends BaseMapper<OrderInfoOut, Order>{
}
