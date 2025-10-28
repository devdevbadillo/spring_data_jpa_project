package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.advice.exceptions.BusinessException;
import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.model.entities.entity.Address;
import com.david.jpa_project.model.entities.entity.Order;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.entities.enums.OrderStatus;
import com.david.jpa_project.model.mappers.OrderInfoMapper;
import com.david.jpa_project.model.projections.OrderInfo;
import com.david.jpa_project.model.repositories.jpql.AddressRepository;
import com.david.jpa_project.model.repositories.jpql.ProductRepository;
import com.david.jpa_project.model.repositories.native_queries.OrderRepository;
import com.david.jpa_project.services.interfaces.IOrdersService;
import com.david.jpa_project.services.utils.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.david.jpa_project.services.utils.OrderUtils.*;

@Service
@Slf4j
public class OrdersServiceImpl implements IOrdersService {

    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderInfoMapper orderInfoMapper;

    public OrdersServiceImpl(
            OrderRepository orderRepository,
            OrderInfoMapper orderInfoMapper,
            AddressRepository addressRepository,
            ProductRepository productRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderInfoMapper = orderInfoMapper;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderInfoOut findOrderById(Long orderId) throws ResourceNotFoundException {
        log.info("Finding order by id: {}", orderId);

        List<OrderInfo> projections = orderRepository.findOrderById(orderId);

        if (projections.isEmpty()) {
            throw new ResourceNotFoundException("Order not found");
        }

        OrderInfo projection = projections.getFirst();
        OrderInfoOut orderInfo = new OrderInfoOut();

        mapOrderInfoFromProjection(orderInfo, projection);
        orderInfo.setAddress(mapAddressFromProjection(projection));
        orderInfo.setProducts(aggregateProductsFromProjections(projections));
        orderInfo.setOrderAmount(calculateTotalAmount(projections));

        return orderInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public PageOut<OrderInfoOut> findOrdersByUser(Long userId, int page, int size) {
        log.info("Finding orders by user: {}", userId);

        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderRepository.findOrdersByUser(userId, pageable);

        List<OrderInfoOut> orderInfoOuts = orders.stream()
                .map(OrderUtils::mapOrderToOrderInfoOut)
                .toList();

        return orderInfoMapper.toPageOut(orders, orderInfoOuts);
    }

    /* Escenario: Un cliente realiza una compra
     * - Se crea la orden
     * - Se reduce el inventario
     * - Se envía notificación
     * Si CUALQUIER paso falla, TODO se revierte (rollback)
     */
    @Transactional
    public OrderInfoOut createOrder(List<Long> productIds, Long addressId) throws ResourceNotFoundException, BusinessException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("La dirección no fue encontrada"));

        Set<Long> uniqueProductIds = new HashSet<>(productIds);
        List<Product> products = productRepository.findAllById(uniqueProductIds);

        if (products.size() != uniqueProductIds.size()) {
            throw new ResourceNotFoundException("Algunos productos no existen");
        }

        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        List<Product> orderedProducts = validateAndReduceStock(productIds, productMap, products);

        Order order = new Order();
        order.setProducts(orderedProducts);
        order.setStatus(OrderStatus.PENDING);
        order.setAddress(address);
        order.setPhoneContact(address.getCustomer().getPhoneNumber());
        Order savedOrder = orderRepository.save(order);

        // TODO - Enviar un email

        log.info("Orden {} creada exitosamente", savedOrder.getId());
        return OrderUtils.mapOrderToOrderInfoOut(savedOrder);
    }

    /**
     * Escenario: Método que DEBE ser llamado dentro de una transacción
     * - Métodos auxiliares que asumen contexto transaccional
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> validateAndReduceStock(List<Long> productIds, Map<Long, Product> productMap, List<Product> products) throws BusinessException {
        List<Product> orderedProducts = new ArrayList<>();

        for (Long productId : productIds) {
            Product product = productMap.get(productId);

            if (product.getStockQuantity() <= 0) {
                throw new BusinessException("El producto " + product.getName() + " no tiene stock disponible");
            }

            product.setStockQuantity(product.getStockQuantity() - 1);
            orderedProducts.add(product);
        }

        productRepository.saveAll(products);

        return orderedProducts;
    }
}