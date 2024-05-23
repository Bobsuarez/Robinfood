package com.robinfood.localprinterbc.usecases.printordersusecase;

import com.robinfood.localprinterbc.dtos.orders.PrintKitchenTicketDTO;

import java.io.IOException;
import java.util.Map;

public interface IExecutePrintOrdersUseCase {
    /**
     * Metodo encargado de imprimir una comanda
     * @param token autorizacion
     * @param printKitchenTicketDTO dto que contiene varios objetos como la orden, print.
     */
    Map<String, Object> printOrder(String token, PrintKitchenTicketDTO printKitchenTicketDTO)throws IOException;
}
