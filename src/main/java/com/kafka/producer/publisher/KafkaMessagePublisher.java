package com.kafka.producer.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessagePublisher {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public String publishOrderData() throws JsonProcessingException {
        String message = new ObjectMapper().writeValueAsString(kafkaProducerService.getOrderApiClientData());
        kafkaTemplate.send("order-topic", message);
        return "Orders data published to kafka topic successfully.";
    }

    public String publishPaymentData() throws JsonProcessingException {
        String message = new ObjectMapper().writeValueAsString(kafkaProducerService.getPaymentApiClientData());
        kafkaTemplate.send("payment-topic", message);
        return "Payments data published to kafka topic successfully.";
    }
}
