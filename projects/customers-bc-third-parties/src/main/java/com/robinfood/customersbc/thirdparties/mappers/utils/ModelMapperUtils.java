package com.robinfood.customersbc.thirdparties.mappers.utils;

import com.robinfood.customersbc.thirdparties.mappers.mappings.CustomerMapping;
import com.robinfood.customersbc.thirdparties.mappers.mappings.ThirdPartyMapping;
import org.modelmapper.ModelMapper;

public final class ModelMapperUtils {
    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(CustomerMapping.getInstance());
        modelMapper.addMappings(ThirdPartyMapping.getInstance());
    }

    private ModelMapperUtils() {}

    public static ModelMapper getInstance() {
        return modelMapper;
    }
}
