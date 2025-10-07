package com.david.jpa_project.controller.dto.out;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AddressOut {

    private String country;
    private String state;
    private String city;
    private String street;
    private String zipCode;

}
