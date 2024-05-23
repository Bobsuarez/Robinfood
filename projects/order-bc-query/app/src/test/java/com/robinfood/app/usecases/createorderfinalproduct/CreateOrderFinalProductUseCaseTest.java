package com.robinfood.app.usecases.createorderfinalproduct;

import com.robinfood.app.datamocks.dto.input.FinalProductDTODataMock;
import com.robinfood.app.datamocks.dto.input.FinalProductPortionDTODataMock;
import com.robinfood.app.datamocks.dto.input.FinalProductTaxDTODataMock;
import com.robinfood.app.datamocks.dto.input.RemovedPortionDTODataMock;
import com.robinfood.app.datamocks.entity.OrderFinalProductEntityMock;
import com.robinfood.app.usecases.calculatesummationfinalproductaddition.ICalculateFinalProductAdditionUseCase;
import com.robinfood.app.usecases.calculatesummationfinalproductdiscount.ICalculateFinalProductDiscountUseCase;
import com.robinfood.app.usecases.calculatesummationfinalproducttax.ICalculateSummationFinalProductTaxUseCase;
import com.robinfood.app.usecases.createorderfinalproductportion.ICreateOrderFinalProductPortionsUseCase;
import com.robinfood.app.usecases.createorderproducttaxes.ICreateOrderProductTaxesUseCase;
import com.robinfood.app.usecases.createorderremovedportion.ICreateOrderRemovedPortionUseCase;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import java.util.Collections;
import java.util.List;

import com.robinfood.repository.orderproductodeduction.IOrderProductFinalDeductionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderFinalProductUseCaseTest {

    @Mock
    private ICalculateFinalProductDiscountUseCase calculateSummationFinalProductDiscountUseCase;

    @Mock
    private ICalculateSummationFinalProductTaxUseCase calculateSummationFinalProductTaxUseCase;

    @Mock
    private ICreateOrderProductTaxesUseCase createOrderProductTaxesUseCase;

    @Mock
    private ICalculateFinalProductAdditionUseCase calculateSummationFinalProductAdditionUseCase;

    @Mock
    private ICreateOrderFinalProductPortionsUseCase createOrderFinalProductPortionUseCase;

    @Mock
    private ICreateOrderRemovedPortionUseCase createOrderRemovedPortionUseCase;

    @Mock
    private IOrderFinalProductRepository orderFinalProductDataSource;

    @Mock
    private IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository;

    @InjectMocks
    private CreateOrderFinalProductUseCase createOrderFinalProductUseCase;

    private OrderFinalProductEntityMock orderFinalProductEntityMock = new OrderFinalProductEntityMock();

    private FinalProductDTODataMock inputFinalProductDTODataMock = new FinalProductDTODataMock();

    private final FinalProductDTO inputFinalProductDTO = inputFinalProductDTODataMock.getDataDefault(1L);

    private final FinalProductDTO inputFinalProductDTOWithOutDeductions = inputFinalProductDTODataMock.getDataDefaultWithoutDeductions(1L);

    private final FinalProductDTO inputFinalProductReplacementDTO = inputFinalProductDTODataMock.getDataReplacementPortion(1L);

    private final FinalProductTaxDTODataMock inputFinalProductTaxDTODataMock = new FinalProductTaxDTODataMock();

    private final FinalProductPortionDTODataMock inputFinalProductPortionDTODataMock = new FinalProductPortionDTODataMock();

    private final RemovedPortionDTODataMock inputRemovedPortionDTODataMock = new RemovedPortionDTODataMock();

    @Test
    void test_CreateOrderFinalProduct_When_Save_Success() {
        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();
        orderFinalProductEntities.get(0).setFinalProductName("Cubano");

        when(calculateSummationFinalProductDiscountUseCase.invoke(inputFinalProductDTO))
                .thenReturn(100.0);

        when(calculateSummationFinalProductAdditionUseCase.invoke(inputFinalProductDTO))
                .thenReturn(100.0);

        when(calculateSummationFinalProductTaxUseCase.invoke(inputFinalProductDTO))
                .thenReturn(50.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        when(createOrderFinalProductPortionUseCase.invoke(inputFinalProductPortionDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderProductTaxesUseCase.invoke(inputFinalProductTaxDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderRemovedPortionUseCase.invoke(inputRemovedPortionDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        final Boolean result = createOrderFinalProductUseCase.invoke(
                inputFinalProductDTODataMock.getDataDefaultList(1L),
                1L,
                1L,
                1L,
             false
            ).join();

            Assertions.assertTrue(result);
    }

    @Test
    void test_CreateOrderFinalProduct_When_Save_Success_Brasil() {
        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();
        orderFinalProductEntities.get(0).setFinalProductName("Cubano");

        when(calculateSummationFinalProductDiscountUseCase.invoke(inputFinalProductDTO))
                .thenReturn(100.0);

        when(calculateSummationFinalProductAdditionUseCase.invoke(inputFinalProductDTO))
                .thenReturn(100.0);

        when(calculateSummationFinalProductTaxUseCase.invoke(inputFinalProductDTO))
                .thenReturn(50.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        when(createOrderFinalProductPortionUseCase.invoke(inputFinalProductPortionDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderProductTaxesUseCase.invoke(inputFinalProductTaxDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderRemovedPortionUseCase.invoke(inputRemovedPortionDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        final Boolean result = createOrderFinalProductUseCase.invoke(
                inputFinalProductDTODataMock.getDataDefaultList(1L),
                1L,
                5L,
                1L,
                false
        ).join();

        Assertions.assertTrue(result);
    }

    @Test
    void test_CreateOrderFinalProduct_When_Save_Deductions_Success() {
        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();
        orderFinalProductEntities.get(0).setFinalProductName("Cubano");

        when(calculateSummationFinalProductDiscountUseCase.invoke(inputFinalProductDTOWithOutDeductions))
                .thenReturn(100.0);

        when(calculateSummationFinalProductAdditionUseCase.invoke(inputFinalProductDTOWithOutDeductions))
                .thenReturn(100.0);

        when(calculateSummationFinalProductTaxUseCase.invoke(inputFinalProductDTOWithOutDeductions))
                .thenReturn(50.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        when(createOrderFinalProductPortionUseCase.invoke(inputFinalProductPortionDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderProductTaxesUseCase.invoke(inputFinalProductTaxDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderRemovedPortionUseCase.invoke(inputRemovedPortionDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        final Boolean result = createOrderFinalProductUseCase.invoke(
                inputFinalProductDTODataMock.getDataDefaultListWithOutDeductions(1L),
                1L,
                1L,
                1L,
                false
        ).join();

        Assertions.assertTrue(result);
    }

    @Test
    void test_CreateOrderFinalProduct_with_portions_empty() {
        // Arrange
        List<FinalProductDTO> finalProductDTOS = inputFinalProductDTODataMock.getDataDefaultList(1L);
        finalProductDTOS.get(0).setPortions(Collections.emptyList());

        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();

        when(calculateSummationFinalProductDiscountUseCase.invoke(any()))
            .thenReturn(100.0);

        when(calculateSummationFinalProductTaxUseCase.invoke(any()))
            .thenReturn(50.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        when(createOrderFinalProductPortionUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderProductTaxesUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderRemovedPortionUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        // Act
        final Boolean result = createOrderFinalProductUseCase.invoke(
            finalProductDTOS,
            1L,
            1L,
            1L,
            false
        ).join();

        // Assert
        verify(calculateSummationFinalProductAdditionUseCase, times(0)).invoke(any());
    }

    @Test
    void test_CreateOrderFinalProduct_with_portions_null() {
        // Arrange
        List<FinalProductDTO> finalProductDTOS = inputFinalProductDTODataMock.getDataDefaultList(1L);
        finalProductDTOS.get(0).setPortions(null);

        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();

        when(calculateSummationFinalProductDiscountUseCase.invoke(any()))
            .thenReturn(100.0);

        when(calculateSummationFinalProductTaxUseCase.invoke(any()))
            .thenReturn(50.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        // Assert
        Assertions.assertThrows(
            NullPointerException.class,
            () -> createOrderFinalProductUseCase.invoke(
                finalProductDTOS,
                1L,
                1L,
                1L,
                false
            ),
            ""
        );
        verify(calculateSummationFinalProductAdditionUseCase, times(0)).invoke(any());
    }

    @Test
    void test_CreateOrderFinalProduct_with_taxes_empty() {
        // Arrange
        List<FinalProductDTO> finalProductDTOS = inputFinalProductDTODataMock.getDataDefaultList(1L);
        finalProductDTOS.get(0).setTaxes(Collections.emptyList());

        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();

        when(calculateSummationFinalProductDiscountUseCase.invoke(any()))
            .thenReturn(100.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        when(createOrderFinalProductPortionUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderProductTaxesUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderRemovedPortionUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        // Act
        final Boolean result = createOrderFinalProductUseCase.invoke(
            finalProductDTOS,
            1L,
            1L,
            1L,
            false
        ).join();

        // Assert
        verify(calculateSummationFinalProductTaxUseCase, times(0)).invoke(any());
    }

    @Test
    void test_CreateOrderFinalProduct_with_taxes_null() {
        // Arrange
        List<FinalProductDTO> finalProductDTOS = inputFinalProductDTODataMock.getDataDefaultList(1L);
        finalProductDTOS.get(0).setTaxes(null);

        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();

        when(calculateSummationFinalProductDiscountUseCase.invoke(any()))
            .thenReturn(100.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        // Assert
        Assertions.assertThrows(
            NullPointerException.class,
            () -> createOrderFinalProductUseCase.invoke(
                finalProductDTOS,
                1L,
                1L,
                1L,
                false
            ),
            "Parameter specified as non-null is null: method kotlin.collections.CollectionsKt___CollectionsKt.map, parameter $this$map"
        );
        verify(calculateSummationFinalProductTaxUseCase, times(0)).invoke(any());
    }

    @Test
    void test_CreateOrderFinalProduct_with_discounts_null() {
        // Arrange
        List<FinalProductDTO> finalProductDTOS = inputFinalProductDTODataMock.getDataDefaultList(1L);
        finalProductDTOS.get(0).setDiscounts(null);

        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();

        when(calculateSummationFinalProductAdditionUseCase.invoke(any()))
            .thenReturn(100.0);

        when(calculateSummationFinalProductTaxUseCase.invoke(any()))
            .thenReturn(50.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        when(createOrderFinalProductPortionUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderProductTaxesUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderRemovedPortionUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        // Act
        final Boolean result = createOrderFinalProductUseCase.invoke(
            finalProductDTOS,
            1L,
            1L,
            1L,
            false
        ).join();

        // Assert
        verify(calculateSummationFinalProductDiscountUseCase, times(0)).invoke(any());
    }

    @Test
    void test_CreateOrderFinalProduct_Whit_Replacement_Portion() {

        when(calculateSummationFinalProductDiscountUseCase.invoke(inputFinalProductReplacementDTO))
                .thenReturn(100.0);

        when(calculateSummationFinalProductAdditionUseCase.invoke(inputFinalProductReplacementDTO))
                .thenReturn(100.0);

        when(calculateSummationFinalProductTaxUseCase.invoke(inputFinalProductReplacementDTO))
                .thenReturn(50.0);

        when(orderFinalProductDataSource.saveAll(orderFinalProductEntityMock.getDataDefaultList()))
                .thenReturn(orderFinalProductEntityMock.getDataDefaultList());

        when(createOrderFinalProductPortionUseCase.invoke(inputFinalProductPortionDTODataMock.getDataDefaultReplacementPortionList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderProductTaxesUseCase.invoke(inputFinalProductTaxDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderRemovedPortionUseCase.invoke(inputRemovedPortionDTODataMock.getDataDefaultList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        final Boolean result = createOrderFinalProductUseCase.invoke(
            inputFinalProductDTODataMock.getDataWithReplacementPortionList(1L),
            1L,
            1L,
            1L,
            false
        ).join();

        Assertions.assertTrue(result);
    }

    @Test
    void test_CreateOrderFinalProduct_When_Status_Is_Paid() {
        List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductEntityMock.getDataDefaultList();
        orderFinalProductEntities.get(0).setFinalProductName("Cubano");

        when(calculateSummationFinalProductDiscountUseCase.invoke(inputFinalProductDTO))
            .thenReturn(100.0);

        when(calculateSummationFinalProductAdditionUseCase.invoke(inputFinalProductDTO))
            .thenReturn(100.0);

        when(calculateSummationFinalProductTaxUseCase.invoke(inputFinalProductDTO))
            .thenReturn(50.0);

        when(orderFinalProductDataSource.saveAll(any())).thenReturn(orderFinalProductEntities);

        when(createOrderFinalProductPortionUseCase.invoke(anyList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderProductTaxesUseCase.invoke(inputFinalProductTaxDTODataMock.getDataDefaultList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderRemovedPortionUseCase.invoke(inputRemovedPortionDTODataMock.getDataDefaultList()))
            .thenReturn(CompletableFuture.completedFuture(true));

        final Boolean result = createOrderFinalProductUseCase.invoke(
            inputFinalProductDTODataMock.getDataDefaultList(1L),
            1L,
            1L,
            1L,
            true
        ).join();

        Assertions.assertTrue(result);
    }
}
