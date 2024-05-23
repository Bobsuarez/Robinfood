package com.robinfood.changestatusor.config;

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
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Configuration
@Slf4j
public class JmsListenerConfig {

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
    @Qualifier(value="jmsConnectionFactory")
    public ConnectionFactory jmsConnectionFactoryOrders() {
        log.info("Creating JMS Connection Factory");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(queueOrderBrokerUrl);
        activeMQConnectionFactory.setUserName(queueOrderBrokerUser);
        activeMQConnectionFactory.setPassword(queueOrderBrokerPassword);
        log.info("Created JMS Connection Factory successfully");
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsFactory(
            @Qualifier("jmsConnectionFactory") @NotNull final ConnectionFactory connectionFactory,
            @NotNull final DefaultJmsListenerContainerFactoryConfigurer configurer
    ) {
        log.info("Configuring JMS Listener");
        String clientID = queueOrderClientId + "_" + UUID.randomUUID() + "_" + System.currentTimeMillis();

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(new SimpleMessageConverter());
        factory.setConcurrency(CONCURRENCY_VALUE);
        factory.setClientId(clientID);
        factory.setSubscriptionDurable(false);

        log.info("Configuring JMS Listener Container Factory with ClientID: {} ", clientID);
        configurer.configure(factory, connectionFactory);

        return factory;
    }
}
