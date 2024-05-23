package com.robinfood.network.http.api;

import com.robinfood.entities.TokenEntity;
import com.robinfood.entities.request.TokenRequestEntity;
import com.robinfood.network.http.connection.HttpClientConnection;
import com.robinfood.utils.ObjectMapperSingleton;
import lombok.SneakyThrows;

import static com.robinfood.constants.Constants.SSO_ATTRIBUTE_RESULT;
import static com.robinfood.constants.Constants.TOKEN;
import static com.robinfood.constants.Constants.URL_SSO;

/**
 * SSO API
 */

public class SsoAPI extends HttpClientConnection {

    /**
     * Get Token
     *
     * @param tokenRequestEntity Token Request Entity
     * @return API Token Response Entity
     */
    @SneakyThrows()
    public TokenEntity getToken(TokenRequestEntity tokenRequestEntity) {

        final String URL = URL_SSO + "v1/services";

        String responseBody = connectionProcess(SSO_ATTRIBUTE_RESULT, tokenRequestEntity, TOKEN, URL);

        return ObjectMapperSingleton.jsonToClass(responseBody, TokenEntity.class);
    }
}
