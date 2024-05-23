package com.robinfood.app.usecases.getuserorderhistory;

import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import org.springframework.data.domain.Page;

public interface IGetUserOrderHistoryUseCase {

    /**
     * Gets the order history by user id
     * @param currentPage current page
     * @param perPage records per page
     * @param userId user id
     * @return the order history by user
     */
    Page<ResponseOrderDTO> invoke(
        Integer currentPage,
        Integer perPage,
        Long userId
    );

}
