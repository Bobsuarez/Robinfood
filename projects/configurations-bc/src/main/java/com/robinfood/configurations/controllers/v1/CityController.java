package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.CityControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CityDTOResponse;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.services.CityService;
import com.robinfood.configurations.utils.CityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.LIST_TIME_ZONES;
import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/v1/countries")
public class CityController implements CityControllerDocs {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Método que consulta el listado de ciudades por país
     *
     * @return respuesta JSON con listado de ciudades
     */
    @Override
    @BasicLog
    @GetMapping(value = "/{countryId}/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<List<CityDTOResponse>>> findAllCitiesByCountry(Long countryId) {
        List<City> cities = cityService.findCitiesByCountry(countryId);
        List<CityDTOResponse> citiesDTO = CityUtil.buildCitiesDTO(cities);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<CityDTOResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Cities retrieved successfully")
                .data(citiesDTO)
                .build());
    }

    @BasicLog
    @Override
    @GetMapping(value = "/cities/time-zones", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(
        "hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + LIST_TIME_ZONES + "')")
    public ResponseEntity<ApiResponseDTO<List<String>>> listTimeTimeZonesByCompanyCountryId(
        @RequestParam(value = "company_country_id") Long companyCountryId) throws JsonProcessingException {

        log.info("Getting list time zones info with companyCountryId {}", companyCountryId);
        List<String> listTimeZone = cityService.listTimeZonesByCompanyCountryId(companyCountryId);
        log.info("List time zone retrieved successfully.");

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<String>>builder()
                .code(HttpStatus.OK.value())
                .message("List time zones retrieved successfully")
                .data(listTimeZone)
                .build());
    }
}
