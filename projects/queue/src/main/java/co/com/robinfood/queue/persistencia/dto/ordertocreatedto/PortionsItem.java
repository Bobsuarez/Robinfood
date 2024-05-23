package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.Data;

@Data
public class PortionsItem {
    private Product product;
    private int quantity;
    private double price;
    private String name;
    private int discount;
    private int unitId;
    private int unitNumber;
    private int id;
    private int free;
    private String sku;
    private Boolean isIncluded;
}