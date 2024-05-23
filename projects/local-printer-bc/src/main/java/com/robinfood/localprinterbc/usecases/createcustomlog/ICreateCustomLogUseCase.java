package com.robinfood.localprinterbc.usecases.createcustomlog;

import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;

public interface ICreateCustomLogUseCase {

    void invoke(OrderDetailDTO orderDetailDTO);
}
