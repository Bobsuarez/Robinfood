package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.UserStore;
import com.robinfood.configurations.repositories.jpa.UserStoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserStoreServiceImplTest {

    private static final Long TEST_LONG = 1L;

    @InjectMocks
    private UserStoreServiceImpl userStoreService;

    @Mock
    private UserStoreRepository userStoreRepository;

    @Test
    void test_FindStoreById_Should_ReturnAStore_When_UserMatchAStore() {

        UserStore userStore = UserStore.builder()
                .store(new Store())
                .build();

        when(userStoreRepository.findByUserId(anyLong()))
                .thenReturn(Optional.ofNullable(userStore));

        assertAll(() -> userStoreService.findStoreByUserId(TEST_LONG));
    }

    @Test
    void test_FindStoreById_Should_ReturnNotFound_When_UserStoreIsNull() {

        when(userStoreRepository.findByUserId(anyLong()))
                .thenReturn(Optional.ofNullable(null));

        assertThrows(EntityNotFoundException.class, () -> userStoreService.findStoreByUserId(TEST_LONG));
    }
}
