package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatetransformrules;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTransformRulesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTransformRulesEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplateTransformRulesMapper;
import com.robinfood.configurationslocalserverbc.mocks.entities.PrintingTemplateTransformRulesEntityMock;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplateTransformRulesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPrintingTemplateTransformRulesUseCaseTest {

    @InjectMocks
    private GetPrintingTemplateTransformRulesUseCase getPrintingTemplateTransformRulesUseCase;

    @Mock
    private IPrintingTemplateTransformRulesRepository printingTemplateTransformRulesRepository;

    @Spy
    private IPrintingTemplateTransformRulesMapper printingTemplateTransformRulesMapper = Mappers
            .getMapper(IPrintingTemplateTransformRulesMapper.class);

    final List<PrintingTemplateTransformRulesEntity> printingTemplateBrandGroupsEntities = new
            PrintingTemplateTransformRulesEntityMock().printingTemplateTransformRulesEntityDefault;

    private final Long printingTemplateId = 1L;

    @Test
    void test_When_Get_Printing_Template_Transform_Rules_UseCase_Invoke_Ok() {

        when(printingTemplateTransformRulesRepository.findByPrintingTemplateId(printingTemplateId))
                .thenReturn(printingTemplateBrandGroupsEntities);

        List<PrintingTemplateTransformRulesDTO> printingTemplateTransformRulesDTOS =
                getPrintingTemplateTransformRulesUseCase.invoke(printingTemplateId);

        Assertions.assertEquals(printingTemplateBrandGroupsEntities.size(), printingTemplateTransformRulesDTOS.size());
    }

    @Test
    void test_When_Get_Printing_Template_Transform_Rules_UseCase_Invoke_Not_Found() {

        List<PrintingTemplateTransformRulesEntity> printingTemplateTransformRulesEntitiesEmpty = new ArrayList<>();

        when(printingTemplateTransformRulesRepository.findByPrintingTemplateId(printingTemplateId))
                .thenReturn(printingTemplateTransformRulesEntitiesEmpty);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getPrintingTemplateTransformRulesUseCase.invoke(printingTemplateId));

        assertEquals("Not found transform rules printing template with id: " + printingTemplateId, exceptionResponse.getMessage());
    }
}
