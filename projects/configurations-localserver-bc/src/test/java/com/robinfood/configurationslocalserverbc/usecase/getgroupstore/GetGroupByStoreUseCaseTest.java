package com.robinfood.configurationslocalserverbc.usecase.getgroupstore;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsStoresDTO;
import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsStoresEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.ITemplateGroupsStoresMapper;
import com.robinfood.configurationslocalserverbc.mocks.entities.TemplateGroupsStoresEntityMock;
import com.robinfood.configurationslocalserverbc.repositories.ITemplateGroupsStoresRepository;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGroupByStoreUseCaseTest {

    @InjectMocks
    private GetGroupByStoreUseCase getGroupByStoreUseCase;

    @Mock
    private ITemplateGroupsStoresRepository templateGroupsStoresRepository;

    @Spy
    private ITemplateGroupsStoresMapper templateGroupsStoresMapper = Mappers
            .getMapper(ITemplateGroupsStoresMapper.class);


    final TemplateGroupsStoresEntity templateGroupsStoresEntityMock = new TemplateGroupsStoresEntityMock()
            .templateGroupsStoresEntityDefault;
    final Optional<TemplateGroupsStoresEntity> optionalTemplateGroupsStoresEntity = Optional
            .of(templateGroupsStoresEntityMock);

    private Long storeId = 1L;


    @Test
    void test_When_Get_Group_By_Store_UseCase_Invoke_Ok() {

        when(templateGroupsStoresRepository.findByStoreIdAndActive(storeId, Boolean.TRUE))
                .thenReturn(optionalTemplateGroupsStoresEntity);

        TemplateGroupsStoresDTO templateGroupsStoresDTO = getGroupByStoreUseCase.invoke(storeId);

        assertNotNull(templateGroupsStoresDTO);
        Assertions.assertEquals(templateGroupsStoresDTO.getId(), optionalTemplateGroupsStoresEntity.get().getId());
    }

    @Test
    void test_When_Get_Group_By_Store_UseCase_Invoke_Not_Found() {

        Optional<TemplateGroupsStoresEntity> optionalTemplateGroupsStoresEntityEmpty = Optional.empty();

        when(templateGroupsStoresRepository.findByStoreIdAndActive(storeId, Boolean.TRUE))
                .thenReturn(optionalTemplateGroupsStoresEntityEmpty);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getGroupByStoreUseCase.invoke(storeId));

        assertEquals("Not found Group by store with id: " + storeId, exceptionResponse.getMessage());
    }
}
