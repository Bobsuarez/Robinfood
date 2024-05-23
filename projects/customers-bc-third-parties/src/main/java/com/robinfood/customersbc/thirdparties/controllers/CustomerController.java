package com.robinfood.customersbc.thirdparties.controllers;

import com.robinfood.customersbc.thirdparties.annotations.BasicLog;
import com.robinfood.customersbc.thirdparties.controllers.docs.CustomerDocs;
import com.robinfood.customersbc.thirdparties.domains.CreateCustomerDomain;
import com.robinfood.customersbc.thirdparties.domains.CustomerDomain;
import com.robinfood.customersbc.thirdparties.dtos.CreateCustomerDTO;
import com.robinfood.customersbc.thirdparties.dtos.CustomerDTO;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.enums.ResponseCode;
import com.robinfood.customersbc.thirdparties.services.CustomerService;
import com.robinfood.customersbc.thirdparties.mappers.utils.ModelMapperUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.robinfood.customersbc.thirdparties.utils.ResponseUtils.getResponseDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
    path = "/api/v1/customers",
    produces = APPLICATION_JSON_VALUE
)
public class CustomerController implements CustomerDocs {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @BasicLog
    @PostMapping(
        path = "/thirdparties",
        consumes = APPLICATION_JSON_VALUE
    )
    @Override
    public Mono<ResponseEntity<ResponseDTO<CustomerDTO>>> createCustomer(
        @Valid @RequestBody CreateCustomerDTO createCustomerDTO
    ) {
        return customerService.createCustomer(
            ModelMapperUtils.getInstance().map(createCustomerDTO, CreateCustomerDomain.class))
            .map((CustomerDomain customerDomain) -> ResponseEntity.ok(
                getResponseDTO(
                    ModelMapperUtils.getInstance().map(customerDomain, CustomerDTO.class),
                    ResponseCode.CREATED
                )
            )
        );
    }
}
