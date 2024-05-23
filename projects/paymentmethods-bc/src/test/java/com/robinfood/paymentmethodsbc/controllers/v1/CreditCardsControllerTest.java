package com.robinfood.paymentmethodsbc.controllers.v1;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.paymentmethodsbc.Application;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
import com.robinfood.paymentmethodsbc.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = true)
@EnableJpaRepositories(basePackages = { "com.robinfood.paymentmethodsbc.*" })
@EntityScan(basePackages = { "com.robinfood.paymentmethodsbc.*" })
@ComponentScan(basePackages = { "com.robinfood.paymentmethodsbc.*" })
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
public class CreditCardsControllerTest {
    @Autowired
    private SSOTokenUtil ssoTokenUtil;

    @MockBean
    private CreditCardService creditCardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreditCardCreateShouldBeOk() throws Exception {
        String endpointTest =
            "/api/v1/backoffice/credit-cards/users/1?country_id=1&payment_method_id=1";
        when(creditCardService.findAllByUserAndCountryAndPaymentMethodId(anyLong(), anyLong(), anyLong()))
            .thenReturn(CreditCardSamples.getCreditCardList());

        this.mockMvc.perform(
                get(endpointTest)
                    .header("Authorization", "Bearer " + generateValidToken())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

    @Test
    public void testCreditCardCreateShouldBeErrorWithNoQueryParams()
        throws Exception {
        String endpointTest = "/api/v1/backoffice/credit-cards/users/1";

        this.mockMvc.perform(
                get(endpointTest)
                    .header("Authorization", "Bearer " + generateValidToken())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(422));
    }

    @Test
    public void testCreditCardCreateShouldBe201WhenSendAllParameters()
        throws Exception {
        String endpointTest = "/api/v1/backoffice/credit-cards";

        CreditCard creditCard = CreditCardSamples.getCreditCard(false);

        CreateCreditCardRequestDTO creditCardDTO = CreditCardSamples.getCreditCardRequestDTO();

        when(creditCardService.create(any(CreateCreditCardRequestDTO.class)))
            .thenReturn(creditCard);

        this.mockMvc.perform(
                post(endpointTest)
                    .header("Authorization", "Bearer " + generateValidToken())
                    .content(asJsonString(creditCardDTO))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(201));
    }

    @Test
    public void testCreditCardCreateShouldBeErrorWhenInvalidToken()
        throws Exception {
        String endpointTest = "/api/v1/backoffice/credit-cards";

        CreateCreditCardRequestDTO creditCardDTO = CreditCardSamples.getCreditCardRequestDTO();

        this.mockMvc.perform(
                post(endpointTest)
                    .header("Authorization", "Bearer " + generateInvalidToken())
                    .content(asJsonString(creditCardDTO))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().is4xxClientError())
            .andDo(print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(401)));
    }

    @Test
    public void testCreditCardDeleteShouldBe200WhenSendAllParameters()
        throws Exception {
        String endpointTest =
            "/api/v1/backoffice/credit-cards/users/1/credit-card/1";

        when(creditCardService.delete(anyLong(), anyLong())).thenReturn("Ok");

        this.mockMvc.perform(
                delete(endpointTest)
                    .header("Authorization", "Bearer " + generateValidToken())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }


    @Test
    public void testCreditCardUpdateShouldBeOk() throws Exception {
        String endpointTest =
        "/api/v1/backoffice/credit-cards/users/1/credit-card/1";

        UpdateCreditCardRequestDTO creditCardDTO = CreditCardSamples.getUpdateCreditCardRequestDTO();


        when(creditCardService.update(
            anyLong(), 
            anyLong(),
            any(UpdateCreditCardRequestDTO.class) 
        )).thenReturn(CreditCardSamples.getCreditCard(true));

        
        this.mockMvc.perform(
                put(endpointTest)
                .header("Authorization", "Bearer " + generateValidToken())
                .content(asJsonString(creditCardDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }


    @Test
    public void testCreditCardUpdateShouldBeErrorWhenInvalidToken() throws Exception {
        String endpointTest =
        "/api/v1/backoffice/credit-cards/users/1/credit-card/1";

        UpdateCreditCardRequestDTO creditCardDTO = CreditCardSamples.getUpdateCreditCardRequestDTO();


        when(creditCardService.update(
            anyLong(), 
            anyLong(),
            any(UpdateCreditCardRequestDTO.class) 
        )).thenReturn(CreditCardSamples.getCreditCard(true));

        
        this.mockMvc.perform(
                put(endpointTest)
                .header("Authorization", "Bearer " + generateInvalidToken())
                .content(asJsonString(creditCardDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().is4xxClientError())
            .andDo(print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(401)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateValidToken() {
        return ssoTokenUtil.exampleToken(true);
    }

    public String generateInvalidToken() {
        return ssoTokenUtil.exampleInvalidToken();
    }
}
