package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.UserStore;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserStoreRepository extends SoftDeleteRepository<UserStore, Long>{

    Optional<UserStore> findByUserId(@Param("userId") Long userId);
}
