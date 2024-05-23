package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.Resolution;

import java.util.List;
import java.util.Optional;

public interface ResolutionRepository extends SoftDeleteRepository<Resolution, Long> {

    boolean existsByPosIdAndStatus(Long id, Integer status);

    Optional<Resolution> findByPosIdAndStatus(Long id, Integer status);

    Optional<List<Resolution>> findAllByPosIdAndStatus(Long id, Integer status);
}
