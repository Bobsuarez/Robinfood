package com.robinfood.app.usecases.getsalesbysegment;

import com.robinfood.app.usecases.getbrands.IGetBrandsUseCase;
import com.robinfood.app.usecases.getchannels.IGetConfigChannelsUseCase;
import com.robinfood.app.usecases.getactivecompanies.IGetActiveCompaniesUseCase;
import com.robinfood.app.usecases.getlistcompanysegment.IGetListCompanyBySegmentUseCase;
import com.robinfood.app.usecases.getlistpaymentmethods.IGetListPaymentMethodsUseCase;
import com.robinfood.app.usecases.getstores.GetStoresUseCase;
import com.robinfood.app.usecases.getstores.IGetStoresUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.SalesBuildAnswerDTO;
import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.configuration.CompanyDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.SaleBySegmentResponseDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mappers.report.salesegment.SaleSegmentResponseMappers;
import com.robinfood.core.mappers.report.salesegment.SalesBuildAnswerDTOMappers;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.report.salessegment.ISalesSegmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class GetSalesBySegmentUseCase implements IGetSalesBySegmentUseCase {

    private final ISalesSegmentRepository salesSegmentRepository;

    private final IGetActiveCompaniesUseCase getCompaniesUseCase;

    private final IGetBrandsUseCase getBrandsUseCase;

    private final IGetConfigChannelsUseCase getConfigChannelsUseCase;

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IGetStoresUseCase getStoresUseCase;

    private final IGetListCompanyBySegmentUseCase getListCompanySegment;

    private final IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase;

    public GetSalesBySegmentUseCase(
            ISalesSegmentRepository salesSegmentRepository,
            IGetActiveCompaniesUseCase getCompaniesUseCase,
            IGetBrandsUseCase getBrandsUseCase,
            IGetConfigChannelsUseCase getConfigChannelsUseCase,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            GetStoresUseCase getStoresUseCase,
            IGetListCompanyBySegmentUseCase getListCompanySegment,
            IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase
    ) {
        this.salesSegmentRepository = salesSegmentRepository;
        this.getCompaniesUseCase = getCompaniesUseCase;
        this.getBrandsUseCase = getBrandsUseCase;
        this.getConfigChannelsUseCase = getConfigChannelsUseCase;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.getStoresUseCase = getStoresUseCase;
        this.getListCompanySegment = getListCompanySegment;
        this.getListPaymentMethodsUseCase = getListPaymentMethodsUseCase;
    }

    @Override
    public SaleBySegmentResponseDTO invoke(
            List<Long> brands,
            List<Long> channels,
            LocalDateTime dateTimeCurrent,
            List<Long> paymentMethods,
            List<Long> stores
    ) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        final Result<CompaniesDTO> resultCompanies = getCompaniesUseCase.invoke();

        final CompaniesDTO getCompanies = ((Result.Success<CompaniesDTO>) resultCompanies).getData();

        final List<String> timezones = getCompanies.getCompanies()
                .stream()
                .map(companyDTO -> companyDTO.getCountry().getTimezone())
                .collect(Collectors.toList());

        final List<Long> companiesIds = getCompaniesIds(getCompanies);

        findErrorInQueriedAnswer(resultCompanies);

        final Result<GetSaleBySegmentDTO> saleBySegmentDTOResult = salesSegmentRepository.getSalesSegment(
                brands,
                companiesIds,
                channels,
                dateTimeCurrent,
                paymentMethods,
                stores,
                timezones,
                token.getAccessToken()
        );

        findErrorInQueriedAnswer(saleBySegmentDTOResult);

        final Result<ChannelsDTO> resultChannels = getConfigChannelsUseCase.invoke();

        findErrorInQueriedAnswer(resultChannels);

        final Result<List<BrandDTO>> resultBrands = getBrandsUseCase.invoke();

        findErrorInQueriedAnswer(resultBrands);

        final Result<List<StoreWithIdAndNameDTO>> resultStores = getStoresUseCase.invoke();

        findErrorInQueriedAnswer(resultStores);

        final Result<List<PaymentMethodsFilterDTO>> resultPaymentMethods = getListPaymentMethodsUseCase.invoke();

        findErrorInQueriedAnswer(resultPaymentMethods);

        final SalesBuildAnswerDTO salesBuildAnswerDTO = SalesBuildAnswerDTOMappers.saleSegmentToResponse(
                ((Result.Success<List<BrandDTO>>) resultBrands).getData(),
                ((Result.Success<CompaniesDTO>) resultCompanies).getData().getCompanies(),
                ((Result.Success<ChannelsDTO>) resultChannels).getData().getContent(),
                ((Result.Success<List<PaymentMethodsFilterDTO>>) resultPaymentMethods).getData(),
                ((Result.Success<GetSaleBySegmentDTO>) saleBySegmentDTOResult).getData(),
                ((Result.Success<List<StoreWithIdAndNameDTO>>) resultStores).getData());

        return SaleSegmentResponseMappers.saleSegmentToResponse(getListCompanySegment.invoke(salesBuildAnswerDTO));
    }

    private List<Long> getCompaniesIds(CompaniesDTO companiesDTO) {
        return companiesDTO.getCompanies()
                .stream()
                .map(CompanyDTO::getId)
                .collect(Collectors.toList());
    }

    private <T> void findErrorInQueriedAnswer(T objectEntity) {

        if (objectEntity instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) objectEntity).getHttpStatus(),
                    ((Result.Error) objectEntity).getException().getMessage()
            );
        }
    }
}
