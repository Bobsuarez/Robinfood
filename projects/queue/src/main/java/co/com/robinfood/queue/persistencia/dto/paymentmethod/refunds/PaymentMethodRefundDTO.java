package co.com.robinfood.queue.persistencia.dto.paymentmethod.refunds;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Builder
public class PaymentMethodRefundDTO implements Serializable {

    private static final long serialVersionUID = -1269160904741574640L;

    private EntityDTO entity;

    private OriginDTO origin;

    private String reason;

    private Integer userId;
}
