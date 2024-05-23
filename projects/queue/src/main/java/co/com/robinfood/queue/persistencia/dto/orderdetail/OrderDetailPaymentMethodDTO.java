package co.com.robinfood.queue.persistencia.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailPaymentMethodDTO {

    private  Double discount;

    private  String name;

    private  Long id;

    private  Long originId;

    private  Double subtotal;

    private  Double tax;

    private  Double value;

}
