package com.robinfood.core.logging.mappeddiagnosticcontext;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.logging.constants.CompanyEnum;
import com.robinfood.core.logging.constants.OriginConsumerEnum;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.core.util.SlugGeneratorUtil;
import org.slf4j.MDC;

import java.util.Objects;

import static com.robinfood.core.constants.GlobalConstants.BRAND_AUDIT;
import static com.robinfood.core.constants.GlobalConstants.COMPANY_AUDIT;
import static com.robinfood.core.constants.GlobalConstants.ORIGIN_AUDIT;
import static com.robinfood.core.constants.GlobalConstants.PAID_AUDIT;
import static com.robinfood.core.constants.GlobalConstants.STORE_AUDIT;
import static com.robinfood.core.constants.GlobalConstants.UUID_AUDIT;

public final class CreateTransactionCustomLog {

    private CreateTransactionCustomLog() {
        throw new IllegalStateException("Utility class");
    }

    public static void invoke(TransactionRequestDTO transactionRequest) {

        removeAll();

        final Long companyId = transactionRequest.getCompany().getId();
        final String companyName = CompanyEnum.parseName(companyId);
        final String paidTransaction = transactionRequest.getPaid().toString();

        addUuid(transactionRequest);

        MDC.put(COMPANY_AUDIT, companyName);
        MDC.put(PAID_AUDIT, paidTransaction);
        SaveDataInMemoryUtil.setData(COMPANY_AUDIT, companyName);
        SaveDataInMemoryUtil.setData(PAID_AUDIT, paidTransaction);
    }

    public static void addUuid(TransactionRequestDTO transactionRequestDTO) {

        transactionRequestDTO.getOrders()
                .stream()
                .findFirst()
                .ifPresent(CreateTransactionCustomLog::processOrderDTO);
    }

    private static void processOrderDTO(OrderDTO orderDTO) {

        if (!Objects.isNull(orderDTO.getUuid())) {
            SaveDataInMemoryUtil.setData(UUID_AUDIT, orderDTO.getUuid().toString());
            MDC.put(UUID_AUDIT, orderDTO.getUuid().toString());
        }

        final String storeName = SlugGeneratorUtil.toSlug(orderDTO.getStore().getName());
        final String originName = OriginConsumerEnum.parseName(orderDTO.getOrigin().getId());
        final String brandName = orderDTO.getBrand().getName();

        MDC.put(BRAND_AUDIT, brandName);
        MDC.put(ORIGIN_AUDIT, originName);
        MDC.put(STORE_AUDIT, storeName);
        SaveDataInMemoryUtil.setData(BRAND_AUDIT, brandName);
        SaveDataInMemoryUtil.setData(ORIGIN_AUDIT, originName);
        SaveDataInMemoryUtil.setData(STORE_AUDIT, storeName);
    }

    public static void addUuid() {

        String brandString = (String) SaveDataInMemoryUtil.getValue(BRAND_AUDIT);
        String companyString = (String) SaveDataInMemoryUtil.getValue(COMPANY_AUDIT);
        String originString = (String) SaveDataInMemoryUtil.getValue(ORIGIN_AUDIT);
        String paidString = (String) SaveDataInMemoryUtil.getValue(PAID_AUDIT);
        String storeString = (String) SaveDataInMemoryUtil.getValue(STORE_AUDIT);
        String uuidString = (String) SaveDataInMemoryUtil.getValue(UUID_AUDIT);

        MDC.put(BRAND_AUDIT, brandString);
        MDC.put(COMPANY_AUDIT, companyString);
        MDC.put(ORIGIN_AUDIT, originString);
        MDC.put(PAID_AUDIT, paidString);
        MDC.put(STORE_AUDIT, storeString);
        MDC.put(UUID_AUDIT, uuidString);
    }

    public static void removeHits() {
        MDC.remove(BRAND_AUDIT);
        MDC.remove(COMPANY_AUDIT);
        MDC.remove(ORIGIN_AUDIT);
        MDC.remove(PAID_AUDIT);
        MDC.remove(STORE_AUDIT);
        MDC.remove(UUID_AUDIT);
    }

    public static void removeAll() {
        MDC.clear();
        SaveDataInMemoryUtil.removeValue(BRAND_AUDIT);
        SaveDataInMemoryUtil.removeValue(COMPANY_AUDIT);
        SaveDataInMemoryUtil.removeValue(ORIGIN_AUDIT);
        SaveDataInMemoryUtil.removeValue(PAID_AUDIT);
        SaveDataInMemoryUtil.removeValue(STORE_AUDIT);
        SaveDataInMemoryUtil.removeValue(UUID_AUDIT);
    }
}
