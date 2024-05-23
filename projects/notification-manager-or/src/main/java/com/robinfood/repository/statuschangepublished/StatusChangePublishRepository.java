package com.robinfood.repository.statuschangepublished;

import com.robinfood.entities.ChangeStatusEntity;
import com.robinfood.network.http.api.NotificationManagerMsgAPI;

public class StatusChangePublishRepository implements IStatusChangePublishRepository {

    private final NotificationManagerMsgAPI notificationManagerMsgAPI;

    public StatusChangePublishRepository() {
        notificationManagerMsgAPI = new NotificationManagerMsgAPI();
    }

    public StatusChangePublishRepository(NotificationManagerMsgAPI notificationManagerMsgAPI) {
        this.notificationManagerMsgAPI = notificationManagerMsgAPI;
    }

    @Override
    public void publish(
            ChangeStatusEntity changeStatusEntity,
            String token,
            String url
    ) {
        notificationManagerMsgAPI.statusChangePublished(changeStatusEntity, token, url);
    }
}
