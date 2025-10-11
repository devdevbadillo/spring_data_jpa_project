package com.david.jpa_project.model.projections;

public interface OrderInfo {
    Long getOrderId();

    String getProductName();

    Double getPrice();

    String getStreet();

    String getCity();

    String getCountry();

    String getZipCode();

    String getState();

    String getPhoneContact();

    String getOrderStatus();
}
