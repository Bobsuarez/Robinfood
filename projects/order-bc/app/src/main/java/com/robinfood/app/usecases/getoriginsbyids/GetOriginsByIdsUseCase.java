package com.robinfood.app.usecases.getoriginsbyids;

import com.robinfood.app.mappers.OriginMapper;
import com.robinfood.core.dtos.OriginDTO;
import com.robinfood.core.entities.OriginEntity;
import com.robinfood.repository.origin.IOriginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IGetOriginsByIdsUseCase
 */
@Service
@Slf4j
public class GetOriginsByIdsUseCase implements IGetOriginsByIdsUseCase {

    private final IOriginRepository originRepository;

    public GetOriginsByIdsUseCase(IOriginRepository originRepository) {
        this.originRepository = originRepository;
    }

    public List<OriginDTO> invoke(List<Long> originIds) {

        log.info("Execute Get Origins By Ids Use Case origins {}", originIds);

        Iterable<OriginEntity> origins = this.originRepository.findAllById(originIds);

        log.info("Result Origin Repository {}", origins);

        List<OriginEntity> originEntities = new ArrayList<>();
        origins.forEach(originEntities::add);

        return originEntities.stream().map(OriginMapper::originEntityToOriginDto).collect(Collectors.toList());
    }

}
