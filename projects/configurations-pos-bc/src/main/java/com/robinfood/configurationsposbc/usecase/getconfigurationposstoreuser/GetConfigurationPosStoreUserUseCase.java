package com.robinfood.configurationsposbc.usecase.getconfigurationposstoreuser;

import com.robinfood.configurationsposbc.dtos.pos.PosDTO;
import com.robinfood.configurationsposbc.dtos.pos.PosResponseDTO;
import com.robinfood.configurationsposbc.dtos.pos.PosTypesDTO;
import com.robinfood.configurationsposbc.entities.UserStorePosEntity;
import com.robinfood.configurationsposbc.exceptions.NotFoundException;
import com.robinfood.configurationsposbc.mappers.IPosMapper;
import com.robinfood.configurationsposbc.repositories.IUserStorePosRepository;
import com.robinfood.configurationsposbc.usecase.getpos.IGetPosUseCase;
import com.robinfood.configurationsposbc.usecase.getpostypes.IGetPosTypesUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetConfigurationPosStoreUserUseCase implements IGetConfigurationPosStoreUserUseCase {

    private final IUserStorePosRepository userStorePosRepository;
    private final IGetPosUseCase getPosUseCase;
    private final IGetPosTypesUseCase getPosTypesUseCase;
    private final IPosMapper posMapper;

    public GetConfigurationPosStoreUserUseCase(IUserStorePosRepository userStorePosRepository,
                                               IGetPosUseCase getPosUseCase,
                                               IGetPosTypesUseCase getPosTypesUseCase,
                                               IPosMapper posMapper) {
        this.userStorePosRepository = userStorePosRepository;
        this.getPosUseCase = getPosUseCase;
        this.getPosTypesUseCase = getPosTypesUseCase;
        this.posMapper = posMapper;
    }


    @Override
    public PosResponseDTO invoke(Long storeId, Long userId) {

        Optional<UserStorePosEntity> userStorePosEntityOptional = userStorePosRepository.findByStoreIdAndUserId(
                storeId, userId);

        if (userStorePosEntityOptional.isEmpty()) {
            log.error(
                    "Not found any Pos with the parameters {} {}",
                    storeId, userId);
            throw new NotFoundException(
                    "Not found any Pos with Store " + storeId + " and User  " + userId + " Entity Parameter");
        }

        UserStorePosEntity userStorePosEntity = userStorePosEntityOptional.get();

        PosDTO posDTO = getPosUseCase.invoke(userStorePosEntity.getPosId());
        PosTypesDTO posTypesDTO = getPosTypesUseCase.invoke(posDTO.getPosTypeId());

        PosResponseDTO posResponseDTO = posMapper.posDTOToPosResponseDTO(posDTO);
        posResponseDTO.setPosTypes(posTypesDTO);

        return posResponseDTO;
    }

}
