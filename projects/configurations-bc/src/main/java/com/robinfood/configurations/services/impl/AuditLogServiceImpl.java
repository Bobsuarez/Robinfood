package com.robinfood.configurations.services.impl;

import static net.logstash.logback.argument.StructuredArguments.v;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.components.AuthenticationFacade;
import com.robinfood.configurations.dto.v1.security.UserDTO;
import com.robinfood.configurations.models.AbstractBaseEntity;
import com.robinfood.configurations.models.AuditLog;
import com.robinfood.configurations.repositories.jpa.AuditLogRepository;
import com.robinfood.configurations.services.AuditLogService;
import com.robinfood.configurations.utils.AuditLogsUtil;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ObjectMapper objectMapper;


    public AuditLogServiceImpl(AuditLogRepository auditLogRepository,
        AuthenticationFacade authenticationFacade,
        ObjectMapper objectMapper) {
        this.auditLogRepository = auditLogRepository;
        this.authenticationFacade = authenticationFacade;
        this.objectMapper = objectMapper;
    }

    @BasicLog
    @Override
    public void createAuditLog(@NotNull AbstractBaseEntity entity) throws JsonProcessingException {

        Long userId = getUserId();

        log.trace("Generating create audit log with object {}", objectMapper.writeValueAsString(entity));
        AuditLog auditLog = AuditLogsUtil.getCreateAuditLog(entity, userId);
        log.trace("Create audit log generated successfully. {}", objectMapper.writeValueAsString(auditLog));

        log.trace("Saving create audit log {}", objectMapper.writeValueAsString(auditLog));
        auditLogRepository.save(auditLog);
        log.trace("Action create audit log saved successfully");

    }

    @BasicLog
    @Override
    public void updateAuditLog(@NotNull AbstractBaseEntity oldEntity,
        @NotNull AbstractBaseEntity newEntity) throws JsonProcessingException {
        Long userId = getUserId();

        newEntity.setUpdatedAt(LocalDateTime.now());
        String oldEntityToString = objectMapper.writeValueAsString(oldEntity);
        String newEntityToString = objectMapper.writeValueAsString(newEntity);

        log.trace("Generating update audit log with object {}",
            objectMapper.writeValueAsString(newEntity));
        AuditLog auditLog = AuditLogsUtil.getUpdateAuditLog(
            newEntity.getId(), oldEntityToString, newEntityToString, newEntity, userId);
        log.trace("Update audit log generated successfully. {}",
            objectMapper.writeValueAsString(auditLog));

        log.trace("Saving update audit log {}", objectMapper.writeValueAsString(auditLog));
        auditLogRepository.save(auditLog);
        log.trace("Action update audit log saved successfully");
    }

    @BasicLog
    @Override
    public void deleteAuditLog(@NotNull AbstractBaseEntity entity) throws JsonProcessingException {
        Long userId = getUserId();
        LocalDateTime now = LocalDateTime.now();
        entity.setUpdatedAt(now);
        entity.setDeletedAt(now);

        log.trace("Generating delete audit log with object {}",
            v("data", objectMapper.writeValueAsString(entity)));
        AuditLog auditLog = AuditLogsUtil.getDeleteAuditLog(entity, userId);
        log.trace("Delete audit log generated successfully. {}",
            v("data", objectMapper.writeValueAsString(auditLog)));

        log.trace("Saving delete audit log {}", objectMapper.writeValueAsString(auditLog));
        auditLogRepository.save(auditLog);
        log.trace("Action delete audit log saved successfully");
    }

    private Long getUserId() {
        Authentication authentication = authenticationFacade.getAuthentication();
        log.trace("Getting the user id");
        Long userId = ((UserDTO) authentication.getPrincipal()).getId();
        log.trace("User id obtained successfully. {}", userId);

        return userId;
    }
}

