package com.robinfood.orderorlocalserver.utilities;

import jakarta.validation.constraints.NotNull;
import java.util.regex.Pattern;
import static com.robinfood.orderorlocalserver.constants.GlobalConstants.DEFAULT_STRING_VALUE;

public final class ReplaceCharactersUtil {

    private static final Pattern myRegexStringNull = Pattern.compile("(null|NULL)");
    private static final Pattern myRegexStringEscape = Pattern.compile("\\\\n|\\\\t|\\\\r");
    private static final Pattern myRegexlineBreaks = Pattern.compile("\n|\t|\r|\n\r|\r\n");

    private ReplaceCharactersUtil() {
        // this constructor is empty because it is a configuration class
    }

    public static String lineBreaksSpecCharacterStringNull(@NotNull String text) {

        return myRegexlineBreaks.matcher(
                        myRegexStringEscape.matcher(
                                        myRegexStringNull.matcher(text)
                                                .replaceAll(DEFAULT_STRING_VALUE)
                                )
                                .replaceAll(DEFAULT_STRING_VALUE)
                )
                .replaceAll(DEFAULT_STRING_VALUE);
    }

}
