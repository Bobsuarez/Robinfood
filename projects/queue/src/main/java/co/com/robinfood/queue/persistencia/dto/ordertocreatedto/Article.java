package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.Data;

@Data
public class Article {
    private int menuHallProductId;
    private String typeName;
    private int typeId;
    private int id;
}