package com.robinfood.configurations.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.models.AbstractBaseEntity;
import javax.validation.constraints.NotNull;

public interface AuditLogService {

    void createAuditLog(@NotNull AbstractBaseEntity entity) throws JsonProcessingException;

    void updateAuditLog(@NotNull AbstractBaseEntity oldEntity,
        @NotNull AbstractBaseEntity newEntity) throws JsonProcessingException;

    void deleteAuditLog(@NotNull AbstractBaseEntity entity) throws JsonProcessingException;

}
