package co.com.robinfood.queue.persistencia.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailProductTaxDTO {

    private  Long familyTypeId;

    private  Long id;

    private  Double price;

    private  Long taxTypeId;

    private  String taxTypeName;

    private  Double value;
}
