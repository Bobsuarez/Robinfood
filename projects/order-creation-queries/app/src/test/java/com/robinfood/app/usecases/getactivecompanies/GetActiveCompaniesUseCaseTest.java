package com.robinfood.app.usecases.getactivecompanies;

import com.robinfood.app.mocks.configurations.CompaniesDTOMock;
import com.robinfood.app.mocks.configurations.TimezonesByCompanyDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.configuration.TimezonesByCompanyDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.companies.ICompanyRepository;
import com.robinfood.repository.configuration.timezonesbycompany.ITimezonesByCompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetActiveCompaniesUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private ITimezonesByCompanyRepository timezonesByCompanyRepository;

    @Mock
    private ICompanyRepository companyRepository;

    @InjectMocks
    private GetActiveCompaniesUseCase getCompaniesUseCase;

    @Test
    void test_Invoke_Should_Return_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(companyRepository.getActive(anyString()))
                .thenReturn(new Result.Success<>(CompaniesDTOMock.getDataDefault()));

        when(timezonesByCompanyRepository.getTimezonesByCompanyStores(anyString(), anyLong()))
                .thenReturn(new Result.Success<>(TimezonesByCompanyDTOMock.getDataDefault()));

        getCompaniesUseCase.invoke();
    }

    @Test
    void test_Invoke_Should_Return_When_Timezones_Is_Null() {

        final String token = "token";

        final TimezonesByCompanyDTO timezonesEmpty = TimezonesByCompanyDTOMock.getDataDefault();
        timezonesEmpty.setTimezones(List.of());

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(companyRepository.getActive(anyString()))
                .thenReturn(new Result.Success<>(CompaniesDTOMock.getDataDefault()));

        when(timezonesByCompanyRepository.getTimezonesByCompanyStores(anyString(), anyLong()))
                .thenReturn(new Result.Success<>(timezonesEmpty));

        getCompaniesUseCase.invoke();
    }
}
