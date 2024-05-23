package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.Data;

@Data
public class Store {
    private int id;
    private int posId;
    private String name;
    private Object sku;
}