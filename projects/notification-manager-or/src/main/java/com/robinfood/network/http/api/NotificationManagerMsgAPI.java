package com.robinfood.network.http.api;

import com.robinfood.entities.ChangeStatusEntity;
import com.robinfood.network.http.connection.HttpClientConnection;
import lombok.SneakyThrows;

import static com.robinfood.constants.Constants.ATTRIBUTE_RESULT_DEFAULT;

/**
 * Notification Manager Msg API
 */

public class NotificationManagerMsgAPI extends HttpClientConnection {

    /**
     * Status Change Publish
     *
     * @param changeStatusEntity Change Status Entity
     * @param token              Token
     * @param url                URL
     * @return Change Status Entity
     */
    @SneakyThrows()
    public void statusChangePublished(
            ChangeStatusEntity changeStatusEntity,
            String token,
            String url
    ) {
        connectionProcess(ATTRIBUTE_RESULT_DEFAULT, changeStatusEntity, token, url);
    }
}
