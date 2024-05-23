package com.robinfood.core.mappers;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.reportsalesstore.GroupReportSalesStoreDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportGroupPaymentMethodsDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportSalesPaymentMethodDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportSalesStoresDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportTotalOrdersPaymentDTO;
import com.robinfood.core.dtos.salesstore.SalesPaymentMethodDTO;
import com.robinfood.core.dtos.salesstore.SalesStoresDTO;

import java.util.ArrayList;
import java.util.List;

public final class ReportSalesStoreMappers {

    private ReportSalesStoreMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static List<ReportSalesPaymentMethodDTO>
    listPaymentMethodsFilterDTOAndSalesStoresDTOToReportSalesPaymentMethodDTO (
            List<PaymentMethodsFilterDTO> listPaymentMethods,
            SalesStoresDTO salesStoresDTO
    ) {

        List<ReportSalesPaymentMethodDTO>  listReportPaymentMethod = new ArrayList<>();

        salesStoresDTO.getPaymentMethods().forEach( (SalesPaymentMethodDTO salesPaymentMethodDTO) ->{
            ReportSalesPaymentMethodDTO reportPaymentMethod = ReportSalesPaymentMethodDTO.builder()
                    .name(String.valueOf(listPaymentMethods.stream()
                            .filter((PaymentMethodsFilterDTO pmFilter) -> pmFilter.getId()
                                    .equals(salesPaymentMethodDTO.getId())).findFirst().get().getName()))
                    .id(salesPaymentMethodDTO.getId())
                    .orders(ReportOrderPaymentMappers.salesPaymentDTOToReportOrdesPaymentDTO(salesPaymentMethodDTO))
                    .build();

            listReportPaymentMethod.add(reportPaymentMethod);
        });

        return listReportPaymentMethod;
    }

    public static ReportSalesStoresDTO
            salesStoresDTOAndStoreDTOAndReportTotalOrdersPaymentDTOToReportSalesStoresDTO (
            List<ReportSalesPaymentMethodDTO>  listReportPaymentMethod,
            ReportTotalOrdersPaymentDTO reportTotalOrderPayment,
            SalesStoresDTO salesStoresDTO,
            StoreDTO storeDTO
    ) {

        return ReportSalesStoresDTO.builder()
                .store(GroupReportSalesStoreDTO.builder()
                        .id(salesStoresDTO.getId())
                        .name(storeDTO.getName())
                        .paymentMethods(
                                ReportGroupPaymentMethodsDTO.builder()
                                        .items(listReportPaymentMethod)
                                        .total(reportTotalOrderPayment)
                                        .build()
                        ).build())
                .build();
    }
}
