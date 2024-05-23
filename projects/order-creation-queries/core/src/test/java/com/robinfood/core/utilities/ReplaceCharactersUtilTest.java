package com.robinfood.core.utilities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReplaceCharactersUtilTest {

    @Test
    void test_lineBreaksSpecCharacterStringNull() {

        final String text1 = "null";
        final String text2 = "null\\nnull";
        final String text3 = "producto sin cebolla\nsin arroz  ";
        Assertions.assertThat(ReplaceCharactersUtil
                        .lineBreaksSpecCharacterStringNull(text1))
                .isEmpty();
        Assertions.assertThat(ReplaceCharactersUtil
                        .lineBreaksSpecCharacterStringNull(text2))
                .isEmpty();
        Assertions.assertThat(ReplaceCharactersUtil
                        .lineBreaksSpecCharacterStringNull(text3))
                .isEqualTo("producto sin cebollasin arroz  ");
    }

    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
        Constructor<ReplaceCharactersUtil> c = ReplaceCharactersUtil.class.getDeclaredConstructor();
        c.setAccessible(true);
        ReplaceCharactersUtil u = c.newInstance();
    }
}
