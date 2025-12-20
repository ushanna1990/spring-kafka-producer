package com.kafka.producer.service;

import com.kafka.producer.client.OrderApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;


@Service
public class OrderApiService {

    @Autowired
    private OrderApiClient orderApiClient;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Retryable(value = {Exception.class, RuntimeException.class}, delay = 60000, maxRetries = 3)
    public String publishOrderData() {
        orderApiClient.viewAllOrders()
                .forEach(order-> kafkaTemplate.send("order-topic",order.getProductId(), order));
        return "Orders sent to Kafka topic successfully.";
    }


}
