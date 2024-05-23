package com.robinfood.app.util;

import com.robinfood.core.util.SlugGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SlugGeneratorUtilTest {

    @Test
    void Test_Slug_Generator_Util_Success() {

        String result =  SlugGeneratorUtil.toSlug("Calle 100");

        Assertions.assertEquals("calle-100", result);
    }

    @Test
    void Test_Slug_Generator_Util_Constructor() throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException
    {
        Constructor<SlugGeneratorUtil> slugGeneratorUtilConstructor = SlugGeneratorUtil.class.getDeclaredConstructor();
        slugGeneratorUtilConstructor.setAccessible(true);

        assertThrows(InvocationTargetException.class, slugGeneratorUtilConstructor::newInstance);
    }

}