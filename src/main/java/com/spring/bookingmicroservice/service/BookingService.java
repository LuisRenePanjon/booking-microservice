package com.spring.bookingmicroservice.service;

import com.spring.bookingmicroservice.client.StockClient;
import com.spring.bookingmicroservice.dto.OrderDTO;
import com.spring.bookingmicroservice.entity.Order;
import com.spring.bookingmicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingService implements IBookingService {

    private final OrderRepository orderRepository;

    private final StockClient stockClient;

    @Autowired
    public BookingService(OrderRepository orderRepository, StockClient stockClient) {
        this.orderRepository = orderRepository;
        this.stockClient = stockClient;
    }
    @Override
    public String saveOrder(OrderDTO orderDTO) {
        try {
            var inStock = orderDTO.getOrderItems().stream()
                    .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

            if(!inStock){
                throw new RuntimeException("Stock not available");
            }

            var order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderDTO.getOrderItems());

            this.orderRepository.save(order);

            return "Order saved with order number: " + orderRepository.save(order).getOrderNumber();
        }catch (Exception e){
            throw new RuntimeException("Error while saving order");
        }
    }
}
