package com.robinfood.repository.mocks;

import com.google.gson.JsonObject;

public class LambdaExchangeJsonObjectMock {

    public static JsonObject getDataDefault() {

        JsonObject json = new JsonObject();
        JsonObject date = new JsonObject();

        json.add("data", date);

       return json;
    }
}
