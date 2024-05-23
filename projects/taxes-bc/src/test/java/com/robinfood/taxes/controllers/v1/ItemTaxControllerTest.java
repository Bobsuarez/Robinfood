package com.robinfood.taxes.controllers.v1;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.domain.CalculatedTax;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.facades.TaxableItemFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ItemTaxController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=taxes"
})
class ItemTaxControllerTest extends BaseTestController {

    @MockBean
    private TaxableItemFacade itemTaxFacade;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private final String modules = "taxes";

    private final String audience = "internal";

    @Test
    void test_ListItemTax_Should_Return200_When_ReceiveValidDTO()
        throws Exception {

        String token = getJWT(modules, PermissionsConstants.LIST_ITEM_TAX, audience);

        String json = "{\n"
            + "  \"orderId\": 1,\n"
            + "  \"criteria\": {\n"
            + "    \"locationId\": 2\n"
            + "  },\n"
            + "  \"items\": [\n"
            + "    {\n"
            + "      \"productTypeId\": 1,\n"
            + "      \"articleId\": 1,\n"
            + "      \"price\": 8900,\n"
            + "      \"discount\": 500,\n"
            + "      \"quantity\": 2\n"
            + "    }\n"
            + "  ]\n"
            + "}";

        when(itemTaxFacade.findAndCalculate(anyList(), anyMap())).thenReturn(getTaxableProduct());

        //Call to code to test
        this.mockMvc.perform(post("/v1/items/taxes")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_ListItemTax_Should_Return400_When_InvalidDTO()
        throws Exception {

        String token = getJWT(modules, PermissionsConstants.LIST_ITEM_TAX, audience);

        String json = "{{\n"
            + "  \"orderId\": 1,\n"
            + "  \"criteria\": {\n"
            + "    \"locationId\": 2\n"
            + "  },\n"
            + "  \"items\": [\n"
            + "    {\n"
            + "      \"productTypeId\": 1,\n"
            + "      \"articleId\": 1,\n"
            + "      \"price\": 8900,\n"
            + "      \"discount\": 500,\n"
            + "      \"quantity\": 2\n"
            + "    }\n"
            + "  ]\n"
            + "}";

        when(itemTaxFacade.findAndCalculate(anyList(), anyMap())).thenReturn(getTaxableProduct());

        //Call to code to test
        this.mockMvc.perform(post("/v1/items/taxes")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").value("Bad Request"))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_ListItemTax_Should_Return401_When_InvalidToken()
        throws Exception {

        String json = "{\n"
            + "  \"orderId\": 1,\n"
            + "  \"criteria\": {\n"
            + "    \"locationId\": 2\n"
            + "  },\n"
            + "  \"items\": [\n"
            + "    {\n"
            + "      \"productTypeId\": 1,\n"
            + "      \"articleId\": 1,\n"
            + "      \"price\": 8900,\n"
            + "      \"discount\": 500,\n"
            + "      \"quantity\": 2\n"
            + "    }\n"
            + "  ]\n"
            + "}";

        when(itemTaxFacade.findAndCalculate(anyList(), anyMap())).thenReturn(getTaxableProduct());

        //Call to code to test
        this.mockMvc.perform(post("/v1/items/taxes")
            .header(HttpHeaders.AUTHORIZATION, "token")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").value("Unauthorized request."))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_ListItemTax_Should_Return401_When_InvalidPermissionToken()
        throws Exception {

        String token = getJWT(modules, PermissionsConstants.CREATE_TAXES, audience);

        String json = "{\n"
            + "  \"orderId\": 1,\n"
            + "  \"criteria\": {\n"
            + "    \"locationId\": 2\n"
            + "  },\n"
            + "  \"items\": [\n"
            + "    {\n"
            + "      \"productTypeId\": 1,\n"
            + "      \"articleId\": 1,\n"
            + "      \"price\": 8900,\n"
            + "      \"discount\": 500,\n"
            + "      \"quantity\": 2\n"
            + "    }\n"
            + "  ]\n"
            + "}";

        when(itemTaxFacade.findAndCalculate(anyList(), anyMap())).thenReturn(getTaxableProduct());

        //Call to code to test
        this.mockMvc.perform(post("/v1/items/taxes")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").value("Forbidden"))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    private List<TaxableItem> getTaxableProduct() {
        List<TaxableItem> taxableProductList = new ArrayList<>();
        TaxableItem taxableProduct =  TaxableItem.builder()
            .discount(new BigDecimal(100))
            .price(new BigDecimal(8900))
            .quantity(1)
            .productTypeId(1L)
            .articleId(1L)
            .totalTax(new BigDecimal(55))
            .taxes(getCalculateTax())
            .build();
        taxableProductList.add(taxableProduct);
        return taxableProductList;
    }

    private List<CalculatedTax> getCalculateTax(){
        List<CalculatedTax> calculatedTaxList = new ArrayList<>();
        CalculatedTax calculatedTax = CalculatedTax.builder()
            .name("")
            .taxId(1L)
            .taxRate(new BigDecimal(9))
            .taxId(1L)
            .familyId(1L)
            .familyTypeId(1L)
            .sapId("asf")
            .build();
        calculatedTaxList.add(calculatedTax);
        return calculatedTaxList;
    }

}
