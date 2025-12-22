package com.kafka.producer.service;

import com.kafka.producer.client.OrderApiClient;
import com.kafka.producer.client.PaymentApiClient;
import com.kafka.producer.model.Order;
import com.kafka.producer.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerService {

    @Autowired
    private OrderApiClient orderApiClient;

    @Autowired
    private PaymentApiClient paymentApiClient;

    @Retryable(value = {Exception.class, RuntimeException.class}, delay = 60000, maxRetries = 3)
    public List<Order> getOrderApiClientData()  {
       return orderApiClient.viewAllOrders();
    }

    @Retryable(value = {Exception.class, RuntimeException.class}, delay = 60000, maxRetries = 3)
    public List<Payment> getPaymentApiClientData()  {
        return paymentApiClient.viewAllPayments();
    }
}
