package com.kafka.producer.client;

import com.kafka.producer.model.OrderResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface OrderApiClient {

    @GetExchange("/viewAllOrders")
    List<OrderResponse> viewAllOrders();

}
