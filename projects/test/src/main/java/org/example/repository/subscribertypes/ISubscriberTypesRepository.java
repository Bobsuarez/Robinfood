package org.example.repository.subscribertypes;

import org.example.entities.SubscriberTypesEntity;

public interface ISubscriberTypesRepository {

    SubscriberTypesEntity findById(Long subscriberTypeId);
}
