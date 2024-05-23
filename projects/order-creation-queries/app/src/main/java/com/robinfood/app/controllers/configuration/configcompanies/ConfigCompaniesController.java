package com.robinfood.app.controllers.configuration.configcompanies;

import com.robinfood.app.usecases.getactivecompanies.IGetActiveCompaniesUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.core.constants.APIConstants.COMPANIES_V1;
import static com.robinfood.core.constants.APIConstants.CONFIG_COMPANIES;

@RequestMapping(COMPANIES_V1)
@RestController
@Slf4j
public class ConfigCompaniesController implements IConfigCompaniesController {

    public final IGetActiveCompaniesUseCase getConfigCompaniesUseCase;

    public ConfigCompaniesController(IGetActiveCompaniesUseCase getConfigCompaniesUseCase) {
        this.getConfigCompaniesUseCase = getConfigCompaniesUseCase;
    }

    @Override
    @GetMapping(CONFIG_COMPANIES)
    public ResponseEntity<ApiResponseDTO<CompaniesDTO>> invoke() {

        log.info("Receiving request get companies");

        final Result<CompaniesDTO> configCompaniesDTOResult =
                getConfigCompaniesUseCase.invoke();

        ApiResponseDTO<CompaniesDTO> apiResponseDTO;

        HttpStatus httpStatus;

        if (configCompaniesDTOResult instanceof Result.Error) {
            httpStatus = ((Result.Error) configCompaniesDTOResult).getHttpStatus();
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) configCompaniesDTOResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {
            httpStatus = HttpStatus.OK;
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<CompaniesDTO>) configCompaniesDTOResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
