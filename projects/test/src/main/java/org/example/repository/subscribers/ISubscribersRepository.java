package org.example.repository.subscribers;

import org.example.entities.SubscribersEntity;

public interface ISubscribersRepository {

    SubscribersEntity findById(Long subscriberId);
}
