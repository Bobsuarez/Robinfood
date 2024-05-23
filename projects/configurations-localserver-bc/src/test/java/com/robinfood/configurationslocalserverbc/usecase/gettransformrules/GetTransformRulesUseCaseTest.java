package com.robinfood.configurationslocalserverbc.usecase.gettransformrules;

import com.robinfood.configurationslocalserverbc.dtos.template.TransformRulesDTO;
import com.robinfood.configurationslocalserverbc.entities.TransformRulesEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.ITransformRulesMapper;
import com.robinfood.configurationslocalserverbc.mocks.entities.TransformRulesEntityMock;
import com.robinfood.configurationslocalserverbc.repositories.ITransformRulesRepository;
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
public class GetTransformRulesUseCaseTest {

    @InjectMocks
    private GetTransformRulesUseCase getTransformRulesUseCase;

    @Mock
    private ITransformRulesRepository transformRulesRepository;

    @Spy
    private ITransformRulesMapper transformRulesMapper = Mappers
            .getMapper(ITransformRulesMapper.class);

    final List<TransformRulesEntity> transformRulesResponseDTOMocks = new TransformRulesEntityMock()
            .transformRulesEntitiesMocks;

    final List<Long> transformRulesIds = Arrays.asList(1L, 2L);

    @Test
    void test_When_Get_Transform_Rules_UseCase_Ok() {

        when(transformRulesRepository.findByIds(transformRulesIds))
                .thenReturn(transformRulesResponseDTOMocks);

        List<TransformRulesDTO> transformRulesDTOS = getTransformRulesUseCase.invoke(transformRulesIds);

        Assertions.assertEquals(transformRulesResponseDTOMocks.get(0).getId()
                , transformRulesDTOS.get(0).getId());
    }

    @Test
    void test_When_Get_Transform_Rules_UseCase_Not_Found() {

        List<TransformRulesEntity> transformRulesEntityArrayListEmpty = new ArrayList<>();

        when(transformRulesRepository.findByIds(transformRulesIds))
                .thenReturn(transformRulesEntityArrayListEmpty);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getTransformRulesUseCase.invoke(transformRulesIds));

        assertEquals("Not found transform rules template with ids: " + transformRulesIds
                , exceptionResponse.getMessage());
    }
}
