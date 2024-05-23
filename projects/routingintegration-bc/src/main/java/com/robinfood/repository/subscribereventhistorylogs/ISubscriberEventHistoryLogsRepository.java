package com.robinfood.repository.subscribereventhistorylogs;

import com.robinfood.entities.SubscriberEventHistoryLogsEntity;

import java.math.BigInteger;

public interface ISubscriberEventHistoryLogsRepository {

    BigInteger save(SubscriberEventHistoryLogsEntity subscriberEventHistoryLogsEntity);

    SubscriberEventHistoryLogsEntity searchById(Long id);
}
