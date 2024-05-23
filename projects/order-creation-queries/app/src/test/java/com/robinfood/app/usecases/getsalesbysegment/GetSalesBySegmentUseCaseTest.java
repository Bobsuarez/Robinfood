package com.robinfood.app.usecases.getsalesbysegment;

import com.robinfood.app.mocks.configurations.CompanyDTOMock;
import com.robinfood.app.usecases.getbrands.GetBrandsUseCase;
import com.robinfood.app.usecases.getchannels.GetConfigChannelsUseCase;
import com.robinfood.app.usecases.getactivecompanies.GetActiveCompaniesUseCase;
import com.robinfood.app.usecases.getlistcompanysegment.GetListCompanyBySegmentUseCase;
import com.robinfood.app.usecases.getlistpaymentmethods.IGetListPaymentMethodsUseCase;
import com.robinfood.app.usecases.getstores.GetStoresUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.GetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.CompanyDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.SaleBySegmentResponseDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.report.salessegment.SalesSegmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetSalesBySegmentUseCaseTest {

    @Mock
    private SalesSegmentRepository salesSegmentRepository;

    @Mock
    private GetActiveCompaniesUseCase getConfigCompaniesUseCase;

    @Mock
    private GetBrandsUseCase getBrandsUseCase;

    @Mock
    private GetConfigChannelsUseCase getConfigChannelsUseCase;

    @Mock
    private GetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private GetStoresUseCase getStoresUseCase;

    @Mock
    private GetListCompanyBySegmentUseCase getListCompanySegment;

    @Mock
    private IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase;

    @InjectMocks
    private GetSalesBySegmentUseCase getSalesBySegmentUseCase;

    @BeforeEach
    void setUp() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(salesSegmentRepository.getSalesSegment(
                anyList(),
                anyList(),
                anyList(),
                any(LocalDateTime.class),
                anyList(),
                anyList(),
                anyList(),
                anyString())).thenReturn(new Result.Success<>(GetSaleBySegmentDTO.builder().build()));
        when(getConfigCompaniesUseCase.invoke()).thenReturn(new Result.Success<>(
                CompaniesDTO.builder()
                        .companies(List.of(CompanyDTOMock.getDataDefault()))
                        .build())
        );
        when(getConfigChannelsUseCase.invoke()).thenReturn(new Result.Success<>(ChannelsDTO.builder().build()));
        when(getBrandsUseCase.invoke()).thenReturn(new Result.Success<>(List.of(BrandDTO.builder().build())));
        when(getListCompanySegment.invoke(any())).thenReturn(List.of(CompanyDTOResponse.builder().build()));
        when(getListPaymentMethodsUseCase.invoke())
                .thenReturn(new Result.Success<>(List.of(PaymentMethodsFilterDTO.builder().build())));

    }

    @Test
    void test_invoke_Should_ReturnDataSegment_When_InvokeTheUseCase() {

        when(getStoresUseCase.invoke()).thenReturn(new Result.Success<>(List.of(StoreWithIdAndNameDTO.builder().build())));

        SaleBySegmentResponseDTO responseDTO = getSalesBySegmentUseCase.invoke(
                List.of(1L, 2L), List.of(1L, 2L), LocalDateTime.now(), List.of(1L, 2L), List.of(1L, 2L)
        );

        Assertions.assertNotNull(responseDTO, "Response SaleSegmentResponseDTO");
    }

    @Test
    void test_invoke_Should_ReturnErrorConnection_When_NotFound() {

        when(getStoresUseCase.invoke()).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(Exception.class, () -> {
            getSalesBySegmentUseCase.invoke(
                    List.of(1L, 2L), List.of(1L, 2L), LocalDateTime.now(), List.of(1L, 2L), List.of(1L, 2L)
            );
        });

        verify(getStoresUseCase).invoke();
    }
}
