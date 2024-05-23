package com.robinfood.configurationslocalserverbc.usecase.gettemplatestypes;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTypesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTypesEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplateTypesMapper;
import com.robinfood.configurationslocalserverbc.mocks.entities.PrintingTemplateTypesEntityMock;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplateTypesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTemplateTypesUseCaseTest {

    @InjectMocks
    private GetTemplateTypesUseCase getTemplateTypesUseCase;

    @Mock
    private IPrintingTemplateTypesRepository printingTemplateTypesRepository;

    @Spy
    private IPrintingTemplateTypesMapper printingTemplateTypesMapper = Mappers
            .getMapper(IPrintingTemplateTypesMapper.class);

    final PrintingTemplateTypesEntity printingTemplateTypesEntity = new PrintingTemplateTypesEntityMock()
            .printingTemplateTypesEntityDefault;

    final Optional<PrintingTemplateTypesEntity> optionalPrintingTemplateTypesEntity = Optional.of(
            printingTemplateTypesEntity);

    final Long printingTemplateTypeId = 1L;

    @Test
    void test_When_Get_Template_Types_UseCase_Ok() {

        when(printingTemplateTypesRepository.findById(printingTemplateTypeId))
                .thenReturn(optionalPrintingTemplateTypesEntity);

        PrintingTemplateTypesDTO printingTemplateTypesDTO = getTemplateTypesUseCase.invoke(printingTemplateTypeId);

        Assertions.assertEquals(optionalPrintingTemplateTypesEntity.get().getId()
                , printingTemplateTypesDTO.getId());
    }

    @Test
    void test_When_Get_Template_Types_UseCase_Not_Found() {

        Optional<PrintingTemplateTypesEntity> optionalPrintingTemplateTypesEntityEmpty = Optional.empty();

        when(printingTemplateTypesRepository.findById(printingTemplateTypeId))
                .thenReturn(optionalPrintingTemplateTypesEntityEmpty);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getTemplateTypesUseCase.invoke(printingTemplateTypeId));

        assertEquals("Not found printing template types with id: " + printingTemplateTypeId
                , exceptionResponse.getMessage());
    }
}
