package com.robinfood.customersbc.thirdparties.controllers;

import com.robinfood.customersbc.thirdparties.components.JwtTokenComponent;
import com.robinfood.customersbc.thirdparties.domains.CreateCustomerDomain;
import com.robinfood.customersbc.thirdparties.domains.CustomerDomain;
import com.robinfood.customersbc.thirdparties.dtos.CreateCustomerDTO;
import com.robinfood.customersbc.thirdparties.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "300000")
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
class CustomerControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    @MockBean
    private CustomerService customerService;

    private final String endpointTest = "/api/v1/customers";

    @Test
    void test_CreateCustomer_ShouldBe_Ok_When_DataIsValid() {
        String url = endpointTest.concat("/thirdparties");

        when(customerService.createCustomer(any(CreateCustomerDomain.class)))
            .thenReturn(Mono.just(CustomerDomain.builder().build()));

        webTestClient
            .post()
            .uri(url)
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .headers(headers -> headers.setBearerAuth(getToken()))
            .bodyValue(CreateCustomerDTO.builder().build())
            .exchange()
            .expectStatus()
            .is2xxSuccessful();

        verify(customerService, times(1)).createCustomer(any(CreateCustomerDomain.class));
    }

    @Test
    void test_CreateCustomer_ShouldBe_Unauthorized_When_JwtIsEmpty() {
        String url = endpointTest.concat("/thirdparties");

        webTestClient
            .post()
            .uri(url, 1)
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isUnauthorized();

        verify(customerService, never()).createCustomer(any(CreateCustomerDomain.class));
    }

    private String getToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("aud", "public");
        claims.put("iss", "robinfood-app");
        claims.put("jti", "8237498239472394792374987");
        claims.put("per", Arrays.asList("menu_read", "order_create"));
        return jwtTokenComponent.doGenerateToken(claims, "12345");
    }
}