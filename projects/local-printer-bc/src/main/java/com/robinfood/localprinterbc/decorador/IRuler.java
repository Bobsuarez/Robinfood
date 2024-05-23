package com.robinfood.localprinterbc.decorador;

import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;

public interface IRuler {

    void createOrderPrint(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO);

}
