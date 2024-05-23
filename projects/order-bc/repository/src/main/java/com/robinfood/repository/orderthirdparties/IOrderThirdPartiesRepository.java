package com.robinfood.repository.orderthirdparties;
import com.robinfood.core.entities.OrderThirdPartyEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface IOrderThirdPartiesRepository  extends CrudRepository<OrderThirdPartyEntity, Long> {

    Optional<OrderThirdPartyEntity> findByOrderId(Long orderId);
}
