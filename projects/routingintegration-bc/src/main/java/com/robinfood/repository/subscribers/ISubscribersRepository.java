package com.robinfood.repository.subscribers;

import com.robinfood.entities.SubscribersEntity;

public interface ISubscribersRepository {

    SubscribersEntity findById(Long subscriberId);
}
