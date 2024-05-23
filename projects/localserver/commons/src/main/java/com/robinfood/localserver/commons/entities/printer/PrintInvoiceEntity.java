package com.robinfood.localserver.commons.entities.printer;

import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillRequestEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailEntity;
import com.robinfood.localserver.commons.entities.storeconfiguration.StorePOSInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PrintInvoiceEntity {

    private ElectronicBillRequestEntity electronicBillRequest;

    private OrderDetailEntity order;

    private PrinterEntity printer;

    private StorePOSInfoEntity store;
}
