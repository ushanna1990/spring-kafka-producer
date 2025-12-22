package com.kafka.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.producer.publisher.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/publish")
public class KafkaProducerController {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/orderData")
    public ResponseEntity<String> publishOrderData() throws JsonProcessingException {
        return ResponseEntity.ok(kafkaMessagePublisher.publishOrderData());
    }

    @GetMapping("/paymentData")
    public ResponseEntity<String> publishPaymentData() throws JsonProcessingException {
        return ResponseEntity.ok(kafkaMessagePublisher.publishPaymentData());
    }
}

