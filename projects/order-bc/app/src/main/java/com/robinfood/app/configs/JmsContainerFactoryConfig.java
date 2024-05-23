package com.robinfood.app.configs;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Implementation of IJms
 */
@Configuration
@EnableJms
@Slf4j
public class JmsContainerFactoryConfig {

    @Value("${activemq.paid-orders.client_id}")
    private String queueOrderClientId;

    public JmsListenerContainerFactory<DefaultMessageListenerContainer> invoke(
        @NotNull final SingleConnectionFactory connectionFactory,
        @NotNull final DefaultJmsListenerContainerFactoryConfigurer containerFactory
    ) {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory =
            new DefaultJmsListenerContainerFactory();

        connectionFactory.setClientId(queueOrderClientId);
        jmsListenerContainerFactory.setClientId(queueOrderClientId);
        jmsListenerContainerFactory.setSubscriptionDurable(true);
        jmsListenerContainerFactory.setConcurrency("1-10");
        containerFactory.configure(jmsListenerContainerFactory, connectionFactory);

        log.info("Jms configured correctly");

        return jmsListenerContainerFactory;
    }

}
