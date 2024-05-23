package com.robinfood.app.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
@Slf4j
public class JmsConfig {

    private static final String CONCURRENCY_VALUE = "1-10";

    // Properties - Order broker
    @Value("${activemq-orders.broker-url}")
    private String queueOrderBrokerUrl;

    @Value("${activemq-orders.user}")
    private String queueOrderBrokerUser;

    @Value("${activemq-orders.password}")
    private String queueOrderBrokerPassword;

    // Properties - Order broker
    @Value("${activemq-payment-methods.broker-url}")
    private String queuePaymentMethodBrokerUrl;

    @Value("${activemq-payment-methods.user}")
    private String queuePaymentMethodBrokerUser;

    @Value("${activemq-payment-methods.password}")
    private String queuePaymentMethodBrokerPassword;

    /**
     * Connections factories
     */
    @Bean
    @Primary
    public ConnectionFactory jmsConnectionFactoryOrders() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(queueOrderBrokerUrl);
        activeMQConnectionFactory.setUserName(queueOrderBrokerUser);
        activeMQConnectionFactory.setPassword(queueOrderBrokerPassword);
        return activeMQConnectionFactory;
    }

    /**
     * Jms listener container factories (Queues - Topics)
     */
    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactoryOrdersQueue(
            @NotNull ConnectionFactory connectionFactory,
            @NotNull DefaultJmsListenerContainerFactoryConfigurer configurer
    ) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConcurrency(CONCURRENCY_VALUE);
        factory.setMessageConverter(jacksonJmsMessageConverterOrders());
        factory.setConnectionFactory(jmsConnectionFactoryOrders());
        configurer.configure(factory, connectionFactory);

        log.info("Broker orders configured correctly");

        return factory;
    }


    /**
     * Template to write in broker
     */
    @Bean
    @Primary
    public JmsTemplate jmsTemplateOrders() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(jmsConnectionFactoryOrders());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverterOrders());
        return jmsTemplate;
    }


    /**
     * Message converter
     */
    @Bean
    public MessageConverter jacksonJmsMessageConverterOrders() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("orderToCreate", OrderToCreateDTO.class);

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
    public MessageConverter jacksonJmsMessageConverterPaymentMethods() {
        return new MappingJackson2MessageConverter();
    }
}
