package com.robinfood.configurations.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.enums.AuditLogsActionsEnum;
import com.robinfood.configurations.models.AbstractBaseEntity;
import com.robinfood.configurations.models.AuditLog;
import javax.validation.constraints.NotNull;

public final class AuditLogsUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private AuditLogsUtil() {
    }

    public static AuditLog getCreateAuditLog(AbstractBaseEntity entity, Long userId)
        throws JsonProcessingException {

        String modifications = objectMapper.writeValueAsString(entity);

        return AuditLog.builder()
            .action(AuditLogsActionsEnum.AUDIT_LOGS_CREATED.getAuditLogsAction())
            .userId(userId)
            .modelId(entity.getId())
            .modelName(entity.getClass().getSimpleName())
            .modification(String.format("{} => %s", modifications))
            .build();
    }

    public static AuditLog getUpdateAuditLog(Long id, @NotNull String oldEntity,
        @NotNull String newEntity, AbstractBaseEntity entity, Long userId) {

        return AuditLog.builder()
            .action(AuditLogsActionsEnum.AUDIT_LOGS_UPDATE.getAuditLogsAction())
            .userId(userId)
            .modelId(id)
            .modelName(entity.getClass().getSimpleName())
            .modification(String.format("%s => %s", oldEntity, newEntity))
            .build();
    }

    public static AuditLog getDeleteAuditLog(AbstractBaseEntity entity, Long userId)
        throws JsonProcessingException {

        String modifications = objectMapper.writeValueAsString(entity);

        return AuditLog.builder()
            .action(AuditLogsActionsEnum.AUDIT_LOGS_DELETE.getAuditLogsAction())
            .userId(userId)
            .modelId(entity.getId())
            .modelName(entity.getClass().getSimpleName())
            .modification(String.format("%s => {}", modifications))
            .build();
    }

}
