package com.robinfood.orderorlocalserver.utilities;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;
import static com.robinfood.orderorlocalserver.constants.GlobalConstants.DEFAULT_STRING_VALUE;

public final class SlugGeneratorUtil {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private SlugGeneratorUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String toSlug(String input) {

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll(DEFAULT_STRING_VALUE);
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
