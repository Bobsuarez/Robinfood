package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.models.AbstractBaseEntity;
import javax.validation.constraints.NotNull;

public interface AuditLogService {

    void createAuditLog(@NotNull AbstractBaseEntity entity) throws JsonProcessingException;

    void updateAuditLog(@NotNull AbstractBaseEntity oldEntity,
        @NotNull AbstractBaseEntity newEntity) throws JsonProcessingException;

    void deleteAuditLog(@NotNull AbstractBaseEntity entity) throws JsonProcessingException;

}
