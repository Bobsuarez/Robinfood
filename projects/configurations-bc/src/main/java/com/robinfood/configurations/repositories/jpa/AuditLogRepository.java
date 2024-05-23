package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.AuditLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends CrudRepository<AuditLog, Long> {

}
