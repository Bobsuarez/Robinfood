package com.robinfood.repository.eventhistory;

import com.robinfood.entities.EventHistoryEntity;

public interface IEventHistoryRepository {

    void saveEventHistory(EventHistoryEntity eventHistoryEntity, String token);

}
