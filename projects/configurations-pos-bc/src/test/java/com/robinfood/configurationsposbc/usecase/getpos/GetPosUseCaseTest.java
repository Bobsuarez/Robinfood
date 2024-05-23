package com.robinfood.configurationsposbc.usecase.getpos;


import com.robinfood.configurationsposbc.dtos.pos.PosDTO;
import com.robinfood.configurationsposbc.entities.PosEntity;
import com.robinfood.configurationsposbc.exceptions.NotFoundException;
import com.robinfood.configurationsposbc.mappers.IPosMapper;
import com.robinfood.configurationsposbc.mocks.PosDTOMock;
import com.robinfood.configurationsposbc.mocks.entities.PosEntityMock;
import com.robinfood.configurationsposbc.repositories.IPosRepository;
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
class GetPosUseCaseTest {

    @Mock
    private IPosRepository posRepository;

    @Mock
    private IPosMapper posMapper;

    @InjectMocks
    private GetPosUseCase getPosUseCase;

    private Long posId = 1L;

    final PosEntity posEntity = new PosEntityMock().posEntity;
    final PosDTO posDTO = new PosDTOMock().posDTO;

    final Optional<PosEntity> posEntityOptional = Optional.of(posEntity);


    @Test
    void test_Get_Pos_UseCase_Ok() {

        when(posRepository.findByIdAndStatus(posId, Boolean.TRUE))
                .thenReturn(posEntityOptional);

        when(posMapper.posEntityToPosDTO(posEntityOptional.get()))
                .thenReturn(posDTO);

        PosDTO posResponseDTO = getPosUseCase.invoke(posId);

        Assertions.assertEquals(posResponseDTO.getId(), posDTO.getId());

    }

    @Test
    void test_Get_Pos_UseCase_Not_Found() {

        Optional<PosEntity> posEntityEmptyOptional = Optional.empty();

        when(posRepository.findByIdAndStatus(posId, Boolean.TRUE))
                .thenReturn(posEntityEmptyOptional);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getPosUseCase.invoke(posId));

        assertEquals("Not found any Pos with Id " + posId
                , exceptionResponse.getMessage());
    }
}
