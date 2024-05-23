package com.robinfood.configurations.utils;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.constants.ConfigurationsBCConstants;
import com.robinfood.configurations.dto.v1.SalePointDTO;
import com.robinfood.configurations.dto.v1.models.CityDTO;
import com.robinfood.configurations.dto.v1.models.CompanyDTO;
import com.robinfood.configurations.dto.v1.models.HeadquarterDTO;
import com.robinfood.configurations.dto.v1.models.HoldingDTO;
import com.robinfood.configurations.dto.v1.models.PosDTO;
import com.robinfood.configurations.dto.v1.models.ResolutionDTO;
import com.robinfood.configurations.dto.v1.models.StoreDTO;

import java.util.List;
import java.util.stream.Collectors;

import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.models.Store;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public final class SalePointUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    private SalePointUtil() {
    }

    @BasicLog
    public static SalePointDTO buildSalePointDTO(Pos pos, Long brandId, String logoBaseUrl,
                                                 List<CompanyBrand> companyBrands) {
        log.info("Generating mappedSalePoint for SalePoint with POS {} and brand id {}",
            JsonUtils.convertToJson(pos), brandId);
        String logoUrl = logoBaseUrl + getLogoPath(pos.getStore(), companyBrands, brandId);

        return SalePointDTO.builder()
            .pos(buildPosDTO(pos))
            .store(buildStoreDTO(pos.getStore()))
            .company(buildCompanyDTO(pos.getStore().getCompany()))
            .logo(logoUrl)
            .build();
    }

    @BasicLog
    public static PosDTO buildPosDTO(Pos pos) {
        log.info("Generating mappedPos for Pos {}", JsonUtils.convertToJson(pos));
        return PosDTO.builder()
            .name(pos.getName())
            .code(pos.getCode())
            .resolutions(getPosResolutions(pos.getResolutionList()))
            .build();
    }

    @BasicLog
    public static StoreDTO buildStoreDTO(Store store) {
        log.info("Generating mappedStore for Store {}", JsonUtils.convertToJson(store));
        return StoreDTO.builder()
            .name(store.getName())
            .address(store.getAddress())
            .city(modelMapper.map(store.getCity(), CityDTO.class))
            .build();
    }

    @BasicLog
    public static CompanyDTO buildCompanyDTO(Company company) {
        log.info("Generating mappedCompany for Company {}", JsonUtils.convertToJson(company));
        return CompanyDTO.builder()
            .name(company.getName())
            .holding(HoldingDTO.builder().identification(company.getHolding().getIdentification()).build())
            .headquarter(modelMapper.map(company.getHeadquarter(), HeadquarterDTO.class))
            .build();

    }

    @BasicLog
    public static List<ResolutionDTO> getPosResolutions(List<Resolution> resolutionList) {
        log.info("Generating mapped Resolutions for Resolutions {}", JsonUtils.convertToJson(resolutionList));
        return resolutionList.stream()
            .filter(resolution -> resolution.getDeletedAt() == null)
            .map(resolution -> modelMapper.map(resolution, ResolutionDTO.class))
            .collect(Collectors.toList());
    }

    @BasicLog
    public static List<ResolutionDTO> getPosResolutionsActive(List<Resolution> resolutionList) {
        log.info("Generating mapped Resolutions for Resolutions {}", JsonUtils.convertToJson(resolutionList));
        return resolutionList.stream()
                .filter(resolution -> resolution.getDeletedAt() == null
                        && resolution.getStatus() == Integer.valueOf(ConfigurationsBCConstants.STATUS_ACTIVE))
                .map(SalePointUtil::mapToResolutionDTO)
                .collect(Collectors.toList());
    }

    @BasicLog
    public static String getLogoPath(Store store, List<CompanyBrand> companyBrands, Long brandId) {
        log.info("Validating logo for store {} and brand {}", JsonUtils.convertToJson(store), brandId);
        if (brandId != null) {
            return getLogoFromBrandCompanyCountry(store, companyBrands, brandId);
        }

        return store.getCompany().getHolding().getLogo();
    }

    @BasicLog
    public static String getLogoFromBrandCompanyCountry(Store store, List<CompanyBrand> companyBrands, Long brandId) {
        return companyBrands.stream()
                .filter(x -> x.getCompany().getId() == store.getCompany().getId() &&
                        x.getBrands().getId() == brandId)
                .findFirst()
                .map(CompanyBrand::getLogo)
                .orElse(store.getCompany().getHolding().getLogo());
    }

    private static ResolutionDTO mapToResolutionDTO(Resolution resolution) {
        ResolutionDTO res = new ResolutionDTO();
        res.setId(resolution.getId());
        res.setInvoiceNumberResolutions(resolution.getInvoiceNumberResolutions());
        res.setEndDate(resolution.getEndDate());
        res.setStartDate(resolution.getStartDate());
        res.setStartingNumber(resolution.getStartingNumber());
        res.setFinalNumber(resolution.getFinalNumber());
        res.setPrefix(resolution.getPrefix());
        return res;
    }

}
