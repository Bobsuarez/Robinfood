package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentsDTO implements Serializable {

    private static final long serialVersionUID = -5164328306304431294L;

    private Long id;

    private String subtotal;

    private String tax;

    private String discount;

    private String value;

    private Long orderPaymentRowId;

}
