package com.robinfood.taxes.enums;

import javax.validation.constraints.NotNull;

public enum AuditLogsActionsEnum {

    AUDIT_LOGS_CREATED("CREATED"),
    AUDIT_LOGS_UPDATE("UPDATE"),
    AUDIT_LOGS_DELETE("DELETE");

    private final String auditLogsAction;

    AuditLogsActionsEnum(@NotNull final String auditLogsAction) {
        this.auditLogsAction = auditLogsAction;
    }

    public String getAuditLogsAction() {
        return auditLogsAction;
    }

}
