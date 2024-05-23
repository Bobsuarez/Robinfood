/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.robinfood.queue.bussiness;

import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;
import co.com.robinfood.queue.persistencia.dto.ordertocreatedto.OrderToCreateDTO;
import co.com.robinfood.queue.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;

/**
 * @author Bobsu
 */
@Slf4j
@AllArgsConstructor
@Component
public class SendMessageTopic {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(DataRequestQueue dataRequestQueue) {
        try {

            jmsTemplate.setConnectionFactory(connectionFactory(dataRequestQueue));

            if (dataRequestQueue.isEnableTopic()) {
                jmsTemplate.setDefaultDestination(getTopic(dataRequestQueue.getNameQueue()));
            }

            jmsTemplate.convertAndSend(
                    dataRequestQueue.getNameQueue(),
//                    message
                    JsonUtils.jsonToClass(dataRequestQueue.getMessageJson(), OrderToCreateDTO.class),
                    new MessagePostProcessor() {
                        @Override
                        public Message postProcessMessage(Message message) throws JMSException {
                            message.setStringProperty("from", "test");
                            message.setStringProperty("country", "test");
                            return message;
                        }
                    }
            );

            log.info(
                    "0002 Send Successfully [ SendMessageTopic ] data: {}",
                    new ObjectMapper().writeValueAsString(dataRequestQueue)
            );

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private ConnectionFactory connectionFactory(DataRequestQueue dataRequestQueue) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(dataRequestQueue.getBrokerURL());
        connectionFactory.setUserName(dataRequestQueue.getUserQueue());
        connectionFactory.setPassword(dataRequestQueue.getPassQueue());

        return connectionFactory;
    }

    private Topic getTopic(String topicName) {
        return new org.apache.activemq.command.ActiveMQTopic(topicName);
    }

}
