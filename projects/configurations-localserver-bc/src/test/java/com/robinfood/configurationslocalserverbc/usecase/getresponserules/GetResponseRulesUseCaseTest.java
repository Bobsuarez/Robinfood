package com.robinfood.configurationslocalserverbc.usecase.getresponserules;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTransformRulesDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.TransformRulesDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TransformRulesResponseDTO;
import com.robinfood.configurationslocalserverbc.mappers.ITransformRulesMapper;
import com.robinfood.configurationslocalserverbc.mocks.dtos.PrintingTemplateTransformRulesDTOMock;
import com.robinfood.configurationslocalserverbc.mocks.dtos.TransformRulesDTOMock;
import com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatetransformrules
        .IGetPrintingTemplateTransformRulesUseCase;
import com.robinfood.configurationslocalserverbc.usecase.gettransformrules.IGetTransformRulesUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetResponseRulesUseCaseTest {

    @InjectMocks
    private GetResponseRulesUseCase getResponseRulesUseCase;

    @Mock
    private IGetPrintingTemplateTransformRulesUseCase getPrintingTemplateTransformRulesUseCase;

    @Mock
    private IGetTransformRulesUseCase getTransformRulesUseCase;

    @Spy
    private ITransformRulesMapper transformRulesMapper = Mappers.getMapper(ITransformRulesMapper.class);

    final List<PrintingTemplateTransformRulesDTO> printingTemplateTransformRulesDTOSMocks = new
            PrintingTemplateTransformRulesDTOMock()
            .printingTemplateTransformRulesDTOSMocks;

    final List<TransformRulesDTO> transformRulesDTOS = new TransformRulesDTOMock().transformRulesDTOSMocks;

    private final Long printingTransformRulesIds = 1L;

    private List<Long> printTemplateIds = Arrays.asList(1L, 2L);

    @Test
    void test_When_Get_Response_Rules_UseCase_Ok() {

        when(getPrintingTemplateTransformRulesUseCase.invoke(printingTransformRulesIds))
                .thenReturn(printingTemplateTransformRulesDTOSMocks);

        when(getTransformRulesUseCase.invoke(printTemplateIds))
                .thenReturn(transformRulesDTOS);

        List<TransformRulesResponseDTO> transformRulesResponseDTOS = getResponseRulesUseCase
                .invoke(printingTransformRulesIds);

        Assertions.assertEquals(transformRulesDTOS.get(0).getName(), transformRulesResponseDTOS.get(0).getName());
    }

}
