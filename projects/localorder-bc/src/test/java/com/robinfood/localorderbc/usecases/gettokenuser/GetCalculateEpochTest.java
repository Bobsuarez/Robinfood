package com.robinfood.localorderbc.usecases.gettokenuser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GetCalculateEpochTest {
    @InjectMocks
    GetCalculateEpoch getCalculateEpoch;

    private final Long FECHA_EPOCH = 1652194757L;

    @Test
    void test_When_minutesExpiration_Success(){

        Long testEpoch = getCalculateEpoch.minutesExpiration(FECHA_EPOCH);

        assertNotNull(testEpoch);

    }

}
