package com.kafka.producer.controller;

import com.kafka.producer.service.OrderApiService;
import com.kafka.producer.service.PaymentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/publish")
public class KafkaProducerController {

    @Autowired
    private OrderApiService orderApiService;

    @Autowired
    private PaymentApiService paymentApiService;

    @GetMapping("/orderData")
    public ResponseEntity<String> publishOrderData() {
        return ResponseEntity.ok(orderApiService.publishOrderData());
    }

    @GetMapping("/paymentData")
    public ResponseEntity<String> publishPaymentData() {
        return ResponseEntity.ok(paymentApiService.publishPaymentData());
    }

}

