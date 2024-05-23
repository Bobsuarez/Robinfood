package com.robinfood.localprinterbc.dtos.invoice;

import com.robinfood.localprinterbc.dtos.orders.ElectronicBillRequestDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.printer.PrinterDTO;
import com.robinfood.localprinterbc.dtos.store.StoreDTO;
import com.robinfood.localprinterbc.dtos.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class InvoiceDetailDTO {

    private PrinterDTO printer;
    private StoreDTO store;
    private OrderDetailDTO order;
    private UserDTO user;
    private ElectronicBillRequestDTO electronicBillRequest;

}
