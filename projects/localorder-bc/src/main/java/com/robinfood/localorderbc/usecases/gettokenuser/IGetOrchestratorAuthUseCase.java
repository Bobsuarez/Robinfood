package com.robinfood.localorderbc.usecases.gettokenuser;

import com.robinfood.localorderbc.entities.token.TokenModel;

public interface IGetOrchestratorAuthUseCase {

    TokenModel invoke(String scope, String authorizationToken, String getterKey);

}
