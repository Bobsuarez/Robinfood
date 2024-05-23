package com.robinfood.app.usecases.getlambdaexchange;

import com.google.gson.JsonObject;
import com.robinfood.core.enums.Result;

/**
 * Use case that returns the data to lambda exchanges
 */
public interface IGetLambdaExchangeUseCase {

    /**
     * Get data lambda exchanges
     *
     * @param currentDate current date time to consult the records
     * @param previousDate previous date time to consult the records
     * @return an object of lambda exchanges
     */
    Result<JsonObject> invoke(
            String currentDate,
            String previousDate
    );
}
