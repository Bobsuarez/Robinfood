package com.robinfood.changestatusor.mappers;

import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.changestatusor.models.retrofit.TokenRequest;
import com.robinfood.changestatusor.utilities.ObjectMapperSingleton;

public final class RequestJsonMapper {

    private RequestJsonMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static String buildToRequestJson(ChangeStateOrderRequestEntity changeStateOrderRequestEntity){

        return ObjectMapperSingleton.objectToJson(changeStateOrderRequestEntity);
    }

    public static String buildToTokenRequestJson(TokenRequest tokenRequest){

        return ObjectMapperSingleton.objectToJson(tokenRequest);
    }
}
