package com.david.jpa_project;

import com.david.jpa_project.model.entities.entity.Address;
import com.david.jpa_project.model.entities.entity.Customer;
import com.david.jpa_project.model.entities.entity.Order;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.entities.enums.OrderStatus;

import java.time.Instant;

public class Factory {
    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setName("Juan");
        customer.setLastName("Rodriguez Perez");
        customer.setCreatedAt(Instant.now());
        customer.setUpdatedAt(Instant.now());
        customer.setPhoneNumber("5512345678");
        return customer;
    }

    public static Address createAddress() {
        Address address = new Address();
        address.setStreet("Calle Principal 123");
        address.setCity("CDMX");
        address.setState("Ciudad de México");
        address.setCountry("México");
        address.setZipCode("12345");
        address.setCreatedAt(Instant.now());
        address.setUpdatedAt(Instant.now());
        return address;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setName("Laptop Dell");
        product.setDescription("Laptop Dell i7 16GB 512GB");
        product.setPrice(15000.00);
        product.setStockQuantity(30);
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        product.setUserAudit("test@test.com");
        return product;
    }

    public static Order createOrder() {
        Order order = new Order();
        OrderStatus status = OrderStatus.PENDING;
        order.setStatus(status);
        order.setPhoneContact("5512345678");
        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());
        return order;
    }
}
