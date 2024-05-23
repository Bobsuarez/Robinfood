package com.robinfood.app.usecases.getactivecompanies;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.configuration.CompanyDTO;
import com.robinfood.core.dtos.configuration.TimezonesByCompanyDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.companies.ICompanyRepository;
import com.robinfood.repository.configuration.timezonesbycompany.ITimezonesByCompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetActiveCompaniesUseCase implements IGetActiveCompaniesUseCase {

    private final ICompanyRepository companyRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final ITimezonesByCompanyRepository timezonesByCompanyRepository;

    public GetActiveCompaniesUseCase(
            ICompanyRepository companyRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            ITimezonesByCompanyRepository timezonesByCompanyRepository
    ) {
        this.companyRepository = companyRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.timezonesByCompanyRepository = timezonesByCompanyRepository;
    }

    @Override
    public Result<CompaniesDTO> invoke() {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        final Result<CompaniesDTO> getCompanies = companyRepository.getActive(token.getAccessToken());

        final CompaniesDTO companiesDTO = ((Result.Success<CompaniesDTO>) getCompanies).getData();

        addTimezonesByCompany(companiesDTO, token.getAccessToken());

        return new Result.Success(companiesDTO);
    }

    private void addTimezonesByCompany(
            CompaniesDTO companiesDTO,
            String token
    ) {
        companiesDTO.getCompanies().forEach((CompanyDTO companyDTO) -> {

            final TimezonesByCompanyDTO timezonesByCompanyDTO = getTimezonesByCompany(token, companyDTO.getId());

            if (timezonesByCompanyDTO.getTimezones().isEmpty()) {
                return;
            }

            log.info("Timezones: {} company: {}, {}", timezonesByCompanyDTO, companyDTO.getId(), companyDTO.getName());

            companyDTO.getCountry().setTimezone(timezonesByCompanyDTO.getTimezones().get(DEFAULT_INTEGER_VALUE));
        });
    }

    private TimezonesByCompanyDTO getTimezonesByCompany(String token, Long companyId) {

        final Result<TimezonesByCompanyDTO> timezonesByCompanyDTOResult = timezonesByCompanyRepository
                .getTimezonesByCompanyStores(token, companyId);

        return ((Result.Success<TimezonesByCompanyDTO>)
                timezonesByCompanyDTOResult).getData();
    }
}
