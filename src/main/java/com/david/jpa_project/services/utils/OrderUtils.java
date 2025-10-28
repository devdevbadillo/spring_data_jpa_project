package com.david.jpa_project.services.utils;

import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.model.entities.entity.Order;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.projections.OrderInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderUtils {
    public static OrderInfoOut mapOrderToOrderInfoOut(Order order) {
        OrderInfoOut orderInfo = new OrderInfoOut();

        orderInfo.setOrderId(order.getId());
        orderInfo.setOrderStatus(order.getStatus().toString());
        orderInfo.setPhoneContact(order.getPhoneContact());
        orderInfo.setAddress(mapAddressFromOrder(order));
        orderInfo.setProducts(aggregateProductsFromOrder(order));
        orderInfo.setOrderAmount(calculateTotalAmountFromOrder(order));

        return orderInfo;
    }

    public static void mapOrderInfoFromProjection(OrderInfoOut orderInfo, OrderInfo projection) {
        orderInfo.setOrderId(projection.getOrderId());
        orderInfo.setPhoneContact(projection.getPhoneContact());
        orderInfo.setOrderStatus(projection.getOrderStatus());
    }

    public static AddressOut mapAddressFromProjection(OrderInfo projection) {
        AddressOut address = new AddressOut();
        address.setStreet(projection.getStreet());
        address.setCity(projection.getCity());
        address.setState(projection.getState());
        address.setCountry(projection.getCountry());
        address.setZipCode(projection.getZipCode());
        return address;
    }

    public static AddressOut mapAddressFromOrder(Order order) {
        AddressOut address = new AddressOut();
        address.setStreet(order.getAddress().getStreet());
        address.setCity(order.getAddress().getCity());
        address.setState(order.getAddress().getState());
        address.setCountry(order.getAddress().getCountry());
        address.setZipCode(order.getAddress().getZipCode());
        return address;
    }

    public static List<ProductOut> aggregateProductsFromOrder(Order order) {
        return aggregateProducts(
                order.getProducts().stream()
                        .map(OrderUtils::mapProductToProductOut)
                        .collect(Collectors.toList())
        );
    }

    public static List<ProductOut> aggregateProductsFromProjections(List<OrderInfo> projections) {
        return aggregateProducts(
                projections.stream()
                        .map(OrderUtils::mapProjectionToProductOut)
                        .collect(Collectors.toList())
        );
    }

    public static List<ProductOut> aggregateProducts(List<ProductOut> products) {
        Map<String, ProductOut> productMap = new HashMap<>();

        for (ProductOut product : products) {
            productMap.merge(product.getName(), product, (existing, newProduct) -> {
                existing.setQuantity(existing.getQuantity() + newProduct.getQuantity());
                return existing;
            });
        }

        return new ArrayList<>(productMap.values());
    }

    public static ProductOut mapProductToProductOut(Product product) {
        ProductOut productOut = new ProductOut();
        productOut.setName(product.getName());
        productOut.setPrice(product.getPrice());
        productOut.setQuantity(1);
        return productOut;
    }

    public static ProductOut mapProjectionToProductOut(OrderInfo projection) {
        ProductOut productOut = new ProductOut();
        productOut.setName(projection.getProductName());
        productOut.setPrice(projection.getPrice());
        productOut.setQuantity(1);
        return productOut;
    }

    public static double calculateTotalAmount(List<OrderInfo> projections) {
        return projections.stream()
                .mapToDouble(OrderInfo::getPrice)
                .sum();
    }

    public static double calculateTotalAmountFromOrder(Order order) {
        return order.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
