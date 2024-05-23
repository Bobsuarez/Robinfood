package org.example.repository.subscriberproperties;


import java.util.List;
import org.example.entities.SubscriberPropertiesEntity;

public interface ISubscriberPropertiesRepository {

    List<SubscriberPropertiesEntity> findBySubscriberId(Long subscriberId);
}
