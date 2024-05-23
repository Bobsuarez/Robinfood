package com.robinfood.repository.statuschangepublished;

import com.robinfood.entities.ChangeStatusEntity;
import com.robinfood.network.http.api.NotificationManagerMsgAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class StatusChangePublishRepositoryTest {

    @Mock
    private NotificationManagerMsgAPI notificationManagerMsgAPI;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Publish_Should_ReturnChangeStatusEntity_When_InvokeTheRepository() {

        doNothing().when(notificationManagerMsgAPI)
                .statusChangePublished(any(ChangeStatusEntity.class), anyString(), anyString());

        StatusChangePublishRepository statusChangePublishRepository =
                new StatusChangePublishRepository(notificationManagerMsgAPI);

        statusChangePublishRepository.publish(ChangeStatusEntity.builder().build(), "token", "url");

        verify(notificationManagerMsgAPI, Mockito.times(1))
                .statusChangePublished(any(ChangeStatusEntity.class), anyString(), anyString());
    }
}