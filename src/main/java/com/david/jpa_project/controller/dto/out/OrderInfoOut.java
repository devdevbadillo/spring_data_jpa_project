package com.david.jpa_project.controller.dto.out;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderInfoOut {
    private Long orderId;
    private Double orderAmount;
    private String orderStatus;
    private AddressOut address;
    private List<ProductOut> products;
    private String phoneContact;
}
