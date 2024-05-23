package com.robinfood.localprinterbc.decorador;

import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;

public abstract class AbstractRules implements IRuler {

    private final IRuler ruler;

    public AbstractRules(IRuler ruler) {
        this.ruler = ruler;
    }

    @Override
    public void createOrderPrint(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {
        this.ruler.createOrderPrint(orderPrintDTO, templateDTO);
    }

}
