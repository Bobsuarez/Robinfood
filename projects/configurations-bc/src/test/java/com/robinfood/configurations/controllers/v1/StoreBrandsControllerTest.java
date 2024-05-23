package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.AssignBrandToStoreRequestDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreBrand;
import com.robinfood.configurations.services.BrandService;
import com.robinfood.configurations.services.CompanyBrandService;
import com.robinfood.configurations.services.StoreBrandsService;
import com.robinfood.configurations.services.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = StoreBrandsController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=configurations_bc"
})
public class StoreBrandsControllerTest extends BaseControllerTest {

    @MockBean
    private StoreService storeService;

    @MockBean
    private CompanyBrandService companyBrandService;

    @MockBean
    private BrandService brandService;

    @MockBean
    private StoreBrandsService storeBrandsService;

    @InjectMocks
    private StoreBrandsController storeBrandsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsert_Success() throws JsonProcessingException, BusinessRuleException {
        // Given
        AssignBrandToStoreRequestDTO requestDTO = new AssignBrandToStoreRequestDTO();
        requestDTO.setStoreId(1L);
        requestDTO.setMenuBrandId(2L);
        StoreBrand storeBrand = new StoreBrand();

        // When
        when(storeBrandsService.create(any(StoreBrand.class))).thenReturn(storeBrand);
        when(companyBrandService.getByCompanyIdAndMenuBrandId(anyLong())).thenReturn(
            CompanyBrand.builder().build());
        when(storeService.findById(anyLong())).thenReturn(Store.builder().build());

        ResponseEntity<ApiResponseDTO<StoreBrand>> response = storeBrandsController.insert(
            requestDTO);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("StoreBrand created successfully", response.getBody().getMessage());
    }

    @Test
    public void testInsert_AlreadyExists() throws JsonProcessingException, BusinessRuleException {
        // given
        AssignBrandToStoreRequestDTO requestDTO = new AssignBrandToStoreRequestDTO();
        requestDTO.setStoreId(1L);
        requestDTO.setMenuBrandId(2L);

        Store store = new Store();

        // When
        when(companyBrandService.getByCompanyIdAndMenuBrandId(anyLong())).thenReturn(
            CompanyBrand.builder().build());
        when(storeBrandsService.create(any(StoreBrand.class))).thenThrow(new BusinessRuleException("StoreBrand already exists."));
        when(storeService.findById(anyLong())).thenReturn(store);

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> {
            storeBrandsController.insert(requestDTO);
        });
        assertEquals("StoreBrand already exists.", exception.getMessage());
    }

    @Test
    public void testInsert_MenuBrandDoesntExists()
        throws JsonProcessingException, BusinessRuleException {
        // given
        AssignBrandToStoreRequestDTO requestDTO = new AssignBrandToStoreRequestDTO();
        requestDTO.setStoreId(1L);
        requestDTO.setMenuBrandId(2123L);
        Store store = new Store();
        CompanyBrand companyBrand = new CompanyBrand();

        // When
        when(companyBrandService.getByCompanyIdAndMenuBrandId(anyLong())).thenReturn(null);
        when(storeBrandsService.create(any(StoreBrand.class))).thenThrow(new BusinessRuleException("Menu brand doesn't exists."));
        when(storeService.findById(1L)).thenReturn(store);

        // Then
        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> {
            storeBrandsController.insert(requestDTO);
        });
        assertEquals("Menu brand doesn't exists.", exception.getMessage());
    }

    @Test
    public void testInsert_CreatedStoreBrandIsNull() throws JsonProcessingException, BusinessRuleException {
        // Given
        AssignBrandToStoreRequestDTO requestDTO = new AssignBrandToStoreRequestDTO();
        requestDTO.setStoreId(1L);
        requestDTO.setMenuBrandId(2L);

        Store store = new Store();

        // When
        when(companyBrandService.getByCompanyIdAndMenuBrandId(anyLong())).thenReturn(
            CompanyBrand.builder().build());
        when(storeBrandsService.create(any(StoreBrand.class))).thenReturn(null);
        when(storeService.findById(anyLong())).thenReturn(store);

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> {
            storeBrandsController.insert(requestDTO);
        });

        // Then
        assertEquals("StoreBrand already exists.", exception.getMessage());
    }
}
