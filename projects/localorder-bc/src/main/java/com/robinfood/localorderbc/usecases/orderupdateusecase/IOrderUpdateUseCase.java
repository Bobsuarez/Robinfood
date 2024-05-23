package com.robinfood.localorderbc.usecases.orderupdateusecase;

public interface IOrderUpdateUseCase {

    void invoke(Long orderId, String orderJson);
}
