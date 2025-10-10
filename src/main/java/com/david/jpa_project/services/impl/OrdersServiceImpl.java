package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.model.projections.OrderInfo;
import com.david.jpa_project.model.repositories.native_queries.OrderRepository;
import com.david.jpa_project.services.interfaces.IOrdersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements IOrdersService {

    private final OrderRepository orderRepository;

    public OrdersServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderInfoOut findOrderByIdWithInformation(Long orderId) {
        List<OrderInfo> projection = orderRepository.findOrderByIdWithInformation(orderId);
        OrderInfoOut orderInfo = new OrderInfoOut();

        if(!projection.isEmpty()){
            OrderInfo orderInfoProjection = projection.getFirst();
            orderInfo.setOrderId(orderInfoProjection.getOrderId());
            orderInfo.setPhoneContact(orderInfoProjection.getContact());
            orderInfo.setOrderStatus(orderInfoProjection.getOrderStatus());
            AddressOut address = new AddressOut();
            address.setStreet(orderInfoProjection.getStreet());
            address.setCity(orderInfoProjection.getCity());
            address.setState(orderInfoProjection.getState());
            address.setCountry(orderInfoProjection.getCountry());
            address.setZipCode(orderInfoProjection.getZipCode());
            orderInfo.setAddress(address);

            List<ProductOut> products = getProductOutList(projection);

            orderInfo.setProducts(products);

            orderInfo.setOrderAmount(projection.stream().mapToDouble(OrderInfo::getPrice).sum());
        }

        return orderInfo;
    }

    private static List<ProductOut> getProductOutList(List<OrderInfo> projection) {
        List<ProductOut> products = new ArrayList<>();

        for(OrderInfo product : projection){
            ProductOut productOut = new ProductOut();
            productOut.setName(product.getProductName());

            if(products.contains(productOut)){
                ProductOut recitedProduct = products.get(products.indexOf(productOut));
                recitedProduct.setQuantity(recitedProduct.getQuantity() + 1);
            }else{
                productOut.setQuantity(1);
                productOut.setPrice(product.getPrice());
                products.add(productOut);
            }

        }
        return products;
    }
}
