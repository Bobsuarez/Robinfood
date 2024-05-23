package com.robinfood.localorderbc.usecases.gettokenuser;


import com.robinfood.localorderbc.entities.token.TokenModel;

public interface IGetOrchestratorLoginUseCase {

    TokenModel invoke(String authorizationToken, String getterKey);

}
