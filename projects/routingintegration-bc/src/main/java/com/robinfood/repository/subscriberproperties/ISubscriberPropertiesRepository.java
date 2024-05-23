package com.robinfood.repository.subscriberproperties;

import com.robinfood.entities.SubscriberPropertiesEntity;

import java.util.List;

public interface ISubscriberPropertiesRepository {

    List<SubscriberPropertiesEntity> findBySubscriberId(Long subscriberId);
}
