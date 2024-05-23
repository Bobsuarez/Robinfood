package com.robinfood.configurationslocalserverbc.repositories;

import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITemplateGroupRepository extends JpaRepository<TemplateGroupsEntity, Long> {
}
