package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.Data;

@Data
public class Delivery {
    private int totalDelivery;
    private double totalTip;
    private int orderType;
    private String addressDescription;
    private String code;
    private String notes;
    private String arrivalTime;
    private String integrationId;
    private String paymentMethod;
    private int totalDiscount;
    private String addressCity;
    private String arrivalDate;
}