package com.robinfood.core.logging.mappeddiagnosticcontext;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.logging.constants.CompanyEnum;
import com.robinfood.core.logging.constants.OriginConsumerEnum;
import com.robinfood.core.util.SlugGeneratorUtil;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

public final class MessageConsumerCustomLog {

    private MessageConsumerCustomLog() {
        throw new IllegalStateException("Utility class");
    }

    public static void invoke(OrderToCreateDTO orderToCreateDTO) {

        Optional.ofNullable(orderToCreateDTO.getOrders())
                .orElseThrow(() -> new TransactionCreationException(
                        HttpStatus.BAD_REQUEST,
                        "the list order is null",
                        TransactionCreationErrors.INCONSISTENT_TRANSACTION_ERROR
                ));

        UUID uuid = Optional.ofNullable(orderToCreateDTO.getUuid())
                .orElseGet(() -> new UUID(0L, 0L));

        final Long idOrigin = orderToCreateDTO.getOrders().get(DEFAULT_INTEGER_VALUE).getOrigin().getId();
        final String storeName = orderToCreateDTO.getOrders().get(DEFAULT_INTEGER_VALUE).getStore().getName();
        final String brandName = orderToCreateDTO.getOrders().get(DEFAULT_INTEGER_VALUE).getBrand().getName();
        final Long companyId = orderToCreateDTO.getCompany().getId();

        MDC.clear();

        MDC.put("uuid", uuid.toString());
        MDC.put("origin", OriginConsumerEnum.parseName(idOrigin));
        MDC.put("store", SlugGeneratorUtil.toSlug(storeName));
        MDC.put("company", CompanyEnum.parseName(companyId));
        MDC.put("brand", brandName);
        MDC.put("paid", orderToCreateDTO.getPaid().toString());
    }
}
