package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatebrandgroups;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateBrandGroupsEntity;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplateBrandGroupsMapper;
import com.robinfood.configurationslocalserverbc.mocks.entities.PrintingTemplateBrandGroupsEntityMock;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplateBrandGroupsRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPrintingTemplateBrandGroupUseCaseTest {

    @InjectMocks
    private GetPrintingTemplateBrandGroupUseCase getPrintingTemplateBrandGroupUseCase;

    @Mock
    private IPrintingTemplateBrandGroupsRepository printingTemplateBrandGroupsRepository;

    @Spy
    private IPrintingTemplateBrandGroupsMapper printingTemplatesMapper = Mappers
            .getMapper(IPrintingTemplateBrandGroupsMapper.class);

    final List<PrintingTemplateBrandGroupsEntity> printingTemplateBrandGroupsEntities
            = new PrintingTemplateBrandGroupsEntityMock().printingTemplateBrandGroupsEntitiesDefault;

    private List<Long> groupsIds = Arrays.asList(1L, 2L);

    @Test
    void test_When_Get_Printing_Template_Brand_Group_UseCase_Invoke_Ok() {

        when(printingTemplateBrandGroupsRepository.findByIds(groupsIds))
                .thenReturn(printingTemplateBrandGroupsEntities);

        List<PrintingTemplateBrandGroupsDTO> printingTemplateBrandGroupsDTOS = getPrintingTemplateBrandGroupUseCase
                .invoke(groupsIds);

        Assertions.assertEquals(printingTemplateBrandGroupsEntities.size(), printingTemplateBrandGroupsDTOS.size());
    }

    @Test
    void test_When_Get_Printing_Template_Brand_Group_UseCase_Invoke_Response_Is_Empty_Ok() {

        List<PrintingTemplateBrandGroupsEntity> printingTemplateBrandGroupsEntitiesEmpty = new ArrayList<>();

        when(printingTemplateBrandGroupsRepository.findByIds(groupsIds))
                .thenReturn(printingTemplateBrandGroupsEntitiesEmpty);

        List<PrintingTemplateBrandGroupsDTO> printingTemplateBrandGroupsDTOS = getPrintingTemplateBrandGroupUseCase
                .invoke(groupsIds);

        assertEquals(0, printingTemplateBrandGroupsDTOS.size());
    }

}
