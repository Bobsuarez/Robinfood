package com.robinfood.configurationslocalserverbc.usecase.getreponseprintingtemplates;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.PrintingTemplateResponseDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TemplateDTO;
import com.robinfood.configurationslocalserverbc.mocks.dtos.PrintingTemplateBrandGroupsDTOMock;
import com.robinfood.configurationslocalserverbc.mocks.dtos.TemplateDTOMock;
import com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatebrandgroups.IGetPrintingTemplateBrandGroupUseCase;
import com.robinfood.configurationslocalserverbc.usecase.gettemplatebystoreid.IGetTemplatesByStoreIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetResponsePrintingTemplatesUseCaseTest {

    @InjectMocks
    private GetResponsePrintingTemplatesUseCase getResponsePrintingTemplatesUseCase;

    @Mock
    private IGetTemplatesByStoreIdUseCase getTemplatesByStoreIdUseCase;

    @Mock
    private IGetPrintingTemplateBrandGroupUseCase getPrintingTemplateBrandGroupUseCase;

    final List<TemplateDTO> templateDTOSMock = new TemplateDTOMock().templateDTOSMock;

    final List<PrintingTemplateBrandGroupsDTO> printingTemplateBrandGroupsDTOSMocks = new
            PrintingTemplateBrandGroupsDTOMock().printingTemplateBrandGroupsDTOSDefault;

    private final Long storeId = 1L;
    private List<Long> templateIds = Arrays.asList(1L);

    @Test
    void test_When_Get_Response_Printing_Templates_UseCase_Ok() {

        when(getTemplatesByStoreIdUseCase.invoke(storeId))
                .thenReturn(templateDTOSMock);

        when(getPrintingTemplateBrandGroupUseCase.invoke(templateIds))
                .thenReturn(printingTemplateBrandGroupsDTOSMocks);

        PrintingTemplateResponseDTO printingTemplateResponseDTO = getResponsePrintingTemplatesUseCase.invoke(storeId);

        Assertions.assertEquals(templateDTOSMock.get(0), printingTemplateResponseDTO.getTemplates().get(0));
    }

}
