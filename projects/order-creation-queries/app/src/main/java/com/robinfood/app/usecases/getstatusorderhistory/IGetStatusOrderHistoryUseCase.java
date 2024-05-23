package com.robinfood.app.usecases.getstatusorderhistory;

import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;

import java.util.List;

public interface IGetStatusOrderHistoryUseCase {

    List<StatusOrderHistoryDTO> invoke(String uuid);
}
