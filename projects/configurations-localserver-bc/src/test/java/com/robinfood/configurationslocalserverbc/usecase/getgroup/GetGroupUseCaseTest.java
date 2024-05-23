package com.robinfood.configurationslocalserverbc.usecase.getgroup;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.ITemplateGroupsMapper;
import com.robinfood.configurationslocalserverbc.mocks.entities.TemplateGroupsEntityMocks;
import com.robinfood.configurationslocalserverbc.repositories.ITemplateGroupRepository;
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
public class GetGroupUseCaseTest {

    @Mock
    private ITemplateGroupRepository templateGroupRepository;

    @InjectMocks
    private GetGroupUseCase getGroupUseCase;

    @Spy
    private ITemplateGroupsMapper templateGroupsMapper = Mappers.getMapper(ITemplateGroupsMapper.class);

    final TemplateGroupsEntity templateGroupsEntity = new TemplateGroupsEntityMocks().templateGroupsEntityDefault;
    final Optional<TemplateGroupsEntity> optionalTemplateGroupsEntity = Optional.of(templateGroupsEntity);

    private Long groupId = 1L;


    @Test
    void test_When_Get_Group_UseCase_Ok() {

        when(templateGroupRepository.findById(groupId))
                .thenReturn(optionalTemplateGroupsEntity);

        TemplateGroupsDTO templateGroupsDTO = getGroupUseCase.invoke(groupId);

        Assertions.assertEquals(templateGroupsDTO.getId(), optionalTemplateGroupsEntity.get().getId());
    }

    @Test
    void test_When_Get_Group_UseCase_Not_Found() {

        Optional<TemplateGroupsEntity> optionalTemplateGroupsEntityEmpty = Optional.empty();

        when(templateGroupRepository.findById(groupId))
                .thenReturn(optionalTemplateGroupsEntityEmpty);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getGroupUseCase.invoke(groupId));

        assertEquals("Not found template store with id: " + groupId, exceptionResponse.getMessage());
    }


}
