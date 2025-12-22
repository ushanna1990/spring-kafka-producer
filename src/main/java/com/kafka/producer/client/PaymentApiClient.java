package com.kafka.producer.client;

import com.kafka.producer.model.Payment;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface PaymentApiClient {

    @GetExchange("/viewAllPayments")
    List<Payment> viewAllPayments();

}
