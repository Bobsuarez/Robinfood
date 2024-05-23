package com.robinfood.app.usecases.getorderbyuids;

import com.robinfood.core.dtos.OrderDTO;
import java.util.List;

/**
 * Use case that returns the order detail for some order uids
 */
public interface IGetOrderByUidsUseCase {

    /**
     * Gets the order detail for some order uids
     * @param uids the list of order uid
     * @return he order detail for some order uids
     */
    List<OrderDTO> invoke(List<String> uids);
}
