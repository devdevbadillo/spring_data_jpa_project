package com.david.jpa_project.services;

import com.david.jpa_project.Factory;
import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.model.entities.entity.Order;
import com.david.jpa_project.model.mappers.OrderInfoMapper;
import com.david.jpa_project.model.projections.OrderInfo;
import com.david.jpa_project.model.repositories.native_queries.OrderRepository;
import com.david.jpa_project.services.impl.OrdersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderInfoMapper orderInfoMapper;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    private List<OrderInfo> mockProjections;
    private Page<Order> mockPage;
    private PageOut<OrderInfoOut> mockPageOut;
    private final static int PAGE = 0;
    private final static int SIZE = 10;

    @BeforeEach
    void setUp() {
        OrderInfo projection1 = mock(OrderInfo.class);
        OrderInfo projection2 = mock(OrderInfo.class);
        mockProjections = Arrays.asList(projection1, projection2);

        List<Order> orders = Factory.createOrders();
        Pageable pageable = PageRequest.of(PAGE, SIZE);
        mockPage = new PageImpl<>(orders, pageable, orders.size());

        List<OrderInfoOut> orderInfoOuts = List.of(Factory.createOrderInfoOut());
        mockPageOut = PageOut.<OrderInfoOut>builder()
                .content(orderInfoOuts)
                .totalElements(mockPage.getTotalElements())
                .totalPages(mockPage.getTotalPages())
                .build();
    }

    @Test
    void findOrderById_WhenOrderExists_ShouldReturnOrderInfoOut() throws ResourceNotFoundException {
        // Given
        Long orderId = 1L;
        when(orderRepository.findOrderById(orderId)).thenReturn(mockProjections);

        // When
        OrderInfoOut result = ordersService.findOrderById(orderId);

        // Then
        assertThat(result).isNotNull();
        verify(orderRepository, times(1)).findOrderById(orderId);
    }

    @Test
    void findOrderById_WhenOrderDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Given
        Long orderId = 1L;
        when(orderRepository.findOrderById(orderId)).thenReturn(List.of());

        // When & Then
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> ordersService.findOrderById(orderId));
    }

    @Test
    void findOrdersByUser_WhenUserExists_ShouldReturnOrderInfoOut() {
        // Given
        Long userId = 1L;
        Pageable pageable = PageRequest.of(PAGE, SIZE);
        when(orderRepository.findOrdersByUser(userId, pageable)).thenReturn(mockPage);
        when(orderInfoMapper.toPageOut(any(), any())).thenReturn(mockPageOut);

        // When
        PageOut<OrderInfoOut> result = ordersService.findOrdersByUser(userId, PAGE, SIZE);

        // Then
        assertThat(result).isNotNull();
        verify(orderRepository, times(1)).findOrdersByUser(userId, pageable);
    }

}
