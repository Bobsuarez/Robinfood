package com.robinfood.configurationsposbc.usecase.getpostypes;

import com.robinfood.configurationsposbc.dtos.pos.PosTypesDTO;
import com.robinfood.configurationsposbc.entities.PosTypesEntity;
import com.robinfood.configurationsposbc.exceptions.NotFoundException;
import com.robinfood.configurationsposbc.mappers.IPosTypesMapper;
import com.robinfood.configurationsposbc.repositories.IPosTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetPosTypesUseCase implements IGetPosTypesUseCase {

    private final IPosTypesRepository posTypesRepository;
    private final IPosTypesMapper posTypesMapper;

    public GetPosTypesUseCase(IPosTypesRepository posTypesRepository, IPosTypesMapper posTypesMapper) {
        this.posTypesRepository = posTypesRepository;
        this.posTypesMapper = posTypesMapper;
    }

    @Override
    public PosTypesDTO invoke(Long posTypeId) {

        Optional<PosTypesEntity> posTypesEntityOptional = posTypesRepository.findById(posTypeId);

        if (posTypesEntityOptional.isEmpty()){
            log.error(
                    "Not found any Pos Types with parameters id {} ", posTypeId);
            throw new NotFoundException(
                    "Not found any Pos Types with Id " + posTypeId);
        }

        return posTypesMapper.posTypesEntityToPosTypesDTO(posTypesEntityOptional.get());
    }
}
