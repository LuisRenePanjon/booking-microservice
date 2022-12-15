package com.spring.bookingmicroservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-microservice")
public interface StockClient {


    @GetMapping("/api/stock/{code}")
    boolean stockAvailable(@PathVariable String code);
}
