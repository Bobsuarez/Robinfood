package co.com.robinfood.queue.usecases.token;

import co.com.robinfood.queue.persistencia.entity.token.TokenModelEntity;

public interface ITokenToBusinessCapabilityRepository {

    TokenModelEntity get();
}
