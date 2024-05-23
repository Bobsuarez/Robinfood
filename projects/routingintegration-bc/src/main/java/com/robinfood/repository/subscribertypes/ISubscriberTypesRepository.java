package com.robinfood.repository.subscribertypes;

import com.robinfood.entities.SubscriberTypesEntity;

public interface ISubscriberTypesRepository {

    SubscriberTypesEntity findById(Long subscriberTypeId);
}
