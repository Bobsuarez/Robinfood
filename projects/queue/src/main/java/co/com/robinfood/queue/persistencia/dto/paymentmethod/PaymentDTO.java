package co.com.robinfood.queue.persistencia.dto.paymentmethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PaymentDTO implements Serializable {

    private static final long serialVersionUID = -5490947678543801232L;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal total;
}
