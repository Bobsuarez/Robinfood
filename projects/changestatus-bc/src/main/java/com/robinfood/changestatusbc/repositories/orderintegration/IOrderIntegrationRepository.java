package com.robinfood.changestatusbc.repositories.orderintegration;

import com.robinfood.changestatusbc.entities.OrderIntegrationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderIntegrationRepository extends CrudRepository<OrderIntegrationEntity, Long> {

    Optional<OrderIntegrationEntity> findByIntegrationId(String integrationId);
}
