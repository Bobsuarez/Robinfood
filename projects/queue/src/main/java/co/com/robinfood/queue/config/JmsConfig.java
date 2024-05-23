package co.com.robinfood.queue.config;

import co.com.robinfood.queue.persistencia.dto.ChangeOrderStatusDTO;
import co.com.robinfood.queue.persistencia.dto.TransactionDTO;
import co.com.robinfood.queue.persistencia.dto.ordertocreatedto.OrderToCreateDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
@EnableJms
public class JmsConfig {

    @Bean
    public MessageConverter messageConverter() {

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
        typeIdMappings.put("changeOrderStatus", ChangeOrderStatusDTO.class);
        typeIdMappings.put("transactionPaymentPayU", TransactionDTO.class);
        typeIdMappings.put("orderToCreate", OrderToCreateDTO.class);//funcional order
//        typeIdMappings.put("orderCreated", OrderCreatedQueueDTO.class);

        return typeIdMappings;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setMessageConverter(messageConverter);
        return jmsTemplate;
    }

//    @Bean
//    public Topic getTopic() {
//        return new org.apache.activemq.command.ActiveMQTopic(topicName);
//    }
}