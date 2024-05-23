package com.robinfood.configurationsposbc.mocks;

import com.robinfood.configurationsposbc.entities.UserStorePosEntity;

import java.time.LocalDateTime;

public class UserStorePosEntityMock {

    public UserStorePosEntity userStorePosEntity = UserStorePosEntity
            .builder()
            .storeId(1L)
            .posId(1L)
            .userId(1L)
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
