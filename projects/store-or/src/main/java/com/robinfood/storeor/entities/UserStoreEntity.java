package com.robinfood.storeor.entities;

import lombok.Data;

@Data
public class UserStoreEntity {

    private final Long userId;
    private final StoreEntity store;
}
