package com.robinfood.localserver.commons.dtos.printer;

import com.robinfood.localserver.commons.dtos.electronicbill.ElectronicBillRequestDTO;
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

    private ElectronicBillRequestDTO electronicBillRequest;

    private OrderDetailDTO order;

    private PrinterDTO printer;

    private StorePOSInfoDTO store;
}
