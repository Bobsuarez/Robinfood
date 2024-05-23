/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.robinfood.queue.bussiness;

import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

/**
 * @author Bobsu
 */
@Slf4j
@AllArgsConstructor
@Component
public class SendMessageQueue {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(DataRequestQueue dataRequestQueue) {

        try {

            jmsTemplate.setConnectionFactory(connectionFactory(dataRequestQueue));

            log.info("{}", dataRequestQueue.getInformationMessage());

                jmsTemplate.convertAndSend(
                        dataRequestQueue.getNameQueue(),
                        dataRequestQueue.getInformationMessage(),
                        message -> {
                            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 200);
                            return message;
                        });

                log.info(
                        " 0002 Send Successfully [ SendMessageQueue ] data: {}",
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
}
