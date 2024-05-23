package com.robinfood.configurationslocalserverbc.usecase.gettemplatebygroup;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateGroupsEntity;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplateGroupMapper;
import com.robinfood.configurationslocalserverbc.mocks.entities.PrintingTemplateGroupsEntityMock;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplateGroupsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPrintingTemplateGroupsUseCaseTest {

    @InjectMocks
    private GetPrintingTemplateGroupsUseCase getPrintingTemplateGroupsUseCase;

    @Mock
    private IPrintingTemplateGroupsRepository printingTemplateGroupsRepository;

    @Spy
    private IPrintingTemplateGroupMapper printingTemplateGroupMapper = Mappers
            .getMapper(IPrintingTemplateGroupMapper.class);

    final List<PrintingTemplateGroupsEntity> printingTemplateGroupsEntitiesMocks = new
            PrintingTemplateGroupsEntityMock().printingTemplateGroupsEntityMocks;

    private final Long groupId = 1L;

    @Test
    void test_When_Get_Printing_Template_Groups_UseCase_Ok() {

        when(printingTemplateGroupsRepository.findByGroupId(groupId))
                .thenReturn(printingTemplateGroupsEntitiesMocks);

        List<PrintingTemplateGroupsDTO> printingTemplateGroupsDTOS = getPrintingTemplateGroupsUseCase.invoke(groupId);

        Assertions.assertEquals(printingTemplateGroupsEntitiesMocks.get(0).getPrintingTemplateId()
                , printingTemplateGroupsDTOS.get(0).getPrintingTemplateId());
    }

}
