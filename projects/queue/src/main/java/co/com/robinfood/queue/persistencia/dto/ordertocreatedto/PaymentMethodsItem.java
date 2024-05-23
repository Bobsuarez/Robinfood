package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.Data;

@Data
public class PaymentMethodsItem {
    private int originId;
    private int id;
    private double value;
}