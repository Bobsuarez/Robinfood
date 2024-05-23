package com.robinfood.usecases.saveeventhistory;

/**
 * Save event history by subscriber use case
 */
public interface ISaveEventHistoryUseCase {

    void invoke(Long eventDispatched, Long flowEventLogId, Long subscriberId, String token, String uuid);
}
