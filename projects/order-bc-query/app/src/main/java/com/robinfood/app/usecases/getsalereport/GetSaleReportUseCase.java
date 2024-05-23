package com.robinfood.app.usecases.getsalereport;

import com.robinfood.repository.orders.IOrdersRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.AsyncConfigConstants.SalesReport.THREAD_NAME;
import static com.robinfood.core.constants.DateTimeConstants.START_HOUR;
import static com.robinfood.core.constants.DateTimeConstants.START_MINUTE;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;

@Component
public class GetSaleReportUseCase implements IGetSaleReportUseCase {

    private final IOrdersRepository ordersRepository;

    public GetSaleReportUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    @Async(THREAD_NAME)
    public CompletableFuture<BigDecimal> invoke(
            LocalDateTime dateToSearch,
            Long idCompany,
            List<Long> statusIds
    ) {
        BigDecimal sumDecimalOrder = ordersRepository
                .sumTotalByLocalDateAndCompanyIdAndPaidAndLocalTimeBetweenAndStatusIdNotIn(
                        dateToSearch.toLocalDate(),
                        dateToSearch.toLocalTime(),
                        idCompany,
                        LocalTime.of(START_HOUR, START_MINUTE),
                        ORDER_PAID,
                        statusIds
                );
        return CompletableFuture.completedFuture(sumDecimalOrder);
    }
}
