package com.robinfood.configurationsposbc.usecase.getpos;

import com.robinfood.configurationsposbc.dtos.pos.PosDTO;
import com.robinfood.configurationsposbc.entities.PosEntity;
import com.robinfood.configurationsposbc.exceptions.NotFoundException;
import com.robinfood.configurationsposbc.mappers.IPosMapper;
import com.robinfood.configurationsposbc.repositories.IPosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetPosUseCase implements IGetPosUseCase {

    private final IPosRepository posRepository;
    private final IPosMapper posMapper;

    public GetPosUseCase(IPosRepository posRepository, IPosMapper posMapper) {
        this.posRepository = posRepository;
        this.posMapper = posMapper;
    }

    @Override
    public PosDTO invoke(Long posId) {

        Optional<PosEntity> posEntityOptional = posRepository.findByIdAndStatus(posId, Boolean.TRUE);

        if (posEntityOptional.isEmpty()){
            log.error(
                    "Not found any Pos with parameters id {} and status active ", posId);
            throw new NotFoundException(
                    "Not found any Pos with Id " + posId);
        }

        return posMapper.posEntityToPosDTO(posEntityOptional.get());
    }
}
