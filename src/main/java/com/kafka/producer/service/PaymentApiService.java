package com.kafka.producer.service;

import com.kafka.producer.client.PaymentApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class PaymentApiService {

    @Autowired
    private PaymentApiClient paymentApiClient;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Retryable(value = {Exception.class, RuntimeException.class}, delay = 10000, maxRetries = 3)
    public String publishPaymentData() {
        paymentApiClient.viewAllPayments()
                .forEach(payment-> kafkaTemplate.send("payment-topic", payment));
        return "Payments sent to Kafka topic successfully.";
    }
}
