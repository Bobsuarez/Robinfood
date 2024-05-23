package com.robinfood.customersbc.thirdparties.services;

import com.robinfood.customersbc.thirdparties.domains.ThirdPartyDomain;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ThirdPartyService {

    Mono<List<ThirdPartyDomain>> getThirdPartiesByExternalId(Long externalId);
}
