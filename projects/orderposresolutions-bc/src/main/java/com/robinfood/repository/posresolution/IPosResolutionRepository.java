package com.robinfood.repository.posresolution;

import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.enums.OrderByEnum;

import java.math.BigInteger;
import java.util.List;

public interface IPosResolutionRepository {

    List<BigInteger> saveAll(List<PosResolutionEntity> posResolutionEntities);

    Boolean existsByPosIdAndStatus(Long posId, Boolean status);

    PosResolutionEntity findByPosIdAndStatus(Long posId, Boolean status);

    BigInteger updateCurrentAndDicStatusIdById(PosResolutionEntity posResolutionEntity);

    PosResolutionEntity findByResolutionId(Long resolutionId);

    BigInteger updateByResolutionId(PosResolutionEntity posResolutionEntity);

    PosResolutionEntity findById(Long id);

    List<PosResolutionEntity> findAllByPosIdAndStatus(Long posId, Integer status);

    Integer countSearchResolutions(Long resolutionId, Boolean status, String valueCustomFilter, Boolean withPos);

    List<PosResolutionEntity> searchResolutions(
            Boolean status,
            OrderByEnum orderByEnum,
            Integer page,
            Long resolutionId,
            Integer size,
            String valueCustomFilter,
            Boolean withPos
    );

}
