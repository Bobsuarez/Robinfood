package com.robinfood.app.usecases.getsalebysegmentreport;

import com.robinfood.app.usecases.getlistpaymentSegment.IGetListPaymentMethodsSegmentUseCase;
import com.robinfood.app.usecases.getordersquerybysegmentstatement.IGetOrdersQueryBySegmentStatementUseCase;
import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.entities.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.AsyncConfigConstants.SalesReport.THREAD_NAME;

@Component
@Slf4j
public class GetSaleBySegmentReportUseCase implements IGetSaleBySegmentReportUseCase {

    private final IGetListPaymentMethodsSegmentUseCase getPaymentMethodsListSegmentCaseUse;

    private final IGetOrdersQueryBySegmentStatementUseCase queryBySegmentStatementUseCase;

    public GetSaleBySegmentReportUseCase(
            IGetOrdersQueryBySegmentStatementUseCase queryBySegmentStatementUseCase,
            IGetListPaymentMethodsSegmentUseCase getPaymentMethodsListSegmentCaseUse
    ) {
        this.queryBySegmentStatementUseCase = queryBySegmentStatementUseCase;
        this.getPaymentMethodsListSegmentCaseUse = getPaymentMethodsListSegmentCaseUse;
    }

    @Override
    @Async(THREAD_NAME)
    public CompletableFuture<List<OrderEntity>> invoke(
            DataIdsToFindTheSegment dataRequestDTO,
            LocalDateTime dateToSearch
    ) {

        List<OrderEntity> orderEntityListCurrent =
                queryBySegmentStatementUseCase.invoke(dateToSearch, dataRequestDTO);

        log.info(" Invoke list size  : {}", orderEntityListCurrent.size());

        orderEntityListCurrent = getPaymentMethodsListSegmentCaseUse
                .filterAndRemoverOrders(orderEntityListCurrent, dataRequestDTO.getPaymentsMethodsList());

        return CompletableFuture.completedFuture(orderEntityListCurrent);
    }
}
