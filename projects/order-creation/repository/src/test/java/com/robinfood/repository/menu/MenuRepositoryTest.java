package com.robinfood.repository.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.core.dtos.MenuValidationDTO;
import com.robinfood.core.dtos.menuhallproductdetail.MenuHallProductDetailDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.models.domain.menu.Brand;
import com.robinfood.core.models.retrofit.menu.brand.BrandResponse;
import com.robinfood.core.models.retrofit.menu.hallproductdetail.MenuHallProductDetailResponse;
import com.robinfood.repository.mocks.TransactionRequestDTOMocks;
import com.robinfood.repository.mocks.domain.menu.BrandMock;
import com.robinfood.repository.mocks.menu.BrandResponseMock;
import com.robinfood.repository.mocks.menu.MenuHallProductDetailResponseMocks;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class MenuRepositoryTest {

    @Mock
    private IMenuRemoteDataSource mockMenuRemoteDataSource;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MenuRepository menuRepository;

    private final String token = "token";
    private final TransactionRequestDTOMocks transactionRequestDTOMocks = new TransactionRequestDTOMocks();
    private final OrderDTO orderDTO = transactionRequestDTOMocks.transactionRequestDTO.getOrders().get(0);

    final MenuValidationDTO menuValidationDTO = MenuValidationDTO.builder()
            .countryId(1L)
            .flowId(1L)
            .order(orderDTO)
            .platformId(1L)
            .timezone("America/Bogota")
            .build();

    @Test
    void test_Validate_Menu_Responds_Correctly() {

        when(mockMenuRemoteDataSource.validateMenu(anyString(), anyString(), any())).thenReturn(CompletableFuture.completedFuture(true));

        final Boolean response = menuRepository.validateMenu(token, menuValidationDTO).join();

        assertTrue(response);
    }

    @Test
    void get_brands_by_country_id() {

        // Arrange
        Long countryId = 3L;
        Brand brand = BrandMock.build();
        BrandResponse brandResponse = BrandResponseMock.build();

        when(mockMenuRemoteDataSource.getBrandsByCountryId(any(), any()))
                .thenReturn(Collections.singletonList(brandResponse));

        when(modelMapper.map(any(), any())).thenReturn(brand);

        // Act
        List<Brand> result = menuRepository.getBrandsByCountryId(token, countryId);

        // Assert
        assertEquals(brand.getFranchiseId(), result.get(0).getFranchiseId());
        assertEquals(brand.getId(), result.get(0).getId());
        assertEquals(brand.getCountryId(), result.get(0).getCountryId());
    }

    @Test
    void test_Get_Menu_Hall_Product_Detail() throws JsonProcessingException {

        // Arrange
        Long menuHallProductId = 3L;
        MenuHallProductDetailResponse responseMock = MenuHallProductDetailResponseMocks.aMenuHallProductDetailResponse();
        MenuHallProductDetailDTO menuHallProductDetailDTO = MenuHallProductDetailResponseMocks.aMenuHallProductDTO();

        when(mockMenuRemoteDataSource.getMenuHallProductDetail(anyString(), anyLong()))
                .thenReturn(responseMock);

        when(modelMapper.map(any(), any())).thenReturn(menuHallProductDetailDTO);
        // Act
        MenuHallProductDetailDTO result = menuRepository.getMenuHallProductDetail(token, menuHallProductId);

        // Assert
        assertEquals(responseMock.getName(), result.getName());
        assertEquals(responseMock.getSizeName(), result.getSizeName());
    }
}
