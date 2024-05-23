package com.robinfood.localserver.commons.utilities;

import com.robinfood.localserver.commons.dtos.storeconfiguration.AcceptLanguageDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.StoreDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class StoreInfoConfiguration {

    @Autowired
    private StoreDTO storeConfiguration;

    @Autowired
    private AcceptLanguageDTO acceptLanguage;

    public StoreInfoConfiguration(StoreDTO storeConfiguration, AcceptLanguageDTO acceptLanguage) {
        this.storeConfiguration = storeConfiguration;
        this.acceptLanguage = acceptLanguage;
    }
}
