package com.robinfood.repository.statusorderhistory;

import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

public interface IStatusOrderHistoryRepository {

    Result<List<StatusOrderHistoryDTO>> getStatusOrderHistory(String token, String uuid);
}
