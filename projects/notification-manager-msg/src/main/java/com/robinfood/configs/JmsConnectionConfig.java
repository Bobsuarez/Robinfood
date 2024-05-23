package com.robinfood.configs;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.robinfood.dtos.request.RequestPublishEventDTO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;

import static com.robinfood.constants.APIConstants.BROKER_PASSWORD;
import static com.robinfood.constants.APIConstants.BROKER_URL;
import static com.robinfood.constants.APIConstants.BROKER_USER;

public class JmsConnectionConfig {

    public JmsTemplate jmsTemplate() {

        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setMessageConverter(messageConverter());
        jmsTemplate.setDefaultDestination(getTopic());
        jmsTemplate.setPubSubDomain(Boolean.TRUE);
        return jmsTemplate;
    }

    private MessageConverter messageConverter() {

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdMappings(buildTypeIdMappings());
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_robinfood_");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        mapper.registerModule(new JavaTimeModule());

        converter.setObjectMapper(mapper);
        return converter;
    }

    private Map<String, Class<?>> buildTypeIdMappings() {
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("changeOrderStatus", RequestPublishEventDTO.class);

        return typeIdMappings;
    }

    private ConnectionFactory connectionFactory() {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUserName(BROKER_USER);
        connectionFactory.setPassword(BROKER_PASSWORD);

        return connectionFactory;
    }

    private Topic getTopic() {
        return new org.apache.activemq.command.ActiveMQTopic(com.robinfood.constants.APIConstants.DESTINATION);
    }
}
