package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.ChannelDTO;
import com.robinfood.configurations.dto.v1.CreateStoreDTO;
import com.robinfood.configurations.dto.v1.StoreDTO;
import com.robinfood.configurations.dto.v1.UpdateStoreDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreTax;
import com.robinfood.configurations.samples.StoreSample;
import com.robinfood.configurations.services.ChannelService;
import com.robinfood.configurations.services.StoreService;
import com.robinfood.configurations.services.StoreTaxService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.time.LocalDateTime;
import java.util.List;
import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.CREATE_STORE;
import static com.robinfood.configurations.constants.PermissionsConstants.LIST_STORES;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;
import static com.robinfood.configurations.constants.PermissionsConstants.UPDATE_STORE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = StoreController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=configurations_bc"
})
class StoreControllerTest extends BaseControllerTest {

    private static final LocalDateTime CURRENT_DATE = LocalDateTime.MIN;
    private static final String ENCODING = "utf-8";
    private static final String PATH = "/v1/stores/1";
    private static final String PATH_FILTER = "/v1/stores/filter";
    private static final String TEST = "TEST";
    private static final String MODULES = "configurations_bc";
    private static final Long TEST_LONG = 1L;
    private static final String JSON = "{\"sort\":\"name\"}";

    @MockBean
    private StoreService service;

    @MockBean
    private ChannelService channelService;

    @MockBean
    private StoreTaxService storeTaxService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_FindStoreId_Should_Return401_When_AudienceIsNotValid() throws Exception {
        String token = getJWT("test_error", "", "internal");
        mockMvc.perform(get(PATH)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").value("Unauthorized"))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.metaData").isEmpty());
    }

    @Test
    void test_FindStore_Should_ReturnStore_When_ReturnCompleteDataSuccess200()
        throws Exception {
        Store store = StoreSample.getStore();
        when(service.findById(anyLong())).thenReturn(store);
        when(storeTaxService.findByIdStore(1L)).thenReturn(List.of(new StoreTax()));
        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(get(PATH)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(content().string(Matchers.containsString("id")))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_CreateStore_Should_Return201_When_ValidData()
        throws Exception {
        // Given

        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + CREATE_STORE, "service");

        // When
        Mockito.when(service.create(any(Store.class)))
            .thenReturn(StoreSample.getStore());

        // Then
        this.mockMvc.perform(post("/v1/stores")
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(objectMapper.writeValueAsString(buildCreateStoreDTO()))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value("201"))
            .andExpect(jsonPath("$.message").value("Store created successfully"))
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_CreateStore_Should_Return400_When_InvalidData()
        throws Exception {
        // Given

        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + CREATE_STORE, "service");

        CreateStoreDTO invalidData = buildCreateStoreDTO();
        invalidData.setAddress(null);

        // When
        Mockito.when(service.create(any(Store.class)))
            .thenThrow(BusinessRuleException.class);

        // Then
        this.mockMvc.perform(post("/v1/stores")
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(objectMapper.writeValueAsString(invalidData))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void test_CreateStore_Should_Return401_When_Unauthorized() throws Exception {
        // Given

        String token = getJWT(MODULES, "", "");

        // When
        Mockito.when(service.create(any(Store.class)))
            .thenReturn(StoreSample.getStore());

        // Then
        this.mockMvc.perform(post("/v1/stores")
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(objectMapper.writeValueAsString(buildCreateStoreDTO()))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void test_UpdateStore_Should_Return201_When_ValidData() throws Exception {
        // Given

        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + UPDATE_STORE, "service");

        // When
        Mockito.when(service.update(anyLong(), any(Store.class)))
            .thenReturn(StoreSample.getStore());

        // Then
        this.mockMvc.perform(put("/v1/stores/1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(objectMapper.writeValueAsString(buildUpdateStoreDTO()))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value("201"))
            .andExpect(jsonPath("$.message").value("Store updated successfully"))
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_UpdateStore_Should_Return400_When_InvalidData()
        throws Exception {
        // Given

        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + UPDATE_STORE, "service");

        UpdateStoreDTO invalidData = buildUpdateStoreDTO();
        invalidData.setAddress(null);

        // When
        Mockito.when(service.update(anyLong(), any(Store.class)))
            .thenThrow(BusinessRuleException.class);

        // Then
        this.mockMvc.perform(put("/v1/stores/1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(objectMapper.writeValueAsString(invalidData))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void test_UpdateStore_Should_Return401_When_Unauthorized() throws Exception {
        // Given

        String token = getJWT(MODULES, "", "");

        // When
        Mockito.when(service.update(anyLong(), any(Store.class)))
            .thenReturn(StoreSample.getStore());

        // Then
        this.mockMvc.perform(put("/v1/stores/1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(objectMapper.writeValueAsString(buildUpdateStoreDTO()))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void test_ListStore_Should_Return200_When_ValidInput() throws Exception {

        List<Store> campaignList = List.of(StoreSample.getStore());
        String token = getJWT(MODULES, PermissionsConstants.SERVICE,
            "service");

        //Mock
        when(service.list(anyString(), anyLong(), any(
            Pageable.class))).thenReturn(new PageImpl<>(
            campaignList, PageRequest.of(1, 10), 10));

        when(service.countByFilter(anyString(), anyLong())).thenReturn(10);

        //Call to code to test
        executeGetRequest(token,"/v1/stores?name=test&company_country_id=1&page=0&sort=name,asc" );
        executeGetRequest(token,"/v1/stores?name=test&company_country_id=0&page=0&sort=name,desc" );
        executeGetRequest(token,"/v1/stores?name=test&company_country_id=&page=0&sort=id,asc" );
        executeGetRequest(token,"/v1/stores?name=test&company_country_id=1&page=0&sort=id,desc" );
    }

    @Test
    void test_ListStore_Should_Return200_When_ValidInputWithNameNull() throws Exception {

        List<Store> storeList = List.of(StoreSample.getStore());
        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        //Mock
        when(service.list(any(), any(), any(
            Pageable.class))).thenReturn(new PageImpl<>(
                storeList, PageRequest.of(1, 10), 10));

        when(service.countByFilter(any(), any())).thenReturn(10);

        //Call to code to test

        executeGetRequest(token,"/v1/stores?page=0&size=10&sort=name,asc" );
        executeGetRequest(token,"/v1/stores?size=10&page=0&sort=name,desc" );
        executeGetRequest(token,"/v1/stores?page=0&size=10&sort=id,asc" );
        executeGetRequest(token,"/v1/stores?size=10&page=0&sort=id,desc" );
    }

    @Test
    void test_listStore_Should_Return200_When_PageEmpty() throws Exception {
        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + LIST_STORES,
            "service");

        //Mock
        when(service.list(anyString(), anyLong(), any(Pageable.class))).thenReturn(new PageImpl<>(
            List.of(), PageRequest.of(0, 1), 0));

        when(service.countByFilter(anyString(), anyLong())).thenReturn(0);

        //Call to code to test
        executeGetRequest(token,"/v1/stores?name=test&companyCountryId=1&page=0&sort=names,asc" );
        executeGetRequest(token,"/v1/stores?name=test&companyCountryId=1&page=0&sort=id,desc" );
        executeGetRequest(token,"/v1/stores?name=&companyCountryId=&page=0&unsorted=true&sorted=true&empty=true" );

    }

    @Test
    void test_listStore_Should_Return200_When_SortDistinct() throws Exception {
        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + LIST_STORES,
            "service");

        List<Store> campaignList = List.of(StoreSample.getStore());

        //Mock
        when(service.list(anyString(), anyLong(), any(Pageable.class))).thenReturn(new PageImpl<>(
            campaignList, PageRequest.of(0, 10), 10));

        when(service.countByFilter(anyString(), anyLong())).thenReturn(10);

        //Call to code to test
        mockMvc.perform(get("/v1/stores?page=0&name=test&unsorted=true&sorted=true&empty=true")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content("{\"sort\":\"id\"}")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());

    }

    @Test
    void test_listStore_Should_ReturnUnauthorized_When_BearerIsNotValid()
        throws Exception {
        String token = getJWT(MODULES, "INVALID_PERMISSION",
            "service");
        //Call to code to test
        MvcResult mvcResult = mockMvc.perform(get("/v1/stores")
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andReturn();

        ApiResponseDTO<List<StoreDTO>> result = objectMapper
            .readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertNotNull(result);
        assertEquals(401, result.getCode());
        assertNotNull(result.getMessage());
        assertEquals("Unauthorized", result.getMessage());
        assertNull(result.getData());

    }

    @Test
    void test_listStore_Should_ReturnUnauthorized_When_TokenIsEmpty()
        throws Exception {

        //Call to code to test
        MvcResult mvcResult = mockMvc.perform(get("/v1/campaigns")
                .header(HttpHeaders.AUTHORIZATION, "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andReturn();

        ApiResponseDTO<List<StoreDTO>> result = objectMapper
            .readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertNotNull(result);
        assertEquals(401, result.getCode());
        assertNotNull(result.getMessage());
        assertEquals("Unauthorized", result.getMessage());
        assertNull(result.getData());

    }

    @Test
    void test_delete_Should_Return200_When_StoreIsDeleted()
        throws Exception {

        String token = getJWT(MODULES, PermissionsConstants.DELETE_STORE, "service");

        Store store = StoreSample.getStore();
        store.setId(1L);

        // When
        Mockito.when(service.create(any(Store.class)))
            .thenReturn(store);

        // Then
        this.mockMvc.perform(delete("/v1/stores/1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("204"))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void test_FindStoresWithoutSearch_Should_ReturnStores_When_ReturnCompleteDataSuccess200()
        throws Exception {
        Store store = StoreSample.getStore();
        when(service.findStores(null)).thenReturn(List.of(store));

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(get(PATH_FILTER)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(content().string(Matchers.containsString("id")))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_FindStoresWithSearch_Should_ReturnStores_When_ReturnCompleteDataSuccess200()
        throws Exception {
        Store store = StoreSample.getStore();
        when(service.findStores("TES")).thenReturn(List.of(store));

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(get(PATH_FILTER.concat("?search=TES"))
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(content().string(Matchers.containsString("id")))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_findChannelByStoreId_Should_ReturnChannel_When_ReturnCompleteDataSuccess200() throws Exception {
        List<ChannelDTO> channels = List.of(new ChannelDTO());
        String token = getJWT(MODULES, SERVICE, "service");

        //Mock
        when(channelService.findByStoreId(anyLong())).thenReturn(channels);

        //Call to code to test
        executeGetRequest(token, "/v1/stores/1/channels");
    }

    @Test
    void test_FindStoresWithSearch_Should_ReturnWithoutResults_When_ReturnCompleteDataSuccess200()
        throws Exception {

        when(service.findStores("TEST 2")).thenReturn(List.of());

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(get(PATH_FILTER.concat("?search=TEST 2"))
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void test_FindStoresWithSearchInvalid_Should_ReturnError_When_ReturnBadRequest400()
        throws Exception {

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(get(PATH_FILTER.concat("?search=T"))
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.error").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty());
    }

    private void executeGetRequest(String token, String path) throws Exception {
        mockMvc.perform(get(path)
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    private CreateStoreDTO buildCreateStoreDTO() {
        return CreateStoreDTO.builder()
            .name(TEST)
            .address(TEST)
            .location(TEST)
            .phone(TEST)
            .email(TEST)
            .internalName(TEST)
            .identifier(TEST)
            .cityId(1L)
            .companyCountryId(TEST_LONG)
            .storeTypeId(TEST_LONG)
            .zoneId(TEST_LONG)
            .internalName(TEST)
            .storeIdentifierTypeId(TEST_LONG)
            .build();
    }

    private UpdateStoreDTO buildUpdateStoreDTO() {
        return UpdateStoreDTO.builder()
            .name(TEST)
            .address(TEST)
            .location(TEST)
            .phone(TEST)
            .email(TEST)
            .internalName(TEST)
            .identifier(TEST)
            .cityId(1L)
            .companyId(TEST_LONG)
            .storeTypeId(TEST_LONG)
            .zoneId(TEST_LONG)
            .internalName(TEST)
            .storeIdentifierTypeId(TEST_LONG)
            .build();
    }
}