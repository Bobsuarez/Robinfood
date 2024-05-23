package com.robinfood.orderorlocalserver.configs.jms;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.robinfood.orderorlocalserver.dtos.orderqueue.OrderCreatedQueueDTO;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@Slf4j
public class JmsConfig {

    private static final String CONCURRENCY_VALUE = "1";

    @Value("${activemq-orders.broker-url}")
    private String queueOrderBrokerUrl;

    @Value("${activemq-orders.user}")
    private String queueOrderBrokerUser;

    @Value("${activemq-orders.password}")
    private String queueOrderBrokerPassword;

    @Value("${activemq.paid-orders.client_id}")
    private String queueOrderClientId;

    @Bean
    public ConnectionFactory jmsConnectionFactoryOrders() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(queueOrderBrokerUrl);
        activeMQConnectionFactory.setUserName(queueOrderBrokerUser);
        activeMQConnectionFactory.setPassword(queueOrderBrokerPassword);
        return activeMQConnectionFactory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverterOrders() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("orderCreated", OrderCreatedQueueDTO.class);

        converter.setTypeIdMappings(typeIdMappings);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_robinfood_");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());

        converter.setObjectMapper(mapper);

        return converter;
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactoryOrdersQueue(
            @NotNull ConnectionFactory connectionFactory,
            @NotNull DefaultJmsListenerContainerFactoryConfigurer configurer
    ) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setClientId(queueOrderClientId + "_" + UUID.randomUUID());
        factory.setConcurrency(CONCURRENCY_VALUE);
        factory.setConnectionFactory(jmsConnectionFactoryOrders());
        factory.setMessageConverter(jacksonJmsMessageConverterOrders());
        factory.setSubscriptionDurable(false);
        configurer.configure(factory, connectionFactory);
        
        log.info("Broker orders configured correctly");

        return factory;
    }
}