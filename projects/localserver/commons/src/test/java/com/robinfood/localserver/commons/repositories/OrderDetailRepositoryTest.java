package com.robinfood.localserver.commons.repositories;

import com.robinfood.localserver.commons.dtos.http.ApiResponseOrderOrQueriesDTO;
import com.robinfood.localserver.commons.dtos.orders.CouponsDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderBuyerDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailChangedPortionDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailDiscountDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailPaymentMethodDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailProductDiscountDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailProductGroupDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailProductGroupPortionDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailProductTaxDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailRemovedPortionDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailUserDTO;
import com.robinfood.localserver.commons.dtos.orders.ServiceDTO;
import com.robinfood.localserver.commons.entities.http.ApiResponseOrderOrQueriesEntity;
import com.robinfood.localserver.commons.entities.orders.CouponEntity;
import com.robinfood.localserver.commons.entities.orders.OrderBuyerEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailChangedPortionEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailDiscountEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailPaymentMethodEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailProductDiscountEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailProductEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailProductGroupEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailProductGroupPortionEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailProductTaxEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailRemovedPortionEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailUserEntity;
import com.robinfood.localserver.commons.entities.orders.ServiceEntity;
import com.robinfood.localserver.commons.mappers.orders.IApiResponseOrderOrQueriesMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailRepositoryTest {

    @InjectMocks
    OrderDetailRepository orderDetailRepository;

    @Mock
    private IApiResponseOrderOrQueriesMapper apiResponseOrderOrQueriesMapper;

    @Mock
    private IOrderDetailDataSource orderDetailDataSource;

    private String token = "token";

    private final List<OrderDetailPaymentMethodEntity> orderDetailPaymentMethodEntityS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailPaymentMethodEntity(
                            0.0,
                            8L,
                            "cash",
                            0L,
                            29351.8516,
                            2348.1484,
                            BigDecimal.valueOf(31700.0)
                    )
            )
    );

    private final OrderDetailChangedPortionEntity orderDetailChangedPortionEntity = new OrderDetailChangedPortionEntity(
            1L,
            "Portion",
            1L,
            "12345",
            2L,
            1.0
    );


    private final List<OrderDetailProductGroupPortionEntity> orderDetailProductGroupPortionEntityS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionEntity(
                            false,
                            orderDetailChangedPortionEntity,
                            BigDecimal.ZERO,
                            1,
                            1L,
                            "Chorizo.",
                            0.0,
                            1L,
                            8,
                            "176",
                            1L,
                            3.0
                    )
            )
    );

    private final List<OrderDetailRemovedPortionEntity> orderDetailRemovedPortionEntityS = Collections.singletonList(
            new OrderDetailRemovedPortionEntity(
                    1L,
                    "Rice"
            )
    );

    private final List<OrderDetailProductGroupEntity> orderDetailProductGroupEntityS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupEntity(
                            10L,
                            "¿QUE QUIERES QUITAR DE TU PLATO?",
                            orderDetailProductGroupPortionEntityS,
                            orderDetailRemovedPortionEntityS,
                            "1760"
                    )
            )
    );

    private final List<OrderDetailProductTaxEntity> orderDetailProductTaxEntityS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductTaxEntity(
                            1L,
                            3928865L,
                            733.3334,
                            null,
                            null,
                            8.0
                    )
            )
    );

    private final List<OrderDetailProductDiscountEntity> orderDetailProductDiscountEntityS = Collections.singletonList(
            new OrderDetailProductDiscountEntity(
                    1L,
                    1L,
                    new BigDecimal(0)
            )
    );

    private final List<OrderDetailProductEntity> orderDetailProductEntityS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductEntity(
                            1L,
                            1L,
                            100.0,
                            1L,
                            "Pecado Natural",
                            1L,
                            "Category",
                            new BigDecimal(0),
                            new BigDecimal(0),
                            new BigDecimal(0),
                            orderDetailProductDiscountEntityS,
                            1L,
                            orderDetailProductGroupEntityS,
                            1L,
                            "urlimage",
                            1L,
                            null,
                            "Pecado",
                            1,
                            1L,
                            "mediano",
                            "sku",
                            orderDetailProductTaxEntityS,
                            100.0,
                            100.0
                    )
            )
    );

    private final OrderDetailUserEntity orderDetailUserEntity = new OrderDetailUserEntity(
            "bruno@email",
            "bruno",
            1L,
            "jason",
            0L,
            "300"
    );

    private final List<OrderDetailDiscountEntity> orderDetailDiscountEntityS = new ArrayList<>(Collections.singletonList(
            new OrderDetailDiscountEntity(
                    1L,
                    1L,
                    new BigDecimal(0)
            )
    )
    );

    List<CouponEntity> couponEntity = new ArrayList<>(
            Collections.singletonList(
                    new CouponEntity(
                            "COUPONS",
                            new BigDecimal(100)
                    )
            )
    );

    private final List<ServiceEntity> serviceEntities = Collections.singletonList(
            new ServiceEntity(
                    159688L,
                    "Domicilios",
                    1L,
                    1000.00

            )
    );

    private final List<OrderDetailEntity> orderDetailEntityS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailEntity(
                            new OrderBuyerEntity(
                                    "365846738"
                            ),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            12345L,
                            "cop",
                            1L,
                            couponEntity,
                            new BigDecimal(0),
                            new BigDecimal(0),
                            orderDetailDiscountEntityS,
                            1L,
                            "",
                            "Notes",
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            "test 1",
                            "12345",
                            true,
                            "e05f1b1f-d82f-4bdd-9ecb-8638be9eca24",
                            orderDetailPaymentMethodEntityS,
                            167L,
                            false,
                            orderDetailProductEntityS,
                            serviceEntities,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            BigDecimal.valueOf(8900.0),
                            1L,
                            "1234",
                            orderDetailUserEntity,
                            Collections.singletonList(orderDetailUserEntity)
                    )
            )
    );


    ApiResponseOrderOrQueriesEntity apiResponseOrderOrQueriesEntity = ApiResponseOrderOrQueriesEntity
            .builder()
            .data(orderDetailEntityS)
            .locale("CO")
            .message("OK")
            .build();


    private final List<OrderDetailPaymentMethodDTO> orderDetailPaymentMethodDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailPaymentMethodDTO(
                            0.0,
                            8L,
                            "cash",
                            0L,
                            29351.8516,
                            2348.1484,
                            BigDecimal.valueOf(31700.0)
                    )
            )
    );

    private final OrderDetailChangedPortionDTO orderDetailChangedPortionDTO = new OrderDetailChangedPortionDTO(
            1L,
            "Portion",
            1L,
            "12345",
            2L,
            1.0
    );


    private final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            false,
                            orderDetailChangedPortionDTO,
                            BigDecimal.ZERO,
                            1L,
                            "Chorizo",
                            1L,
                            0.0,
                            1,
                            0,
                            "176",
                            1L,
                            3.0
                    )
            )
    );

    private final List<OrderDetailRemovedPortionDTO> orderDetailRemovedPortionDTOS = Collections.singletonList(
            new OrderDetailRemovedPortionDTO(
                    1L,
                    "Rice"
            )
    );

    private final List<OrderDetailProductGroupDTO> orderDetailProductGroupDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupDTO(
                            10L,
                            "¿QUE QUIERES QUITAR DE TU PLATO?",
                            orderDetailProductGroupPortionDTOS,
                            orderDetailRemovedPortionDTOS,
                            "1760"
                    )
            )
    );

    private final List<OrderDetailProductTaxDTO> orderDetailProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductTaxDTO(
                            1L,
                            3928865L,
                            733.3334,
                            null,
                            null,
                            8.0
                    )
            )
    );

    private final List<OrderDetailProductDiscountDTO> orderDetailProductDiscountDTOS = Collections.singletonList(
            new OrderDetailProductDiscountDTO(
                    1L,
                    1L,
                    new BigDecimal(0)
            )
    );

    private final List<OrderDetailProductDTO> orderDetailProductDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "article",
                            1L,
                            100.0,
                            1L,
                            "Pecado Natural",
                            1L,
                            "Category",
                            new BigDecimal(0),
                            new BigDecimal(0),
                            new BigDecimal(0),
                            orderDetailProductDiscountDTOS,
                            1L,
                            orderDetailProductGroupDTOS,
                            1L,
                            "urlimage",
                            1L,
                            null,
                            "Pecado",
                            1,
                            1L,
                            "mediano",
                            "sku",
                            orderDetailProductTaxDTOS,
                            100.0,
                            100.0
                    )
            )
    );

    private final OrderDetailUserDTO orderDetailUserDTO = new OrderDetailUserDTO(
            "bruno@email",
            "bruno",
            1L,
            "jason",
            0L,
            "300"
    );

    private final List<OrderDetailDiscountDTO> orderDetailDiscountDTOS = new ArrayList<>(Collections.singletonList(
            new OrderDetailDiscountDTO(
                    1L,
                    1L,
                    new BigDecimal(0)
            )
    )
    );

    List<CouponsDTO> couponDTO = new ArrayList<>(
            Collections.singletonList(
                    new CouponsDTO(
                            "COUPONS",
                            new BigDecimal(100)
                    )
            )
    );

    private final List<ServiceDTO> serviceDTOS = Collections.singletonList(
            new ServiceDTO(
                    159688L,
                    "Domicilios",
                    1L,
                    1000.00

            )
    );

    private final List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailDTO(
                            new OrderBuyerDTO(
                                    "365846738"
                            ),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            "cop",
                            couponDTO,
                            12345L,
                            new BigDecimal(0),
                            new BigDecimal(0),
                            orderDetailDiscountDTOS,
                            1L,
                            1L,
                            "",
                            "Notes",
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            "test 1",
                            "12345",
                            true,
                            "529177c8-77d9-11ed-a1eb-0242ac120002",
                            orderDetailPaymentMethodDTOS,
                            167L,
                            false,
                            orderDetailProductDTOS,
                            serviceDTOS,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            BigDecimal.valueOf(8900.0),
                            1L,
                            "1234",
                            orderDetailUserDTO,
                            Collections.singletonList(orderDetailUserDTO)
                    )
            )
    );

    ApiResponseOrderOrQueriesDTO apiResponseOrderOrQueriesDTO = ApiResponseOrderOrQueriesDTO
            .builder()
            .data(orderDetailDTOS)
            .locale("CO")
            .message("OK")
            .build();


    @Test
    void test_When_Get_Detail_Order_Is_Success() {

        when(orderDetailDataSource.invoke(token, 12345L)).thenReturn(
                apiResponseOrderOrQueriesEntity
        );

        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        orderDetailDTOList.add(orderDetailDTOS.get(0));

        when(apiResponseOrderOrQueriesMapper.apiResponseOrderOrQueriesEntityToApiResponseOrderOrQueriesDTO(
                apiResponseOrderOrQueriesEntity)).thenReturn(apiResponseOrderOrQueriesDTO);

        ApiResponseOrderOrQueriesDTO apiResponseOrderOrQueriesDTO = orderDetailRepository
                .invoke(token, 12345L);

        assertEquals(apiResponseOrderOrQueriesDTO.getData().get(0).getId(),
                apiResponseOrderOrQueriesEntity.getData().get(0).getId());

    }

}
