package com.kafka.producer.config;

import com.kafka.producer.client.OrderApiClient;
import com.kafka.producer.client.PaymentApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${application.order.service.url}")
    public String orderServiceUrl;

    @Value("${application.payment.service.url}")
    public String paymentServiceUrl;

    @Bean
    public OrderApiClient orderApiClient(RestClient.Builder restClientBuilder) {

        RestClient restClient = restClientBuilder.baseUrl(orderServiceUrl).build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(OrderApiClient.class);
    }

    @Bean
    public PaymentApiClient paymentApiClient(RestClient.Builder restClientBuilder) {

        RestClient restClient = restClientBuilder.baseUrl(paymentServiceUrl).build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(PaymentApiClient.class);
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

}
