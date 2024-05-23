package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.models.UserStore;
import com.robinfood.configurations.repositories.jpa.UserStoreRepository;
import com.robinfood.configurations.services.UserStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserStoreServiceImpl implements UserStoreService {

    private final UserStoreRepository userStoreRepository;

    public UserStoreServiceImpl(UserStoreRepository userStoreRepository){
        this.userStoreRepository = userStoreRepository;
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public UserStore findStoreByUserId(Long userId) {

        return userStoreRepository.findByUserId(userId).orElseThrow(() ->
                new EntityNotFoundException("User with id " + userId + " was not found") );

    }
}
