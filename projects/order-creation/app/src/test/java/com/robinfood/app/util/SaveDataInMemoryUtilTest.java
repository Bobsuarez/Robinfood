package com.robinfood.app.util;

import com.robinfood.core.util.SaveDataInMemoryUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveDataInMemoryUtilTest {

    @Test
    void test_Get_Value_Success() {

        final String keyCommand = "keyCommand";
        final String command = "CommandTransaction";

        SaveDataInMemoryUtil.setData(keyCommand, command);

        final Object response = SaveDataInMemoryUtil.getValue(keyCommand);

        Assertions.assertNotNull(response);
    }
}
