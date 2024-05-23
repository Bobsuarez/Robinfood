package com.robinfood.repository.statuschangepublished;

import com.robinfood.entities.ChangeStatusEntity;

/**
 * Status Change Publish Repository
 */
public interface IStatusChangePublishRepository {

    void publish(
            ChangeStatusEntity changeStatusEntity,
            String token,
            String url
    );
}
