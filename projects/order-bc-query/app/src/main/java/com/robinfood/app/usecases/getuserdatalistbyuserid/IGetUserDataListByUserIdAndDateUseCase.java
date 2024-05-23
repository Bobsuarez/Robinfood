package com.robinfood.app.usecases.getuserdatalistbyuserid;

import com.robinfood.core.dtos.UserDataDTO;
import java.time.LocalDate;
import java.util.List;


public interface IGetUserDataListByUserIdAndDateUseCase {

    /**
     * Gets all the user data for all the orders made by a user on a certain date
     * @param createdAt the date when the orders to check were created
     * @param userId the id of the user
     * @return the list of user data information for all orders made by a certain user
     * in a certain date
     */
    List<UserDataDTO> invoke(
            LocalDate createdAt,
            Long userId
    );
}
