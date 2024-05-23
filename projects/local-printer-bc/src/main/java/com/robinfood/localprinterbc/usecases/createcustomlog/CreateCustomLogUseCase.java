package com.robinfood.localprinterbc.usecases.createcustomlog;

import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.loggings.CreateOrderCustomLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateCustomLogUseCase implements ICreateCustomLogUseCase {

    @Override
    public void invoke(OrderDetailDTO orderDetailDTO) {
        CreateOrderCustomLog.invoke(orderDetailDTO);
    }
}
