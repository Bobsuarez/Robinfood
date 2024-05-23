package com.robinfood.localprinterbc.usecases.applyrules;

import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;

public interface IApplyRulesUseCase {

    OrderPrintDTO invoke(OrderDetailDTO orderDetailDTO, TemplateDTO templateDTO);
}
