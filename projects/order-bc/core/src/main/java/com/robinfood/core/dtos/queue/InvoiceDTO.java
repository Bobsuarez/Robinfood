package com.robinfood.core.dtos.queue;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class InvoiceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String discount;

    private Long invoice;

    private String subtotal;

    private Long paymentId;

    private String tax;

    private String total;

    private Long totalToPay;
}
