package com.robinfood.configurations.controllers.v1;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.CountryControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CountryDTO;
import com.robinfood.configurations.models.Country;
import com.robinfood.configurations.services.CountryService;
import com.robinfood.configurations.utils.CountryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;

@Slf4j
@RestController
@RequestMapping(value = "/v1/countries")
public class CountryController implements CountryControllerDocs {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    /**
     * Método que consulta el listado de países
     *
     * @return respuesta JSON con listado de países
     */
    @Override
    @BasicLog
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<List<CountryDTO>>> findAllCountries() {
        List<Country> countries = service.findAllCountries();
        List<CountryDTO> countriesDTO = CountryUtil.buildCountriesDTO(countries);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<CountryDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Countries retrieved successfully")
                .data(countriesDTO)
                .build());
    }
}
