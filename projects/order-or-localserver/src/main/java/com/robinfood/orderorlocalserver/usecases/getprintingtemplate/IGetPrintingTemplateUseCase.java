package com.robinfood.orderorlocalserver.usecases.getprintingtemplate;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateDTO;

public interface IGetPrintingTemplateUseCase {

    PrintingTemplateDTO invoke(Long storeId);
}
