package com.robinfood.app.usecases.getlistcompanysegment;


import com.robinfood.app.usecases.getlistbrandsegment.IGetBrandBySegmentUseCase;
import com.robinfood.app.usecases.getlistchannelsegment.IGetChannelBySegmentUseCase;
import com.robinfood.app.usecases.getlistpaymentsegment.IGetPaymentBySegmentUseCase;
import com.robinfood.app.usecases.getliststoresegment.IGetStoreBySegmentUseCase;
import com.robinfood.core.dtos.SalesBuildAnswerDTO;
import com.robinfood.core.dtos.configuration.CompanyDTO;
import com.robinfood.core.dtos.report.salebysegment.CompanyBySegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.BrandsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.ChannelsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.CompanyDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.PaymentMethodsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.StoresDTOResponse;
import com.robinfood.core.mappers.report.salesegment.CompanySegmentToResponseMappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetListCompanyBySegmentUseCase implements IGetListCompanyBySegmentUseCase {

    private final IGetBrandBySegmentUseCase getListBrandSegment;

    private final IGetChannelBySegmentUseCase getListChannelSegment;

    private final IGetStoreBySegmentUseCase getListStoreSegment;

    private final IGetPaymentBySegmentUseCase getListPaymentSegment;

    public GetListCompanyBySegmentUseCase(
            IGetBrandBySegmentUseCase getListBrandSegment,
            IGetChannelBySegmentUseCase getListChannelSegment,
            IGetStoreBySegmentUseCase getListStoreSegment,
            IGetPaymentBySegmentUseCase getListPaymentSegment
    ) {
        this.getListBrandSegment = getListBrandSegment;
        this.getListChannelSegment = getListChannelSegment;
        this.getListStoreSegment = getListStoreSegment;
        this.getListPaymentSegment = getListPaymentSegment;
    }

    @Override
    public List<CompanyDTOResponse> invoke(SalesBuildAnswerDTO salesBuildAnswerDTO) {

        return salesBuildAnswerDTO.getSaleSegmentDTO().getCompanies()
                .stream()
                .map(companyBySegmentDTO -> builderToCompany(companyBySegmentDTO, salesBuildAnswerDTO))
                .collect(Collectors.toList());
    }

    private CompanyDTOResponse builderToCompany(
            CompanyBySegmentDTO companyBySegmentDTO,
            SalesBuildAnswerDTO salesBuildAnswerDTO
    ) {
        final CompanyDTO companyInformation = getCompanyInformation(
                companyBySegmentDTO.getId(),
                salesBuildAnswerDTO.getCompaniesDTOList());

        final BrandsDTOResponse resultBrands = getListBrandSegment.invoke(
                companyInformation.getCurrency_type(),
                companyBySegmentDTO.getBrands(),
                salesBuildAnswerDTO.getBrandDTOList());

        final ChannelsDTOResponse resultChannels = getListChannelSegment.invoke(
                companyInformation.getCurrency_type(),
                companyBySegmentDTO.getChannels(),
                salesBuildAnswerDTO.getChannelDTOList());

        final StoresDTOResponse resultStores = getListStoreSegment.invoke(
                companyInformation.getCurrency_type(),
                companyBySegmentDTO.getStores(),
                salesBuildAnswerDTO.getStoresDTOList());

        final PaymentMethodsDTOResponse resultPaymentMethods = getListPaymentSegment.invoke(
                companyInformation.getCurrency_type(),
                companyBySegmentDTO.getPaymentMethods(),
                 salesBuildAnswerDTO.getPaymentDTOList());

        return CompanySegmentToResponseMappers.toCompanyResponse(
                companyInformation,
                resultBrands,
                resultChannels,
                resultPaymentMethods,
                resultStores);
    }

    private CompanyDTO getCompanyInformation(Long companyId, List<CompanyDTO> companies) {

        return Optional.ofNullable(companies)
                .orElse(Collections.emptyList())
                .stream()
                .filter(companiesDTO -> companiesDTO.getId().equals(companyId))
                .findFirst()
                .orElseThrow();
    }
}
