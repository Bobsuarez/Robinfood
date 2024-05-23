package com.robinfood.configurationslocalserverbc.usecase.gettemplatebystoreid;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTypesDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplatesDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsStoresDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TemplateDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TransformRulesResponseDTO;
import com.robinfood.configurationslocalserverbc.mocks.dtos.PrintingTemplateGroupsDTOMock;
import com.robinfood.configurationslocalserverbc.mocks.dtos.PrintingTemplateTypesDTOMock;
import com.robinfood.configurationslocalserverbc.mocks.dtos.PrintingTemplatesDTOMock;
import com.robinfood.configurationslocalserverbc.mocks.dtos.TemplateGroupsDTOMocks;
import com.robinfood.configurationslocalserverbc.mocks.dtos.TemplateGroupsStoresDTOMock;
import com.robinfood.configurationslocalserverbc.mocks.dtos.TransformRulesResponseDTOMock;
import com.robinfood.configurationslocalserverbc.usecase.getgroup.IGetGroupUseCase;
import com.robinfood.configurationslocalserverbc.usecase.getgroupstore.IGetGroupByStoreUseCase;
import com.robinfood.configurationslocalserverbc.usecase.getprintingtemplate.IGetPrintingTemplateUseCase;
import com.robinfood.configurationslocalserverbc.usecase.getresponserules.IGetResponseRulesUseCase;
import com.robinfood.configurationslocalserverbc.usecase.gettemplatebygroup.IGetPrintingTemplateGroupsUseCase;
import com.robinfood.configurationslocalserverbc.usecase.gettemplatestypes.IGetTemplateTypesUseCase;
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
public class GetTemplatesByStoreIdUseCaseTest {

    @InjectMocks
    private GetTemplatesByStoreIdUseCase getTemplatesByStoreIdUseCase;

    @Mock
    private IGetGroupByStoreUseCase getGroupByStoreUseCase;

    @Mock
    private IGetGroupUseCase getGroupUseCase;

    @Mock
    private IGetPrintingTemplateGroupsUseCase getPrintingTemplateGroupsUseCase;

    @Mock
    private IGetPrintingTemplateUseCase getPrintingTemplateUseCase;

    @Mock
    private IGetResponseRulesUseCase getResponseRulesUseCase;

    @Mock
    private IGetTemplateTypesUseCase getTemplateTypesUseCase;

    final TemplateGroupsStoresDTO templateGroupsStoresDTOMock = new
            TemplateGroupsStoresDTOMock().templateGroupsStoresEntityDefault;

    final TemplateGroupsDTO templateGroupsDTOMock = new TemplateGroupsDTOMocks().templateGroupsDTODefault;

    final List<PrintingTemplateGroupsDTO> printingTemplateGroupsDTOSMocks = new PrintingTemplateGroupsDTOMock()
            .printingTemplateGroupsDTOSResponseMock;

    final List<PrintingTemplatesDTO> printingTemplatesDTOSMocks = new PrintingTemplatesDTOMock()
            .printingTemplatesResponseDTOS;

    final List<TransformRulesResponseDTO> transformRulesResponseDTOMocks = new TransformRulesResponseDTOMock()
            .transformRulesResponseDTOResponseMock;

    final PrintingTemplateTypesDTO printingTemplateTypesDTOMock = new PrintingTemplateTypesDTOMock()
            .printingTemplateTypesDTOMock;

    final Long storeId = 1L;
    final Long groupId = 1L;
    final List<Long> templateIds = Arrays.asList(1L);


    @Test
    void test_When_Get_Templates_By_StoreId_UseCase_Ok() {

        when(getGroupByStoreUseCase.invoke(storeId))
                .thenReturn(templateGroupsStoresDTOMock);

        when(getGroupUseCase.invoke(groupId))
                .thenReturn(templateGroupsDTOMock);

        when(getPrintingTemplateGroupsUseCase.invoke(groupId))
                .thenReturn(printingTemplateGroupsDTOSMocks);

        when(getPrintingTemplateUseCase.invoke(templateIds))
                .thenReturn(printingTemplatesDTOSMocks);

        when(getResponseRulesUseCase.invoke(1L))
                .thenReturn(transformRulesResponseDTOMocks);

        when(getTemplateTypesUseCase.invoke(1L))
                .thenReturn(printingTemplateTypesDTOMock);

        List<TemplateDTO> templateDTOS = getTemplatesByStoreIdUseCase.invoke(storeId);

        Assertions.assertNotNull(templateDTOS);

    }

}
