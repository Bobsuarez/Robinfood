package com.robinfood.app.usecases.hasuserappliedconsumptiontoday;

import java.time.LocalDate;

/**
 * Use case that checks the orders made by a certain user on a certain date and
 * checks if the consumption tax was applied on at least one order
 */
public interface IHasUserAppliedConsumptionTodayUseCase {

    /**
     * Checks if the user has applied the consumption discount to any order created at any date.
     * @param createdAt the date when the orders to check were created
     * @param userId the id of the user
     * @return true if the user has applied consumption discount to any order on a certain date,
     * false otherwise
     */
    Boolean invoke(LocalDate createdAt, Long userId);
}
