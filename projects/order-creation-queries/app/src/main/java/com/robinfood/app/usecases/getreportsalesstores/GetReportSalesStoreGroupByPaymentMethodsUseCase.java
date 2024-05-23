package com.robinfood.app.usecases.getreportsalesstores;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportSalesPaymentMethodDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportSalesStoresDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportTotalOrdersPaymentDTO;
import com.robinfood.core.dtos.salesstore.SalesStoresDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mappers.ReportSalesStoreMappers;
import com.robinfood.core.mappers.ReportTotalOrdersPaymentMappers;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.store.IStoreRepository;
import com.robinfood.repository.paymentmethods.IPaymentMethodsRepository;
import com.robinfood.repository.salesstore.ISalesStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@Component
@Slf4j
public class GetReportSalesStoreGroupByPaymentMethodsUseCase
        implements IGetReportSalesStoreGroupByPaymentMethodsUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IPaymentMethodsRepository paymentMethodsRepository;

    private final ISalesStoreRepository salesStoreRepository;

    private final IStoreRepository storeRepository;

    public GetReportSalesStoreGroupByPaymentMethodsUseCase (
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IPaymentMethodsRepository paymentMethodsRepository,
            ISalesStoreRepository salesStoreRepository,
            IStoreRepository storeRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.paymentMethodsRepository = paymentMethodsRepository;
        this.salesStoreRepository = salesStoreRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public Result<ReportSalesStoresDTO> invoke(LocalDateTime dateTimeCurrent, Long storeId) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        final StoreDTO storeDTO = ((Result.Success<StoreDTO>) storeRepository.getStore(
                storeId,
                token.getAccessToken()
        )).getData();

        final SalesStoresDTO salesStoresDTOResult =((Result.Success<SalesStoresDTO>) salesStoreRepository
                .getSalesByStoreGroupByPaymentMethods(
                       dateTimeCurrent,
                       storeId,
                       storeDTO.getTimezone(),
                       token.getAccessToken()
               )).getData();

        final List<PaymentMethodsFilterDTO> listPaymentMethods = ((Result.Success<List<PaymentMethodsFilterDTO>>)
                paymentMethodsRepository.getDataPaymentMethods(token.getAccessToken())).getData();

        return buildReportSalesStore(
                listPaymentMethods,
                salesStoresDTOResult,
                storeDTO
        );
    }

    private ReportTotalOrdersPaymentDTO builtTotalReportSalesStore (
            List<ReportSalesPaymentMethodDTO> listReportPaymentMethod
    ) {
        Integer counterSalesAWeekBefore = DEFAULT_INTEGER_VALUE;
        Integer counterSalesCurrent = DEFAULT_INTEGER_VALUE;
        BigDecimal totalSalesAWeekBefore = BigDecimal.ZERO;
        BigDecimal totalSalesCurrent = BigDecimal.ZERO;

        for (ReportSalesPaymentMethodDTO reportSalesPaymentMethodDTO : listReportPaymentMethod) {

            totalSalesAWeekBefore = totalSalesAWeekBefore.add(reportSalesPaymentMethodDTO.getOrders()
                    .getSalesAWeekBefore().getValue());

            totalSalesCurrent = totalSalesCurrent.add(reportSalesPaymentMethodDTO.getOrders()
                    .getSalesCurrent().getValue());

            counterSalesAWeekBefore += reportSalesPaymentMethodDTO.getOrders().getSalesAWeekBefore().getCounter();
            counterSalesCurrent += reportSalesPaymentMethodDTO.getOrders().getSalesCurrent().getCounter();
        }

        return ReportTotalOrdersPaymentMappers.variousSalesTotalsDTOToReportTotalOrdersPaymentDTO(
                counterSalesAWeekBefore,
                counterSalesCurrent,
                totalSalesAWeekBefore,
                totalSalesCurrent
        );
    }

    private Result<ReportSalesStoresDTO> buildReportSalesStore (
            List<PaymentMethodsFilterDTO> listPaymentMethods,
            SalesStoresDTO salesStoresDTO,
            StoreDTO storeDTO
    ) {

        final List<ReportSalesPaymentMethodDTO>  listReportPaymentMethod = ReportSalesStoreMappers
                .listPaymentMethodsFilterDTOAndSalesStoresDTOToReportSalesPaymentMethodDTO(
                        listPaymentMethods,
                        salesStoresDTO
                );

        final ReportTotalOrdersPaymentDTO reportTotalOrderPayment = builtTotalReportSalesStore(
                listReportPaymentMethod
        );

        final ReportSalesStoresDTO getReportSalesStoresDTO =ReportSalesStoreMappers
                .salesStoresDTOAndStoreDTOAndReportTotalOrdersPaymentDTOToReportSalesStoresDTO(
                        listReportPaymentMethod,
                        reportTotalOrderPayment,
                        salesStoresDTO,
                        storeDTO
                );

        return new Result.Success(
                getReportSalesStoresDTO
        );
    }
}
