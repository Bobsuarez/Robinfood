package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ActivePosDTOMock;
import com.robinfood.configurations.dto.v1.StorePosDTO;
import com.robinfood.configurations.dto.v1.UpdatePosDTOMock;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.models.BrandCompanyChannel;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.Headquarter;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.repositories.jpa.PosRepository;
import com.robinfood.configurations.repositories.jpa.ResolutionRepository;
import com.robinfood.configurations.samples.PosSample;
import com.robinfood.configurations.samples.StorePosDTOSample;
import com.robinfood.configurations.samples.StoreSample;
import com.robinfood.configurations.services.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PosServiceImplTest {

    private static final String TEST = "TEST";
    private static final Long TEST_LONG = 1L;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();

    @InjectMocks
    private PosServiceImpl posService;

    @Mock
    private StoreService storeService;

    @Mock
    private PosRepository posRepository;

    @Mock
    private ResolutionRepository resolutionRepository;

    private Headquarter headquarterModel;
    private City cityModel;
    private Company companyModel;
    private Store storeModel;
    private Pos posModel;

    private StorePosDTO storePosDTOModel;
    private BrandCompanyChannel brandCompanyCountryModel;
    private Resolution resolutionModel;

    @BeforeEach
    void setUp() {
        buildHeadquarter();
        buildCompany();
        buildCity();
        buildBrandCompanyCountry();
        buildCompanyCountry();
        buildStore();
        buildResolution();
        buildPos();
    }

    private void buildPos() {
        posModel = Pos.builder()
                .name(TEST)
                .code(TEST)
                .store(storeModel)
                .resolutionList(Collections.singletonList(resolutionModel))
                .build();
        posModel.setId(TEST_LONG);
        posModel.setCreatedAt(CURRENT_DATE);
        posModel.setUpdatedAt(CURRENT_DATE);
        posModel.setDeletedAt(null);
    }

    private void buildResolution() {
        resolutionModel = Resolution.builder()
                .status(1)
                .startingNumber(TEST)
                .finalNumber(TEST)
                .name(TEST)
                .startDate(CURRENT_DATE)
                .endDate(CURRENT_DATE)
                .prefix(TEST)
                .invoiceText(TEST)
                .serial(TEST)
                .invoiceNumberResolutions(TEST)
                .document(TEST)
                .pos(null)
                .build();
    }

    private void buildStore() {
        storeModel = Store.builder()
                .name(TEST)
                .address(TEST)
                .city(cityModel)
                .company(companyModel)
                .build();
        storeModel.setId(TEST_LONG);
        storeModel.setCreatedAt(CURRENT_DATE);
        storeModel.setUpdatedAt(CURRENT_DATE);
        storeModel.setDeletedAt(null);
    }

    private void buildCompanyCountry() {
        companyModel = Company.builder()
                .name(TEST)
                .country(null)
                .build();
    }

    private void buildBrandCompanyCountry() {
        Brand brandModel = Brand.builder().build();
        brandModel.setId(TEST_LONG);
        brandModel.setCreatedAt(CURRENT_DATE);
        brandModel.setUpdatedAt(CURRENT_DATE);
        brandModel.setDeletedAt(null);

        brandCompanyCountryModel = BrandCompanyChannel.builder()
                .banner(TEST)
                .color(TEST)
                .configUrl(TEST)
                .icon(TEST)
                .build();
    }

    private void buildCity() {
        cityModel = City.builder()
                .name(TEST)
                .timezone(TEST)
                .state(null)
                .build();
        cityModel.setId(TEST_LONG);
        cityModel.setCreatedAt(CURRENT_DATE);
        cityModel.setUpdatedAt(CURRENT_DATE);
        cityModel.setDeletedAt(null);
    }

    private void buildCompany() {
        companyModel = Company.builder()
                .name(TEST)
                .headquarter(headquarterModel)
                .build();
        companyModel.setId(TEST_LONG);
        companyModel.setCreatedAt(CURRENT_DATE);
        companyModel.setUpdatedAt(CURRENT_DATE);
        companyModel.setDeletedAt(null);
    }

    private void buildHeadquarter() {
        headquarterModel = Headquarter.builder()
                .phone(TEST)
                .email(TEST)
                .address(TEST)
                .cityName(TEST)
                .company(companyModel)
                .build();
        headquarterModel.setId(TEST_LONG);
        headquarterModel.setCreatedAt(CURRENT_DATE);
        headquarterModel.setUpdatedAt(CURRENT_DATE);
        headquarterModel.setDeletedAt(null);
    }

    @Test
    void test_FindPosId_Should_ReturnPosId() {
        when(posRepository.findPosId(anyLong(),
                anyLong())).thenReturn(
                TEST_LONG);
        assertAll(() -> posService.findPosId(TEST_LONG, TEST_LONG));
    }

    @Test
    void test_FindPosId_Should_ReturnPosIdNull() {
        when(posRepository.findPosId(anyLong(),
                anyLong())).thenReturn(
                null);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            posService.findPosId(TEST_LONG, TEST_LONG);
        });

        verify(posRepository, times(1)).findPosId(
                anyLong(), anyLong());
        String expectedMessage = String.format(
                "POS id with store id %d and pos type id %d does not exists.",
                TEST_LONG, TEST_LONG);
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_FindPosById_Should_ReturnPosId() {
        when(posRepository.findById(anyLong())).thenReturn(Optional.ofNullable(posModel));
        assertAll(() -> posService.findById(TEST_LONG));
    }

    @Test
    void test_FindPosById_Should_ReturnException_When_PosNotExists() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            posService.findById(TEST_LONG);
        });

        verify(posRepository, times(1)).findById(anyLong());
        String expectedMessage = String.format("POS with id %d not found.", TEST_LONG, TEST_LONG);
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_FindPosById_Should_ReturnException_When_PosIsEmpty() {
        when(posRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            posService.findById(TEST_LONG);
        });

        verify(posRepository, times(1)).findById(anyLong());
        String expectedMessage = String.format("POS with id %d not found.", TEST_LONG, TEST_LONG);
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_FindPosByStoreId_Success() {

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

        when(posRepository.findAllByStoreId(anyLong())).thenReturn(Optional.of(listPos));
        assertAll(() -> posService.findByStoreId(anyLong()));

    }

    @Test
    void test_FindPosByStoreId_SuccessWithDeleteAtNotNullAndStatusZero() {

        List<Resolution> listResolution = new ArrayList<>();
        listResolution.add(Resolution.builder()
                .deletedAt(LocalDateTime.now())
                .id(1L).status(0).startingNumber("1").finalNumber("10")
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

        when(posRepository.findAllByStoreId(anyLong())).thenReturn(Optional.of(listPos));
        assertAll(() -> posService.findByStoreId(anyLong()));

    }

    @Test
    void test_FindPosByStoreId_IsEmpty() {
        when(posRepository.findAllByStoreId(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            posService.findByStoreId(TEST_LONG);
        });
        verify(posRepository, times(1)).findAllByStoreId(anyLong());
        String expectedMessage = String.format("POS with StoreId %d not found.", TEST_LONG);
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_CreateSPos_Should_ThrowBusinessRuleException_When_AlreadyExistingName() throws BusinessRuleException, JsonProcessingException {

        when(storeService.findById(anyLong())).thenReturn(StoreSample.getStore());

        when(posRepository.saveAndFlush(any())).thenReturn(PosSample.getPos());

        when(resolutionRepository.findById(1L)).thenReturn(Optional.of(Resolution.builder().id(1L).build()));

        posService.create(StorePosDTOSample.getStorePosDTO());

        verify(storeService).findById(anyLong());

        verify(posRepository, times(1))
                .saveAndFlush(any());
    }

    @Test
    void test_CreateSPos_Should_When_Resolutions_id_Is_Null() throws BusinessRuleException, JsonProcessingException {

        when(storeService.findById(anyLong())).thenReturn(StoreSample.getStore());

        when(posRepository.saveAndFlush(any())).thenReturn(PosSample.getPos());

        posService.create(StorePosDTOSample.getStorePosResolutionsIdsNUllDTO());

        verify(storeService).findById(anyLong());

        verify(posRepository, times(1))
                .saveAndFlush(any());
    }


    @Test
    void test_CreateSPos_Should_Asigned_Pos_Id_Exception() {
        when(posRepository.saveAndFlush(any())).thenReturn(PosSample.getPos());
        when(resolutionRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            posService.create(StorePosDTOSample.getStorePosDTO());
        });
        verify(resolutionRepository, times(1)).findById(anyLong());
        String expectedMessage = String.format("Resolutions not found %d", TEST_LONG);
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_UpdatePos_Should_Status_True_ThrowBusinessRuleException() throws BusinessRuleException, JsonProcessingException {

        when(posRepository.findById(anyLong())).thenReturn(Optional.of(PosSample.getPos()));

        when(posRepository.save(any(Pos.class))).thenReturn(PosSample.getPos());

        posService.update(anyLong(), UpdatePosDTOMock.getUpdatePosDTOStatusTrue());

        verify(posRepository).findById(anyLong());

        verify(posRepository, times(1)).save(any(Pos.class));
    }

    @Test
    void test_UpdatePos_Should_Status_False_ThrowBusinessRuleException() throws BusinessRuleException, JsonProcessingException {

        when(posRepository.findById(anyLong())).thenReturn(Optional.of(posModel));

        when(posRepository.save(any(Pos.class))).thenReturn(PosSample.getPos());

        posService.update(1L, UpdatePosDTOMock.getUpdatePosDTOStatusFalse());

        verify(posRepository).findById(anyLong());

        verify(posRepository, times(1)).save(any(Pos.class));
    }

    @Test
    void test_ActivePos_Should_Status_True_ThrowBusinessRuleException() throws BusinessRuleException, JsonProcessingException {

        when(posRepository.findById(anyLong())).thenReturn(Optional.of(PosSample.getPos()));

        when(posRepository.save(any(Pos.class))).thenReturn(PosSample.getPos());

        posService.activePos(ActivePosDTOMock.getSActivePosDTOTrue(), anyLong());

        verify(posRepository).findById(anyLong());

        verify(posRepository, times(1)).save(any(Pos.class));
    }

    @Test
    void test_ActivePos_Should_Status_False_ThrowBusinessRuleException() throws BusinessRuleException, JsonProcessingException {

        when(posRepository.findById(anyLong())).thenReturn(Optional.of(PosSample.getPos()));

        when(posRepository.save(any(Pos.class))).thenReturn(PosSample.getPos());

        posService.activePos(ActivePosDTOMock.getSActivePosDTOFalse(), anyLong());

        verify(posRepository).findById(anyLong());

        verify(posRepository, times(1)).save(any(Pos.class));
    }

    @Test
    void test_List_pos() {

        Pos pos = PosSample.getPosWithResolutions();

        List<Pos> posList1 = List.of(pos);

        when(posRepository.findByStoreIdAndNameAndStatus(null, 1L, 1L, PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(posList1, PageRequest.of(0, 10), 10));

        assertAll(() -> posService.list(null, 1L, 1L, PageRequest.of(0, 10)));
    }
}