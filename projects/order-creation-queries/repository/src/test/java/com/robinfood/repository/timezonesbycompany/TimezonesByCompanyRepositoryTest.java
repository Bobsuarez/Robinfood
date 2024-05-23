package com.robinfood.repository.timezonesbycompany;

import com.robinfood.core.dtos.configuration.TimezonesByCompanyDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.ConfigurationBcAPI;
import com.robinfood.repository.configuration.timezonesbycompany.TimezonesByCompanyRepository;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimezonesByCompanyRepositoryTest {

    @Mock
    private ConfigurationBcAPI configurationBcAPI;

    @Mock
    private Call<APIResponseEntity<List<String>>> mockApiResponseEntityCall;

    @InjectMocks
    private TimezonesByCompanyRepository timezonesByCompanyRepository;

    private final String token = "token";
    private final Long companyId = 1L;

    @Test
    void test_GetTimezonesByCompanyStores_Successfully() throws IOException {

        final List<String> timezones = List.of("America/Bogota");

        when(configurationBcAPI.getTimezonesByCompanyId(
                token,
                companyId
        )).thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute()).thenReturn(Response.success(new APIResponseEntity<>(
                HttpStatus.SC_OK,
                timezones,
                "locale",
                "message",
                "code"
        )));

        final Result<TimezonesByCompanyDTO> response = timezonesByCompanyRepository.getTimezonesByCompanyStores(token, companyId);

        assertNotNull(response);
    }

    @Test
    void test_GetTimezonesByCompanyStores_When_Error() throws IOException {

        when(configurationBcAPI.getTimezonesByCompanyId(
                token,
                companyId
        )).thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute()).thenReturn(Response.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(
                        new APIResponseEntity<List<String>>(
                                HttpStatus.SC_INTERNAL_SERVER_ERROR,
                                List.of(),
                                "locale",
                                "Internal Server Error",
                                null
                        )

                )
        )));

        final Result<TimezonesByCompanyDTO> response = timezonesByCompanyRepository.getTimezonesByCompanyStores(token, companyId);

        assertTrue(response instanceof Result.Error);
    }
}
