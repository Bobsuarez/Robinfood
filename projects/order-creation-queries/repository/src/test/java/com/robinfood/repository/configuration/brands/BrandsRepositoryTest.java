package com.robinfood.repository.configuration.brands;

import com.robinfood.core.dtos.configuration.BrandsDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.ConfigurationBcAPI;
import com.robinfood.repository.mocks.BrandsDTOMock;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandsRepositoryTest {

    @Mock
    private ConfigurationBcAPI configurationBcAPI;

    @Mock
    private Call<APIResponseEntity<BrandsDTO>> responseEntityCall;

    @InjectMocks
    private BrandsRepository brandsRepository;

    private final String token = "token";

    @Test
    void test_GetBrands_Should_OK_When_DataIsCorrect() throws Exception {

        when(configurationBcAPI.getBrands(anyString())).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        BrandsDTO.builder()
                                .content(List.of(BrandsDTOMock.getDataDefault()))
                                .build(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        brandsRepository.getAll(token);

        verify(configurationBcAPI).getBrands(anyString());
    }

    @Test
    void test_GetBrands_Should_InternalServerError_When_WrongAnswerOfOrderBC() throws Exception {

        when(configurationBcAPI.getBrands(anyString())).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
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

        brandsRepository.getAll(token);

        verify(configurationBcAPI).getBrands(anyString());
    }
}
