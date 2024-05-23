package com.robinfood.app.usecases.getsalereport;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IGetSaleReportUseCase {

    CompletableFuture<BigDecimal> invoke(
            LocalDateTime dateToSearch,
            Long idCompany,
            List<Long> statusIds
    );
}
