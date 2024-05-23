package co.com.robinfood.queue.persistencia.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailChangedPortionDTO {

    private  Long id;

    private  String name;

    private  Long parentId;

    private  String sku;

    private  Long unitId;

    private  Double unitNumber;
}
