package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.model.entities.entity.Order;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.mappers.OrderInfoMapper;
import com.david.jpa_project.model.projections.OrderInfo;
import com.david.jpa_project.model.repositories.native_queries.OrderRepository;
import com.david.jpa_project.services.interfaces.IOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrdersServiceImpl implements IOrdersService {

    private final OrderRepository orderRepository;
    private final OrderInfoMapper orderInfoMapper;

    public OrdersServiceImpl(OrderRepository orderRepository, OrderInfoMapper orderInfoMapper) {
        this.orderRepository = orderRepository;
        this.orderInfoMapper = orderInfoMapper;
    }

    @Override
    public OrderInfoOut findOrderById(Long orderId) throws ResourceNotFoundException {
        log.info("Finding order by id: {}", orderId);

        List<OrderInfo> projections = orderRepository.findOrderById(orderId);

        if (projections.isEmpty()) {
            throw new ResourceNotFoundException("Order not found");
        }

        OrderInfo firstProjection = projections.getFirst();
        OrderInfoOut orderInfo = new OrderInfoOut();

        mapOrderInfoFromProjection(orderInfo, firstProjection);
        orderInfo.setAddress(mapAddressFromProjection(firstProjection));
        orderInfo.setProducts(aggregateProductsFromProjections(projections));
        orderInfo.setOrderAmount(calculateTotalAmount(projections));

        return orderInfo;
    }

    @Override
    public PageOut<OrderInfoOut> findOrdersByUser(Long userId, int page, int size) {
        log.info("Finding orders by user: {}", userId);
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderRepository.findOrdersByUser(userId, pageable);

        List<OrderInfoOut> orderInfoOuts = orders.stream()
                .map(this::mapOrderToOrderInfoOut)
                .toList();

        return orderInfoMapper.toPageOut(orders, orderInfoOuts);
    }

    private OrderInfoOut mapOrderToOrderInfoOut(Order order) {
        OrderInfoOut orderInfo = new OrderInfoOut();

        orderInfo.setOrderId(order.getId());
        orderInfo.setOrderStatus(order.getStatus().toString());
        orderInfo.setPhoneContact(order.getPhoneContact());
        orderInfo.setAddress(mapAddressFromOrder(order));
        orderInfo.setProducts(aggregateProductsFromOrder(order));
        orderInfo.setOrderAmount(calculateTotalAmountFromOrder(order));

        return orderInfo;
    }

    private void mapOrderInfoFromProjection(OrderInfoOut orderInfo, OrderInfo projection) {
        orderInfo.setOrderId(projection.getOrderId());
        orderInfo.setPhoneContact(projection.getPhoneContact());
        orderInfo.setOrderStatus(projection.getOrderStatus());
    }

    private AddressOut mapAddressFromProjection(OrderInfo projection) {
        AddressOut address = new AddressOut();
        address.setStreet(projection.getStreet());
        address.setCity(projection.getCity());
        address.setState(projection.getState());
        address.setCountry(projection.getCountry());
        address.setZipCode(projection.getZipCode());
        return address;
    }

    private AddressOut mapAddressFromOrder(Order order) {
        AddressOut address = new AddressOut();
        address.setStreet(order.getAddress().getStreet());
        address.setCity(order.getAddress().getCity());
        address.setState(order.getAddress().getState());
        address.setCountry(order.getAddress().getCountry());
        address.setZipCode(order.getAddress().getZipCode());
        return address;
    }

    private List<ProductOut> aggregateProductsFromOrder(Order order) {
        return aggregateProducts(
                order.getProducts().stream()
                        .map(this::mapProductToProductOut)
                        .collect(Collectors.toList())
        );
    }

    private List<ProductOut> aggregateProductsFromProjections(List<OrderInfo> projections) {
        return aggregateProducts(
                projections.stream()
                        .map(this::mapProjectionToProductOut)
                        .collect(Collectors.toList())
        );
    }

    private List<ProductOut> aggregateProducts(List<ProductOut> products) {
        Map<String, ProductOut> productMap = new HashMap<>();

        for (ProductOut product : products) {
            productMap.merge(product.getName(), product, (existing, newProduct) -> {
                existing.setQuantity(existing.getQuantity() + newProduct.getQuantity());
                return existing;
            });
        }

        return new ArrayList<>(productMap.values());
    }

    private ProductOut mapProductToProductOut(Product product) {
        ProductOut productOut = new ProductOut();
        productOut.setName(product.getName());
        productOut.setPrice(product.getPrice());
        productOut.setQuantity(1);
        return productOut;
    }

    private ProductOut mapProjectionToProductOut(OrderInfo projection) {
        ProductOut productOut = new ProductOut();
        productOut.setName(projection.getProductName());
        productOut.setPrice(projection.getPrice());
        productOut.setQuantity(1);
        return productOut;
    }

    private double calculateTotalAmount(List<OrderInfo> projections) {
        return projections.stream()
                .mapToDouble(OrderInfo::getPrice)
                .sum();
    }

    private double calculateTotalAmountFromOrder(Order order) {
        return order.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}