package com.robinfood.network.http.api;

import com.robinfood.entities.TokenEntity;
import com.robinfood.entities.request.TokenRequestEntity;
import com.robinfood.network.http.connection.HttpClientConnection;
import com.robinfood.util.ObjectMapperSingletonUtil;
import lombok.SneakyThrows;

import static com.robinfood.constants.APIConstants.URL_SSO;
import static com.robinfood.constants.GeneralConstants.SSO_ATTRIBUTE_RESULT;
import static com.robinfood.constants.GeneralConstants.TOKEN;

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

        String responseBody = connectionProcess(
                SSO_ATTRIBUTE_RESULT, tokenRequestEntity, TOKEN, URL_SSO
        );

        return ObjectMapperSingletonUtil.jsonToClass(responseBody, TokenEntity.class);
    }
}
