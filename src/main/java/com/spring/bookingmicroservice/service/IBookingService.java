package com.spring.bookingmicroservice.service;

import com.spring.bookingmicroservice.dto.OrderDTO;

public interface IBookingService {

    String saveOrder(OrderDTO orderDTO);

}
