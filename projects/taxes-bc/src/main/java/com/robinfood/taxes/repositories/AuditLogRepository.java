package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.AuditLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends CrudRepository<AuditLog, Long> {
}
