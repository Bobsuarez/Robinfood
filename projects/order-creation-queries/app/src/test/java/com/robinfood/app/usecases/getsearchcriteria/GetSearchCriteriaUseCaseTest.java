package com.robinfood.app.usecases.getsearchcriteria;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetSearchCriteriaUseCaseTest {

    @InjectMocks
    private GetSearchCriteriaUseCase getSearchCriteriaUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {
        getSearchCriteriaUseCase.invoke();
    }
}