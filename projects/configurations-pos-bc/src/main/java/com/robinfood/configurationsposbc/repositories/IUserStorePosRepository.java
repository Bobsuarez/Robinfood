package com.robinfood.configurationsposbc.repositories;

import com.robinfood.configurationsposbc.entities.UserStorePosEntity;
import com.robinfood.configurationsposbc.entities.UserStorePosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserStorePosRepository extends JpaRepository<UserStorePosEntity, UserStorePosId> {

    Optional<UserStorePosEntity> findByStoreIdAndUserId(Long storeId, Long userId);
}
