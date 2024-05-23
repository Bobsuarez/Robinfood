package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.Data;

import java.util.List;

@Data
public class GroupsItem {
    private String name;
    private int id;
    private String sku;
    private List<PortionsItem> portions;
}