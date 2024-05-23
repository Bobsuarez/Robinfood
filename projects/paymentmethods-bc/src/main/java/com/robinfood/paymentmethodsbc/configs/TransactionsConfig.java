package com.robinfood.paymentmethodsbc.configs;

import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Data
@Component
@ConfigurationProperties(prefix = "transactions")
public class TransactionsConfig {
    public static final String STATUS_ACCEPTED_ID = "accepted";
    public static final String STATUS_PENDING_ID = "pending";
    public static final String STATUS_REJECTED_ID = "rejected";
    public static final String STATUS_CANCELED_ID = "canceled";
    public static final String STATUS_ERROR_ID = "error";

    public static final String STATUS_REFUND_ACCEPTED_ID = "refund-accepted";
    public static final String STATUS_REFUND_PENDING_ID = "refund-pending";
    public static final String STATUS_REFUND_REJECTED_ID = "refund-rejected";
    public static final String STATUS_REFUND_REQUESTED_ID = "refund-requested";

    public static final String ENTITY_ORDERS_ID = "orders";
    public static final String ENTITY_TRANSACTIONS_ID = "transactions";

    public static final String FAILED_INTERNAL_ID = "internal";
    public static final String FAILED_GATEWAY_ID = "gateway";

    private Map<String, Long> status;

    private Map<String, Long> entities;

    private Map<String, Long> failed;

    public Long getStatusId(final String statusCodeName) {
        return status.get(statusCodeName);
    }

    public Long getEntityId(final String entityCodeName) {
        return entities.get(entityCodeName);
    }

    public Long getFailedTypeId(final String failedTypeName) {
        return failed.get(failedTypeName);
    }

    public boolean isStatusAccepted(@NotNull Long statusId) {
        return statusId.longValue() == getStatusId(STATUS_ACCEPTED_ID).longValue();
    }

    public boolean isStatusPending(@NotNull Long statusId) {
        return statusId.longValue() == getStatusId(STATUS_PENDING_ID).longValue();
    }

    public boolean isStatusRejected(@NotNull Long statusId) {
        return statusId.longValue() == getStatusId(STATUS_REJECTED_ID).longValue();
    }

    public boolean isStatusCanceled(@NotNull Long statusId) {
        return statusId.longValue() == getStatusId(STATUS_CANCELED_ID).longValue();
    }

    public boolean isStatusError(@NotNull Long statusId) {
        return statusId.longValue() == getStatusId(STATUS_ERROR_ID).longValue();
    }

    public boolean isStatusRefundAccepted(@NotNull Long statusId) {
        return statusId.longValue() == getStatusId(STATUS_REFUND_ACCEPTED_ID).longValue();
    }

    public boolean isStatusRefundPending(@NotNull Long statusId) {
        return statusId.longValue() == getStatusId(STATUS_REFUND_PENDING_ID).longValue();
    }

    public boolean isStatusRefundRejected(@NotNull Long statusId) {
        return statusId.longValue() == getStatusId(STATUS_REFUND_REJECTED_ID).longValue();
    }
}
