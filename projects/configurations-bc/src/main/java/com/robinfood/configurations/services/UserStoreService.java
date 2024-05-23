package com.robinfood.configurations.services;

import com.robinfood.configurations.models.UserStore;

public interface UserStoreService {

    UserStore findStoreByUserId(Long userId);
}
