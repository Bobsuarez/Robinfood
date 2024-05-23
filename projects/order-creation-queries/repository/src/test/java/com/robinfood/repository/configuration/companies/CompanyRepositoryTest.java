package com.robinfood.repository.configuration.companies;

import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.ConfigurationBcAPI;
import com.robinfood.repository.mocks.ConfigCompaniesDTOMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyRepositoryTest {

    @Mock
    private ConfigurationBcAPI configurationBcAPI;

    @Mock
    private Call<APIResponseEntity<CompaniesDTO>> responseConfigCompaniesDTOCall;

    @InjectMocks
    private CompanyRepository companyRepository;

    private final String token = "token";

    @Test
    void test_GetConfigCompanies_Should_OK_When_DataIsCorrect() throws Exception {

        when(configurationBcAPI.getCompanies(
                anyString(),
                anyInt()
        )).thenReturn(responseConfigCompaniesDTOCall);

        when(responseConfigCompaniesDTOCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        ConfigCompaniesDTOMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        companyRepository.getActive(token);

        verify(configurationBcAPI).getCompanies(anyString(), anyInt());
    }


    @Test
    void test_GetConfigCompanies_Should_InternalServerError_When_WrongAnswerOfConfigurationBC() throws Exception {

        when(configurationBcAPI.getCompanies(anyString(), anyInt())).thenReturn(responseConfigCompaniesDTOCall);

        when(responseConfigCompaniesDTOCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        List.of(),
                        "CO",
                        "",
                        "Order filter not be returned"
                ))
                ))
        );

        companyRepository.getActive(token);

        verify(configurationBcAPI).getCompanies(anyString(), anyInt());
    }
}
