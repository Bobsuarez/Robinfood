package com.robinfood.changestatusbc.configs.jms;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
@Slf4j
public class JmsConfig {

    // Properties - Order broker
    @Value("${activemq-orders.broker-url}")
    private String queueOrderBrokerUrl;

    @Value("${activemq-orders.user}")
    private String queueOrderBrokerUser;

    @Value("${activemq-orders.password}")
    private String queueOrderBrokerPassword;


    @Bean
    @Primary
    public ConnectionFactory jmsConnectionFactoryOrders() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(queueOrderBrokerUrl);
        activeMQConnectionFactory.setUserName(queueOrderBrokerUser);
        activeMQConnectionFactory.setPassword(queueOrderBrokerPassword);
        return activeMQConnectionFactory;
    }

}
