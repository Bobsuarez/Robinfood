package com.robinfood.customersbc.thirdparties.mappers.mappings;

import com.robinfood.customersbc.thirdparties.mappers.converters.CamelCaseConverter;
import com.robinfood.customersbc.thirdparties.domains.ThirdPartyDomain;
import com.robinfood.customersbc.thirdparties.dtos.ThirdPartyDTO;
import lombok.Getter;
import org.modelmapper.PropertyMap;

public final class ThirdPartyMapping {
    @Getter
    private static final PropertyMap<ThirdPartyDomain, ThirdPartyDTO> instance;

    static {
        instance = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(CamelCaseConverter.getInstance()).map(source.getFirstName()).setFirstName(null);
                using(CamelCaseConverter.getInstance()).map(source.getLastName()).setLastName(null);
            }
        };
    }

    private ThirdPartyMapping() {}

}
