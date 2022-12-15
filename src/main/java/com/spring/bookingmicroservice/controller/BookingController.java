package com.spring.bookingmicroservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.bookingmicroservice.dto.OrderDTO;
import com.spring.bookingmicroservice.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final IBookingService bookingService;

    @Autowired
    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping()
    @HystrixCommand(fallbackMethod = "saveOrderFallback")
    public String saveOrder(@RequestBody OrderDTO orderDTO){
        return this.bookingService.saveOrder(orderDTO);
    }


    private String saveOrderFallback(){
        return "Sorry, booking service is not available, please try again later";
    }

}
