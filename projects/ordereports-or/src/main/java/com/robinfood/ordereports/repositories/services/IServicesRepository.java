package com.robinfood.ordereports.repositories.services;

import com.robinfood.ordereports.dtos.orders.DeliveryCourierServiceDTO;

public interface IServicesRepository {

    DeliveryCourierServiceDTO getServices(String token, String transactionUuid);
}
