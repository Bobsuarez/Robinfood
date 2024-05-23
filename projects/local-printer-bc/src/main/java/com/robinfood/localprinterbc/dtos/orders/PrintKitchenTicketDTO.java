package com.robinfood.localprinterbc.dtos.orders;

import com.robinfood.localprinterbc.dtos.printer.PrinterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PrintKitchenTicketDTO {
    private OrderDetailDTO order;
    private PrinterDTO printer;
}
