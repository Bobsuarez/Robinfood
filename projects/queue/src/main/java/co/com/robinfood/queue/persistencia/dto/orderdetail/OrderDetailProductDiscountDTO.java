package co.com.robinfood.queue.persistencia.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailProductDiscountDTO {

    private  Long id;

    private  Long typeId;

    private  Double value;
}
