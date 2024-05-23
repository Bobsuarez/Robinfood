package com.robinfood.customersbc.thirdparties.controllers;

import com.robinfood.customersbc.thirdparties.annotations.BasicLog;
import com.robinfood.customersbc.thirdparties.controllers.docs.ThirdPartyDocs;
import com.robinfood.customersbc.thirdparties.domains.ThirdPartyDomain;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.dtos.ThirdPartyDTO;
import com.robinfood.customersbc.thirdparties.enums.ResponseCode;
import com.robinfood.customersbc.thirdparties.services.ThirdPartyService;
import com.robinfood.customersbc.thirdparties.mappers.utils.ModelMapperUtils;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.robinfood.customersbc.thirdparties.utils.ResponseUtils.getResponseDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
    path = "/api/v1/customers",
    produces = APPLICATION_JSON_VALUE
)
public class ThirdPartyController implements ThirdPartyDocs {
    private final ThirdPartyService thirdPartyService;

    public ThirdPartyController(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @BasicLog
    @GetMapping(
        path = "/{externalId}/thirdparties"
    )
    @Override
    public Mono<ResponseEntity<ResponseDTO<List<ThirdPartyDTO>>>> getThirdPartiesByExternalId(
        @PathVariable(name = "externalId") Long externalId
    ) {
        return thirdPartyService.getThirdPartiesByExternalId(externalId)
            .map((List<ThirdPartyDomain> thirdPartyDomainList) -> ResponseEntity.ok(
                getResponseDTO(
                    ModelMapperUtils.getInstance().map(
                        thirdPartyDomainList, new TypeToken<List<ThirdPartyDTO>>() {}.getType()),
                    ResponseCode.SUCCESS
                )
            ));
    }
}
