package com.robinfood.app.services.orderdiscarded;

import com.robinfood.core.dtos.OrderDiscardedInfoDTO;

public interface IOrderDiscardedService {

    OrderDiscardedInfoDTO validateAndUpdate(String uuid);
}
