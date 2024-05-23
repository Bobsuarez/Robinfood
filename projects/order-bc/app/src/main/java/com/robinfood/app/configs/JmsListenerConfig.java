package com.robinfood.app.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class JmsListenerConfig {

    private static final String CONCURRENCY_VALUE = "1-10";

    @Value("${activemq-orders.broker-url}")
    private String queueOrderBrokerUrl;

    @Value("${activemq-orders.user}")
    private String queueOrderBrokerUser;

    @Value("${activemq-orders.password}")
    private String queueOrderBrokerPassword;

    @Value("${activemq.paid-orders.client_id}")
    private String queueOrderClientId;

    @Bean
    @Qualifier(value="jmsConnectionFactory")
    public ConnectionFactory jmsConnectionFactoryOrders() {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(queueOrderBrokerUrl);
        activeMQConnectionFactory.setUserName(queueOrderBrokerUser);
        activeMQConnectionFactory.setPassword(queueOrderBrokerPassword);

        return activeMQConnectionFactory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        ObjectMapper mapper = buildObjectMapper();

        return buildMessageConverter(mapper);
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsFactory(
            @Qualifier("jmsConnectionFactory") @NotNull final ConnectionFactory connectionFactory,
            @NotNull final DefaultJmsListenerContainerFactoryConfigurer configurer
    ) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);

        return factory;
    }

    private ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }

    private MappingJackson2MessageConverter buildMessageConverter(
            ObjectMapper mapper
    ) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdMappings(buildTypeIdMappings());
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_robinfood_");
        converter.setObjectMapper(mapper);

        return converter;
    }

    private Map<String, Class<?>> buildTypeIdMappings() {
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("changeOrderStatus", ChangeOrderStatusDTO.class);
        typeIdMappings.put("orderCreated", OrderCreatedQueueDTO.class);

        return typeIdMappings;
    }
}
