package com.robinfood.app.mocks;

import com.google.gson.JsonObject;

public class LambdaExchangeJsonObjectMock {

    public static JsonObject getDataDefault()  {

        JsonObject json = new JsonObject();
        JsonObject dates = new JsonObject();
        JsonObject exchangeRates = new JsonObject();
        JsonObject usd = new JsonObject();
        JsonObject rates = new JsonObject();
        JsonObject cop = new JsonObject();

        cop.addProperty("COP", 4000);
        cop.addProperty("MXN", 40);

        rates.add("rates",cop);
        usd.add("USD", rates);
        exchangeRates.add("exchangeRates", usd);

        dates.add("2023/03/14", exchangeRates);
        dates.add("2023/03/7", exchangeRates);

        json.add("dates", dates );

        return json;
    }

    public static JsonObject getDataDefaultWithCurrencyMXN()  {

        JsonObject json = new JsonObject();
        JsonObject dates = new JsonObject();
        JsonObject exchangeRates = new JsonObject();
        JsonObject usd = new JsonObject();
        JsonObject rates = new JsonObject();
        JsonObject mxn = new JsonObject();

        mxn.addProperty("MXN", 40);

        rates.add("rates",mxn);
        usd.add("USD", rates);
        exchangeRates.add("exchangeRates", usd);

        dates.add("2023/03/14", exchangeRates);
        dates.add("2023/03/7", exchangeRates);

        json.add("dates", dates );

        return json;
    }
}
