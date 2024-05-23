package com.robinfood.app.usecases.getordertotaldailysales;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.ordertotaldailysales.IOrderTotalDailySalesRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetOrderTotalDailySalesUseCase implements IGetOrderTotalDailySalesUseCase {

    private final IOrderTotalDailySalesRepository orderTotalDailySalesRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public GetOrderTotalDailySalesUseCase(IOrderTotalDailySalesRepository orderTotalDailySalesRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase) {
        this.orderTotalDailySalesRepository = orderTotalDailySalesRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    @Override
    public Result<List<OrderTotalDailySalesDTO>> invoke(int storeId, LocalDate date) {
        var token = getTokenBusinessCapabilityUseCase.invoke();
        return orderTotalDailySalesRepository.getOrderTotalDailySales(token.getAccessToken(), storeId, date);
    }

}
