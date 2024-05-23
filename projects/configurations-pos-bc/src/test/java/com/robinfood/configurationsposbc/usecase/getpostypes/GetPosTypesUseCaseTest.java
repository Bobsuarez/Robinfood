package com.robinfood.configurationsposbc.usecase.getpostypes;

import com.robinfood.configurationsposbc.dtos.pos.PosTypesDTO;
import com.robinfood.configurationsposbc.entities.PosTypesEntity;
import com.robinfood.configurationsposbc.exceptions.NotFoundException;
import com.robinfood.configurationsposbc.mappers.IPosTypesMapper;
import com.robinfood.configurationsposbc.mocks.PosTypesDTOMock;
import com.robinfood.configurationsposbc.mocks.entities.PosTypesEntityMock;
import com.robinfood.configurationsposbc.repositories.IPosTypesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPosTypesUseCaseTest {

    @Mock
    private IPosTypesRepository posTypesRepository;

    @Mock
    private IPosTypesMapper posTypesMapper;

    @InjectMocks
    private GetPosTypesUseCase getPosTypesUseCase;

    final PosTypesDTO posTypesDTO = new PosTypesDTOMock().posTypesDTO;
    final PosTypesEntity posTypesEntity = new PosTypesEntityMock().posTypesEntity;

    final Optional<PosTypesEntity> posTypesEntityOptional = Optional.of(posTypesEntity);

    private final Long posTypeId = 1L;

    @Test
    void test_Get_PosTypes_UseCase_Ok() {

        when(posTypesRepository.findById(posTypeId))
                .thenReturn(posTypesEntityOptional);

        when(posTypesMapper.posTypesEntityToPosTypesDTO(posTypesEntityOptional.get()))
                .thenReturn(posTypesDTO);

        PosTypesDTO posTypesResponseDTO = getPosTypesUseCase.invoke(posTypeId);

        Assertions.assertEquals(posTypesResponseDTO.getId(), posTypesDTO.getId());

    }

    @Test
    void test_Get_Pos_UseCase_Not_Found() {

        Optional<PosTypesEntity> posTypesEntityEmptyOptional = Optional.empty();

        when(posTypesRepository.findById(posTypeId))
                .thenReturn(posTypesEntityEmptyOptional);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getPosTypesUseCase.invoke(posTypeId));

        assertEquals("Not found any Pos Types with Id " + posTypeId
                , exceptionResponse.getMessage());
    }
}
