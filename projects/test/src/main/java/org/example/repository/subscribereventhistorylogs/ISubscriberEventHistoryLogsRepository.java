package org.example.repository.subscribereventhistorylogs;


import java.math.BigInteger;
import org.example.entities.SubscriberEventHistoryLogsEntity;

public interface ISubscriberEventHistoryLogsRepository {

    BigInteger save(SubscriberEventHistoryLogsEntity subscriberEventHistoryLogsEntity);

    SubscriberEventHistoryLogsEntity searchById(Long id);
}
