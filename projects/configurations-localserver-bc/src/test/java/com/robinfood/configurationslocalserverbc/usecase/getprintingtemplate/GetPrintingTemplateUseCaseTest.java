package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplate;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplatesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplatesEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplatesMapper;
import com.robinfood.configurationslocalserverbc.mocks.entities.PrintingTemplatesEntityMock;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplatesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPrintingTemplateUseCaseTest {

    @InjectMocks
    private GetPrintingTemplateUseCase getPrintingTemplateUseCase;

    @Mock
    private IPrintingTemplatesRepository printingTemplatesRepository;

    @Spy
    private IPrintingTemplatesMapper printingTemplatesMapper = Mappers
            .getMapper(IPrintingTemplatesMapper.class);

    final List<PrintingTemplatesEntity> printingTemplatesEntities = new PrintingTemplatesEntityMock()
            .printingTemplatesEntityDefault;

    private List<Long> printTemplateIds = Arrays.asList(1L, 2L);

    @Test
    void test_When_Get_Printing_Template_UseCase_Invoke_Ok() {

        when(printingTemplatesRepository.findByIds(printTemplateIds))
                .thenReturn(printingTemplatesEntities);

        List<PrintingTemplatesDTO> printingTemplatesDTOS = getPrintingTemplateUseCase.invoke(printTemplateIds);

        Assertions.assertEquals(printingTemplatesEntities.size(), printingTemplatesDTOS.size());
    }

    @Test
    void test_When_Get_Printing_Template_UseCase_Invoke_Not_Found() {

        List<PrintingTemplatesEntity> printingTemplatesEntitiesEmpty = new ArrayList<>();

        when(printingTemplatesRepository.findByIds(printTemplateIds))
                .thenReturn(printingTemplatesEntitiesEmpty);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getPrintingTemplateUseCase.invoke(printTemplateIds));

        assertEquals("Not found printing template with ids: " + printTemplateIds, exceptionResponse.getMessage());
    }

}
