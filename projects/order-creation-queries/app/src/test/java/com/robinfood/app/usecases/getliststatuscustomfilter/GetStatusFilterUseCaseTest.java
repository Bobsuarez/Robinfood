package com.robinfood.app.usecases.getliststatuscustomfilter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetStatusFilterUseCaseTest {

    @InjectMocks
    GetStatusCustomFilterUseCase getStatusCustomFilterUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {

        getStatusCustomFilterUseCase.invoke();
    }
}
