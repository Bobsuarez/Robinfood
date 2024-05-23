package com.robinfood.customersbc.thirdparties.mappers.converters;

import lombok.Getter;
import org.apache.commons.text.WordUtils;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

public final class CamelCaseConverter {

    @Getter
    private static final Converter<String, String> instance;

    static {
        instance = new AbstractConverter<>() {
            protected String convert(String source) {
                return toCamelCase(source);
            }
        };
    }

    private CamelCaseConverter() {}

    private static String toCamelCase(String string) {
        String sanitizedString = Optional.ofNullable(string).orElse(EMPTY)
            .replaceAll("\\s+", SPACE).trim();

        return WordUtils.capitalizeFully(sanitizedString);
    }
}
