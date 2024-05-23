package com.robinfood.customersbc.thirdparties.controllers;

import com.robinfood.customersbc.thirdparties.components.JwtTokenComponent;
import com.robinfood.customersbc.thirdparties.domains.ThirdPartyDomain;
import com.robinfood.customersbc.thirdparties.exceptions.EntityNotFoundException;
import com.robinfood.customersbc.thirdparties.services.ThirdPartyService;
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
import java.util.List;
import java.util.Map;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.NOT_FOUND_TYPE;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "300000")
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
class ThirdPartyControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    @MockBean
    private ThirdPartyService thirdPartyService;

    private final String endpointTest = "/api/v1/customers";

    @Test
    void test_GetThirdParties_ShouldBe_Ok_When_DataIsValid() {
        String url = endpointTest.concat("/{externalId}/thirdparties");

        when(thirdPartyService.getThirdPartiesByExternalId(anyLong()))
            .thenReturn(Mono.just(List.of(ThirdPartyDomain.builder().build())));

        webTestClient
            .get()
            .uri(url, 1)
            .accept(APPLICATION_JSON)
            .headers(headers -> headers.setBearerAuth(getToken()))
            .exchange()
            .expectStatus()
            .is2xxSuccessful();

        verify(thirdPartyService, times(1)).getThirdPartiesByExternalId(anyLong());
    }

    @Test
    void test_GetThirdParties_ShouldBe_Unauthorized_When_JwtIsEmpty() {
        String url = endpointTest.concat("/{externalId}/thirdparties");

        webTestClient
            .get()
            .uri(url, 1)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isUnauthorized();

        verify(thirdPartyService, never()).getThirdPartiesByExternalId(anyLong());
    }

    @Test
    void test_GetThirdParties_ShouldBe_NotFound_When_ThirdPartyNotExist() {
        String url = endpointTest.concat("/{externalId}/thirdparties");

        when(thirdPartyService.getThirdPartiesByExternalId(anyLong()))
            .thenReturn(Mono.error(
                new EntityNotFoundException(
                    NOT_FOUND_TYPE, "Entity CustomerModel with external id 1 not found"
                )
            ));

        webTestClient
            .get()
            .uri(url, 1)
            .accept(APPLICATION_JSON)
            .headers(headers -> headers.setBearerAuth(getToken()))
            .exchange()
            .expectStatus()
            .isNotFound();

        verify(thirdPartyService, times(1)).getThirdPartiesByExternalId(anyLong());
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