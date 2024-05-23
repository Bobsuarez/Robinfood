package com.robinfood.configurationslocalserverbc.repositories;

import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsStoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITemplateGroupsStoresRepository extends JpaRepository<TemplateGroupsStoresEntity, Long> {

    Optional<TemplateGroupsStoresEntity> findByStoreIdAndActive(Long storeId, Boolean active);

}
