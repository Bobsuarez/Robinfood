package com.robinfood.repository.lambdaexchange;

import com.google.gson.JsonObject;
import com.robinfood.core.enums.Result;

public interface ILambdaExchangeRepository {

    /**
     * get the data exchanges
     *
     * @param currentDate current date time to consult the records
     * @param previousDate previous date time to consult the records
     * @param token token auth service
     * @return data of lambda exchange
     */
    Result<JsonObject> getExchanges(
            String currentDate,
            String previousDate,
            String token
    );
}
