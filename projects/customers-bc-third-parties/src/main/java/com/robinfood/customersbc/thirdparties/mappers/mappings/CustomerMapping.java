package com.robinfood.customersbc.thirdparties.mappers.mappings;

import com.robinfood.customersbc.thirdparties.mappers.converters.CamelCaseConverter;
import com.robinfood.customersbc.thirdparties.domains.CustomerDomain;
import com.robinfood.customersbc.thirdparties.dtos.CustomerDTO;
import lombok.Getter;
import org.modelmapper.PropertyMap;

public final class CustomerMapping {
    @Getter
    private static final PropertyMap<CustomerDomain, CustomerDTO> instance;

    static {
        instance = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(CamelCaseConverter.getInstance()).map(source.getFirstName()).setFirstName(null);
                using(CamelCaseConverter.getInstance()).map(source.getLastName()).setLastName(null);
            }
        };
    }

    private CustomerMapping() {}
}
