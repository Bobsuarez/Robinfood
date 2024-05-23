package com.robinfood.repository.orderpos;

import com.robinfood.core.entities.PosResolutionsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderPosRepository extends CrudRepository<PosResolutionsEntity, Long> {

    Optional<PosResolutionsEntity> findByPosIdAndCurrent(Long posId, int current);

    Optional<List<PosResolutionsEntity>> findByStoreIdAndCurrent(Long storeId, int current);

}
