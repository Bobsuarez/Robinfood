package com.robinfood.localserver.commons.dtos.print;

import com.robinfood.localserver.commons.dtos.orders.OrderDetailDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.StorePOSInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PrintInvoiceDTO {

    private StorePOSInfoDTO store;
    private OrderDetailDTO order;
}
