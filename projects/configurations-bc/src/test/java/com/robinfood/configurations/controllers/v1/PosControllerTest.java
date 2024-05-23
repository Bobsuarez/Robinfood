package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.dto.v1.ActivePosDTOMock;
import com.robinfood.configurations.dto.v1.ResolutionsIdsDTO;
import com.robinfood.configurations.dto.v1.StorePosDTO;
import com.robinfood.configurations.dto.v1.UpdatePosDTOMock;
import com.robinfood.configurations.dto.v1.models.ActivePosDTO;
import com.robinfood.configurations.dto.v1.models.CreatePosDTO;
import com.robinfood.configurations.dto.v1.models.UpdatePosDTO;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.samples.PosSample;
import com.robinfood.configurations.samples.StorePosDTOSample;
import com.robinfood.configurations.services.PosService;
import com.robinfood.configurations.services.StoreService;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.CREATE_STORE;
import static com.robinfood.configurations.constants.PermissionsConstants.UPDATE_POS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PosController.class)
@TestPropertySource(properties = {
        "jwt.token.mod=configurations_bc"
})
class PosControllerTest extends BaseControllerTest {

    private static final String ENCODING = "utf-8";
    private static final String PATH = "/v1/pos?store_id=1&pos_type_id=1";
    private static final String MODULES = "configurations_bc";
    private static final Long TEST_LONG = 1L;
    private static final String JSON = "{\"sort\":\"name\"}";

    @MockBean
    private PosService service;

    @MockBean
    private StoreService storeService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_FindPosId_Should_Return401_When_AudienceIsNotValid() throws Exception {
        String token = getJWT("test_error", "test_error", "internal");
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
    void test_FindPosId_Should_ReturnPosId_When_IdReceivedReturnSuccess200()
            throws Exception {
        when(service.findPosId(anyLong(), anyLong())).thenReturn(TEST_LONG);
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
    void test_FindPosByStoreId_Should_ReturnPosId_When_IdReceivedReturnSuccess200()
            throws Exception {

        List<Resolution> listResolution = new ArrayList<>();
        listResolution.add(Resolution.builder().
                id(1L).status(1).startingNumber("1").finalNumber("10")
                .name("Caja 1 - MUY Kennedy")
                .startDate(LocalDateTime.parse("2022-02-10T05:00:00"))
                .endDate(LocalDateTime.parse("2023-02-10T05:00:00"))
                .prefix("RM42").invoiceText("-").serial("123ABCD")
                .invoiceNumberResolutions("18764025218798")
                .document("Doc Aut DIAN").build());

        List<Pos> listPos = new ArrayList<>();
        listPos.add(Pos.builder()
                .id(1L).name("Caja 1").code("na")
                .resolutionList(listResolution).build()
        );

        when(service.findByStoreId(anyLong())).thenReturn(listPos);

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(get("/v1/pos/stores/65")
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
    void test_FindPosByStoreId_Should_ReturnPosId_When_AudienceIsNotValid()
            throws Exception {
        String token = getJWT("test_error", "test_error", "internal");
        mockMvc.perform(get("/v1/pos/stores/65")
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
    void test_CreatePos_Should_Return201_When_ValidData_Status_False() throws Exception {

        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + CREATE_STORE, "service");

        Mockito.when(service.create(any(StorePosDTO.class)))
                .thenReturn(StorePosDTOSample.getStorePosDTO());

        this.mockMvc.perform(post("/v1/pos/stores/pos")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(buildCreatePosDTO(false)))
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("201"))
                .andExpect(jsonPath("$.message").value("Pos created successfully"));

    }

    @Test
    void test_CreatePos_Should_Return201_When_ValidData_Status_True() throws Exception {

        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + CREATE_STORE, "service");

        Mockito.when(service.create(any(StorePosDTO.class)))
                .thenReturn(StorePosDTOSample.getStorePosDTO());

        this.mockMvc.perform(post("/v1/pos/stores/pos")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(buildCreatePosDTO(true)))
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("201"))
                .andExpect(jsonPath("$.message").value("Pos created successfully"));

    }

    @Test
    void test_UpdatePos_Should_Return201_When_ValidData_Status_False() throws Exception {

        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + CREATE_STORE, "service");

        Mockito.when(service.update(anyLong(), any(UpdatePosDTO.class)))
                .thenReturn(PosSample.getPos());

        this.mockMvc.perform(put("/v1/pos/stores/pos/1")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(UpdatePosDTOMock.getUpdatePosDTOStatusFalse()))
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.code").value("202"))
                .andExpect(jsonPath("$.message").value("Pos updated successfully"));

    }

    @Test
    void test_UpdatePos_Should_Return201_When_ValidData_Status_True() throws Exception {

        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + UPDATE_POS, "service");

        Mockito.when(service.update(anyLong(), any(UpdatePosDTO.class)))
                .thenReturn(PosSample.getPos());

        this.mockMvc.perform(put("/v1/pos/stores/pos/1")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(UpdatePosDTOMock.getUpdatePosDTOStatusTrue()))
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.code").value("202"))
                .andExpect(jsonPath("$.message").value("Pos updated successfully"));

    }

    @Test
    void test_ActivePos_Should_Return202_When_ValidData_Status_True() throws Exception {

        final String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + UPDATE_POS, "service");

        Mockito.when(service.activePos(any(ActivePosDTO.class), anyLong()))
                .thenReturn(PosSample.getPos());

        this.mockMvc.perform(patch("/v1/pos/stores/pos/1/active")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(ActivePosDTOMock.getSActivePosDTOTrue()))
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.code").value("202"))
                .andExpect(jsonPath("$.message").value("Pos updated successfully"));

    }

    @Test
    void test_ActivePos_Should_Return202_When_ValidData_Status_False() throws Exception {

        final String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + UPDATE_POS, "service");

        Mockito.when(service.activePos(any(ActivePosDTO.class), anyLong()))
                .thenReturn(PosSample.getPos());

        this.mockMvc.perform(patch("/v1/pos/stores/pos/1/active")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(ActivePosDTOMock.getSActivePosDTOFalse()))
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.code").value("202"))
                .andExpect(jsonPath("$.message").value("Pos updated successfully"));

    }

    private StorePosDTO buildCreatePosDTO(boolean status) {

        return StorePosDTO.builder()
                .pos(List.of(CreatePosDTO.builder()
                        .code("TEST")
                        .name("TEST")
                        .posTypeId(TEST_LONG)
                        .status(status)
                        .resolutionsIds(ResolutionsIdsDTO.builder()
                                .id(TEST_LONG)
                                .resolutionId(TEST_LONG).build())
                        .build()))
                .storeId(TEST_LONG)
                .build();
    }

    @Test
    void test_listPos_Should_Return200_When_ValidInput() throws Exception {
        Pos pos = PosSample.getPosWithResolutions();

        List<Pos> posList1 = List.of(pos);
        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        //Mock
        when(service.list("caja", null, 1L, PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(posList1, PageRequest.of(0, 10), 10));

        //Call to code to test
        executeGetRequest(token, "/v1/pos/all?storeId=1&page=1&size=10&posName=caja&sorted=false");
        executeGetRequest(token, "/v1/pos/all?storeId=1&page=1&size=10&posName=caja&sort=id,asc");
        executeGetRequest(token, "/v1/pos/all?posName=caja&storeId=1&page=1&size=10&sort=id,asc");
        executeGetRequest(token, "/v1/pos/all?posName=caja&storeId=1&page=1&size=10&sort=id,asc");
        executeGetRequest(token, "/v1/pos/all?posName=caja&storeId=1&page=1&size=10&sort=id,desc");
        executeGetRequest(token, "/v1/pos/all?posName=caja&storeId=1&page=1&size=10&sort=posName,desc");
        executeGetRequest(token, "/v1/pos/all?posName=caja&storeId=1&page=1&size=10&sort=posName,asc");
        executeGetRequest(token, "/v1/pos/all?posName=caja&storeId=1&page=1&size=10&sort=");
        executeGetRequest(token, "/v1/pos/all?posName=caja&storeId=1&page=1&size=10&sort=");
        executeGetRequest(token, "/v1/pos/all?posName=caja&storeId=1&page=1&size=10&unsorted=true&sorted=true");

    }

    @Test
    void test_listPos_Should_Return200_When_PosName_Is_Null() throws Exception {
        Pos pos = PosSample.getPosWithResolutions();

        List<Pos> posList1 = List.of(pos);
        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        //Mock
        when(service.list("", null, 1L, PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(posList1, PageRequest.of(0, 10), 10));

        //Call to code to test
        executeGetRequest(token, "/v1/pos/all?storeId=1&page=1&size=10&sorted=false");
        executeGetRequest(token, "/v1/pos/all?storeId=1&page=1&size=10&sort=posTypeId,desc");
        executeGetRequest(token, "/v1/pos/all?posName=c&storeId=1&page=1&size=10&sorted=false");
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

}