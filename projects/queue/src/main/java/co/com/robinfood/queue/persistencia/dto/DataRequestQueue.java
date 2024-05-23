/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.robinfood.queue.persistencia.dto;

import co.com.robinfood.queue.persistencia.dto.orderdetail.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Bobsu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataRequestQueue {

    private String brokerURL;
    private String userQueue;
    private String passQueue;
    private String nameQueue;
    private String messageJson;
    private boolean isEnableFirm;
    private boolean isEnableTopic;
    private boolean isEnableSQS;
    private Object informationMessage;
    private List<OrderDetailDTO> informationMessageSQS;

}
