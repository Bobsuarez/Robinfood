package com.robinfood.localprinterbc.usecases.templateconfiguration;

import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;

public interface IGetTemplateConfigurationUseCase {
    PrintingTemplateDTO invoke(String token, Long storeId);

}
