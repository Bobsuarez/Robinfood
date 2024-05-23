package com.robinfood.configurationsposbc.usecase.getconfigurationposstoreuser;

import com.robinfood.configurationsposbc.dtos.pos.PosDTO;
import com.robinfood.configurationsposbc.dtos.pos.PosResponseDTO;
import com.robinfood.configurationsposbc.dtos.pos.PosTypesDTO;
import com.robinfood.configurationsposbc.entities.UserStorePosEntity;
import com.robinfood.configurationsposbc.exceptions.NotFoundException;
import com.robinfood.configurationsposbc.mappers.IPosMapper;
import com.robinfood.configurationsposbc.mocks.PosDTOMock;
import com.robinfood.configurationsposbc.mocks.PosResponseDTOMock;
import com.robinfood.configurationsposbc.mocks.PosTypesDTOMock;
import com.robinfood.configurationsposbc.mocks.UserStorePosEntityMock;
import com.robinfood.configurationsposbc.repositories.IUserStorePosRepository;
import com.robinfood.configurationsposbc.usecase.getpos.IGetPosUseCase;
import com.robinfood.configurationsposbc.usecase.getpostypes.IGetPosTypesUseCase;
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
class GetConfigurationPosStoreUserUseCaseTest {

    @Mock
    private IUserStorePosRepository userStorePosRepository;

    @Mock
    private IGetPosUseCase getPosUseCase;

    @Mock
    private IGetPosTypesUseCase getPosTypesUseCase;

    @Mock
    private IPosMapper posMapper;

    @InjectMocks
    private GetConfigurationPosStoreUserUseCase getConfigurationPosStoreUserUseCase;

    private Long userId = 1L;
    private Long storeId = 1L;
    private Long posId = 1L;
    private Long posTypeId = 1L;

    final UserStorePosEntity userStorePosEntityMock = new UserStorePosEntityMock().userStorePosEntity;
    final PosDTO posDTO = new PosDTOMock().posDTO;
    final PosTypesDTO posTypes = new PosTypesDTOMock().posTypesDTO;
    final PosResponseDTO posResponseWithoutPosType = new PosResponseDTOMock().posResponseWithoutPosType;

    final Optional<UserStorePosEntity> optionalUserStorePosEntity = Optional.of(userStorePosEntityMock);

    @Test
    void test_Get_Configuration_Pos_Store_User_UseCase_Ok() {

        when(userStorePosRepository.findByStoreIdAndUserId(storeId, userId))
                .thenReturn(optionalUserStorePosEntity);

        when(getPosUseCase.invoke(posId))
                .thenReturn(posDTO);

        when(getPosTypesUseCase.invoke(posTypeId))
                .thenReturn(posTypes);

        when(posMapper.posDTOToPosResponseDTO(posDTO))
                .thenReturn(posResponseWithoutPosType);

        PosResponseDTO posResponseDTO = getConfigurationPosStoreUserUseCase.invoke(storeId, userId);

        Assertions.assertEquals(posResponseDTO.getPosTypes(), posTypes);

    }

    @Test
    void test_Get_Configuration_Pos_Store_User_UseCase_Not_Found() {

        Optional<UserStorePosEntity> userStorePosEntity = Optional.empty();

        when(userStorePosRepository.findByStoreIdAndUserId(storeId, userId))
                .thenReturn(userStorePosEntity);

        NotFoundException exceptionResponse = assertThrows(NotFoundException.class,
                () -> getConfigurationPosStoreUserUseCase.invoke(storeId, userId));

        assertEquals("Not found any Pos with Store " + storeId + " and User  " + userId + " Entity Parameter"
                , exceptionResponse.getMessage());
    }
}
