package com.david.jpa_project.repository;

import com.david.jpa_project.factory.OrderFactory;
import com.david.jpa_project.factory.ProductFactory;
import com.david.jpa_project.model.entities.entity.Customer;
import com.david.jpa_project.model.entities.entity.Order;
import com.david.jpa_project.model.repositories.native_queries.OrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Order testOrder;
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = OrderFactory.createCustomer();
        testCustomer = entityManager.persist(testCustomer);

        var testAddress = entityManager.persist(OrderFactory.createAddress());

        testCustomer.setAddresses(List.of(testAddress));
        testCustomer = entityManager.persist(testCustomer);

        var testProduct = ProductFactory.createProduct();
        entityManager.persist(testProduct);

        testOrder = OrderFactory.createOrder();
        testOrder.setAddress(testAddress);
        testOrder.setProducts(List.of(testProduct));
        testOrder = entityManager.persist(testOrder);

        testCustomer.setOrders(List.of(testOrder));
        entityManager.flush();
    }

    @Test
    void findOrderById_withOrderIdExist_shouldReturnOrderInfo() {
        // When
        Long id = testOrder.getId();

        // Given
        var result = orderRepository.findOrderById(id);

        // Then
        assertThat(result).isNotEmpty();
        var orderInfo = result.getFirst();
        assertThat(orderInfo.getOrderStatus()).isEqualTo("PENDING");
    }

    @Test
    void findOrderById_withOrderIdNotExist_thenShouldReturnEmptyList() {
        // When
        Long id = testOrder.getId() + 1;

        // Given
        var result = orderRepository.findOrderById(id);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void findOrdersByUser_withUserIdExist_shouldReturnOrderInfo() {
        // When
        var pageable = PageRequest.of(0, 1);

        // Given
        var result = orderRepository.findOrdersByUser(testCustomer.getId(), pageable);

        // Then
        assertThat(result).isNotEmpty();
    }

    @Test
    void findOrdersByUser_withUserIdNotExist_thenShouldReturnEmptyList() {
        // When
        var pageable = PageRequest.of(0, 1);

        // Given
        var result = orderRepository.findOrdersByUser(testCustomer.getId() + 1, pageable);

        // Then
        assertThat(result).isEmpty();
    }

}
