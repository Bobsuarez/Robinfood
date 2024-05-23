package com.robinfood.repository.pickuptime;

import com.robinfood.core.entities.PickupTimeEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository of Pickup time
 */
public interface IPickupTimeRepository extends CrudRepository<PickupTimeEntity, Long> {

    /**
     * Find pickup time by transaction id
     * @param transactionId id of the transaction
     * @return pickup time
     */
    List<PickupTimeEntity> findByTransactionId(Long transactionId);

}
