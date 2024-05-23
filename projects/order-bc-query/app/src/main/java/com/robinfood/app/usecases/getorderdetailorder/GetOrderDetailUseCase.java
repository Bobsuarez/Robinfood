package com.robinfood.app.usecases.getorderdetailorder;

import com.robinfood.app.mappers.GetOrderDetailDeductionMapper;
import com.robinfood.app.mappers.GetOrderElectronicBillingMapper;
import com.robinfood.app.mappers.GetOrderThirdPartyMapper;
import com.robinfood.app.mappers.OrderCouponMappersKt;
import com.robinfood.app.usecases.getorderservicesdetails.IGetOrderServicesUseCase;
import com.robinfood.app.usecases.gettransactioninfo.IGetTransactionInfoUseCase;
import com.robinfood.app.usecases.getorderdetailbyids.IGetOrderDetailByIdsUseCase;
import com.robinfood.app.usecases.getorderdetaildiscountbyorderids.IGroupOrderDetailDiscountByOrderIdsUseCase;
import com.robinfood.app.usecases.getorderdetailfinalproduct.IGroupOrderDetailProductsUseCase;
import com.robinfood.app.usecases.getorderdetailpaymentmethodbyorderids.IGroupOrderDetailPaymentMethodsByOrderIds;
import com.robinfood.app.usecases.getorderdieductionbyfinalproductids.IGetOrderDeductionsByFinalProductIdsUseCase;
import com.robinfood.app.usecases.getorderdiscountbyfinalproductids.IGetOrderDiscountByFinalProductIdsUseCase;
import com.robinfood.app.usecases.getorderdiscountbyorderids.IGetOrderDiscountByOrderIdsUseCase;
import com.robinfood.app.usecases.getorderfiscalidentifierbytransactionidusecase.IGetOrderFiscalIdentifierByTransactionIdUseCase;
import com.robinfood.app.usecases.getorderfoodcoins.IGetOrderFoodCoinsUseCase;
import com.robinfood.app.usecases.getorderintegration.IGetOrderIntegrationUseCase;
import com.robinfood.app.usecases.getorderisintegration.IGetOrderIsIntegrationUseCase;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdsUseCase;
import com.robinfood.app.usecases.getorderproducttaxesbyfinalproductids.IGetOrderProductTaxesByFinalProductIdsUseCase;
import com.robinfood.app.usecases.getstatusbylistid.IGetStatusByListIdUseCase;
import com.robinfood.app.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import com.robinfood.core.dtos.CouponsDTO;
import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.dtos.GetOrderDetailDeductionDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductDTO;
import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.dtos.OrderIntegrationDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.OrderProductTaxDTO;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.dtos.response.orderhistory.GetOrderServicesDetailsDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.core.entities.PosResolutionsEntity;
import com.robinfood.repository.ordercoupons.IOrderCouponRepository;
import com.robinfood.repository.orderdeduction.IOrderDeductionRepository;
import com.robinfood.repository.orderelectronicbillings.IOrderElectronicBillingsRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orderpos.IOrderPosRepository;
import com.robinfood.repository.orders.IOrdersRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.robinfood.repository.orderthirdparties.IOrderThirdPartiesRepository;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;
import static com.robinfood.core.constants.GlobalConstants.BASIC_DATA_OBJECT_IN_JSON_PAYLOAD;


/**
 * Implementation of IGetOrderDetailOrderUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetOrderDetailUseCase implements IGetOrderDetailUseCase {

    private final IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;
    private final IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;
    private final IGetOrderDetailByIdsUseCase getOrderDetailByIdsUseCase;
    private final IGetOrderDiscountByOrderIdsUseCase getOrderDiscountByOrderIdsUseCase;
    private final IGetOrderDiscountByFinalProductIdsUseCase getOrderDiscountByFinalProductIdsUseCase;
    private final IGetOrderProductTaxesByFinalProductIdsUseCase getOrderProductTaxesByFinalProductIdsUseCase;
    private final IGetOrderIntegrationUseCase getOrderIntegrationUseCase;
    private final IGetOrderIsIntegrationUseCase getOrderIsIntegrationUseCase;
    private final IGetOrderServicesUseCase getOrderServicesUseCase;
    private final IGroupOrderDetailDiscountByOrderIdsUseCase groupDetailOrderOrderDiscountByOrderIdsUseCase;
    private final IGroupOrderDetailPaymentMethodsByOrderIds groupDetailOrderPaymentMethodsByOrderIds;
    private final IGroupOrderDetailProductsUseCase groupOrderFinalProductUseCase;
    private final IGetStatusByListIdUseCase getStatusByListIdUseCase;
    private final IGetOrderFiscalIdentifierByTransactionIdUseCase getOrderFiscalIdentifierByTransactionIdUseCase;
    private final IOrderFinalProductRepository orderFinalProductRepository;
    private final IOrdersRepository ordersRepository;
    private final IOrderPosRepository orderPosRepository;
    private final IOrderCouponRepository orderCouponRepository;
    private final IGetTransactionInfoUseCase getTransactionInfo;
    private final IGetOrderFoodCoinsUseCase orderFoodCoinsUseCase;
    private final IOrderDeductionRepository orderDeductionRepository;
    private final IGetOrderDeductionsByFinalProductIdsUseCase getOrderDeductionsByFinalProductIdsUseCase;
    private final IOrderThirdPartiesRepository orderThirdPartiesRepository;
    private final IOrderElectronicBillingsRepository orderElectronicBillingsRepository;


    @Override
    public List<GetOrderDetailDTO> invoke(List<Long> orderIds) {

        log.info("Starting process to get order details by orderIds: {}", orderIds);

        final Iterable<OrderEntity> orderEntities = ordersRepository.findAllById(orderIds);

        if (CollectionsKt.toList(orderEntities).isEmpty()) {
            log.error("Orders with ids: [{}] were not found", orderIds);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Orders with ids: " + orderIds + " were not found."
            );
        }

        Stream<OrderEntity> targetStreamOrderEntities = StreamSupport.stream(
                orderEntities.spliterator(), false);

        final List<OrderStatusDTO> ordersStatus = getStatusByListIdUseCase.invoke(
                targetStreamOrderEntities.map(OrderEntity::getStatusId).collect(Collectors.toList())
        );

        final List<OrderDetailDTO> orderDetailDTOS = getOrderDetailByIdsUseCase.invoke(orderIds);

        final List<GetOrderDetailDTO> orderDetailOrderDTOS = new ArrayList<>();

        final Map<Long, BigDecimal> orderDetailDeductions =
                orderDeductionRepository.findAllByOrderIdIsIn(orderIds)
                        .stream()
                        .map(GetOrderDetailDeductionMapper::orderDeductionEntityToGetOrderDetailDeductionDTO)
                        .collect(Collectors.toMap(GetOrderDetailDeductionDTO::getId,
                                GetOrderDetailDeductionDTO::getValue));

        final List<OrderFinalProductEntity> orderFinalProductEntitiesByOrderId = orderFinalProductRepository
                .findAllByOrderIdIn(orderIds);

        final List<Long> orderFinalProductIds = CollectionsKt.map(
                orderFinalProductEntitiesByOrderId, OrderFinalProductEntity::getId
        );

        final List<OrderDiscountDTO> orderDiscountByProductIdsDTOS = getOrderDiscountByFinalProductIdsUseCase
                .invoke(orderFinalProductIds);

        final List<OrderProductTaxDTO> orderProductTaxDTOS = getOrderProductTaxesByFinalProductIdsUseCase
                .invoke(orderFinalProductIds);

        final List<OrderDeductionFinalProductDTO> orderDeductionFinalProductDTOS =
                getOrderDeductionsByFinalProductIdsUseCase.invoke(orderFinalProductIds);

        final Map<Long, List<GetOrderDetailFinalProductDTO>> getOrderFinalProductDTOS =
                groupOrderFinalProductUseCase.invoke(
                        orderIds,
                        orderFinalProductIds,
                        orderDiscountByProductIdsDTOS,
                        orderProductTaxDTOS,
                        orderDeductionFinalProductDTOS
                );

        final List<OrderDiscountDTO> orderDiscountDTOS = getOrderDiscountByOrderIdsUseCase.invoke(
                orderIds);

        final Map<Long, List<GetOrderDetailDiscountDTO>> getOrderDiscountDTOByOrderIds =
                groupDetailOrderOrderDiscountByOrderIdsUseCase
                        .invoke(orderDiscountDTOS);

        for (OrderEntity orderEntity : orderEntities) {

            Map<String,String> transactionInfo;
            transactionInfo = getTransactionInfo.invoke(orderEntity.getTransactionId());

            final OrderStatusDTO status = CollectionsKt.first(ordersStatus,
                    orderStatusDTO -> orderStatusDTO.getId().equals(orderEntity.getStatusId()));

            final Boolean OrderIsIntegration = getOrderIsIntegrationUseCase.invoke(
                    orderEntity.getId());

            OrderIntegrationDTO orderIntegrationDTO = new OrderIntegrationDTO();
            if (Boolean.TRUE.equals(OrderIsIntegration)) {
                orderIntegrationDTO = getOrderIntegrationUseCase.invoke(orderEntity.getId());
            }

            final List<UserDataDTO> userDataDTOS = getUserDataByOrderIdsUseCase.invoke(orderIds);

            final UserDataDTO orderDetailUser = userDataDTOS.stream()
                    .filter(userDataDTO -> orderEntity.getId().equals(userDataDTO.getOrderId()))
                    .findAny()
                    .orElse(null);

            final OrderDetailDTO orderDetailNote = orderDetailDTOS.stream()
                    .filter(orderDetailDTO -> orderEntity.getId().equals(orderDetailDTO.getId()))
                    .findAny()
                    .orElse(null);

            final List<CouponsDTO> coupons = CollectionsKt.map(
                    CollectionsKt.map(
                            orderCouponRepository.findOrderCouponEntityByTransactionId(orderEntity.getTransactionId()),
                            OrderCouponMappersKt::toResponseOrderDiscountDTO
                    ),
                    OrderCouponMappersKt::toResponseCouponsDTO
            );

            final BigDecimal foodcoins = orderFoodCoinsUseCase.invoke(orderEntity.getId());

            final List<OrderPaymentDTO> orderPaymentDTOS = getOrderPaymentByOrderIdsUseCase.invoke(
                    orderIds);

            final Map<Long, List<GetOrderDetailPaymentMethodDTO>> getOrderPaymentDTOByOrderIds =
                    groupDetailOrderPaymentMethodsByOrderIds
                            .invoke(orderPaymentDTOS);

            final OrderFiscalIdentifierDTO orderFiscalIdentifierDTO = getOrderFiscalIdentifierByTransactionIdUseCase
                    .invoke(orderEntity.getTransactionId());

            final String prefix = orderPosRepository
                    .findByPosIdAndCurrent(orderEntity.getPosId(), DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT)
                    .stream().map(PosResolutionsEntity::getPrefix)
                    .findFirst()
                    .orElse(DEFAULT_STRING_VALUE);

            final List<GetOrderServicesDetailsDTO> getOrderServicesDetailsDTOList = getOrderServicesUseCase
                    .invoke(orderEntity.getId());

            final OrderThirdPartyDTO orderThirdPartyDTO = orderThirdPartiesRepository.findByOrderId(orderEntity.getId())
                    .map(GetOrderThirdPartyMapper::orderThirdPartyEntityToOrderThirdPartyDTO)
                    .orElse(null);

            DataElectronicBillingDTO dataElectronicBillingDTO =
                    orderElectronicBillingsRepository.findByOrderIdAndStatusCodeAccepted(orderEntity.getId(),
                                    BASIC_DATA_OBJECT_IN_JSON_PAYLOAD, HttpStatus.ACCEPTED.value())
                            .map(GetOrderElectronicBillingMapper::orderElectronicBillingEntityToDataElectronicBillingDTO)
                            .orElse(null);

            ElectronicBillDTO electronicBillDTO = Optional.ofNullable(orderThirdPartyDTO)
                    .map(orderThirdParty -> ElectronicBillDTO.builder().orderThirdParty(orderThirdParty)
                            .orderElectronicBilling(dataElectronicBillingDTO).build())
                    .orElseGet(() -> Optional.ofNullable(dataElectronicBillingDTO)
                            .map(dataElectronicBilling -> ElectronicBillDTO.builder()
                                    .orderElectronicBilling(dataElectronicBilling).orderThirdParty(orderThirdPartyDTO)
                                    .build())
                            .orElse(null));

            assert orderDetailNote != null;
            orderDetailOrderDTOS.add(
                    new GetOrderDetailDTO(
                            orderEntity.getBrandId(),
                            orderEntity.getBrandName(),
                            orderEntity.getCompanyId(),
                            orderEntity.getCo2Total(),
                            coupons,
                            orderEntity.getCurrency(),
                            orderEntity.getDiscounts(),
                            getOrderDiscountDTOByOrderIds.get(orderEntity.getId()),
                            orderDetailDeductions.getOrDefault(orderEntity.getId(), BigDecimal.ZERO),
                            orderEntity.getDeliveryTypeId(),
                            electronicBillDTO,
                            Long.parseLong(transactionInfo.get("FLOW_ID")),
                            foodcoins,
                            orderEntity.getId(),
                            orderEntity.getOrderInvoiceNumber(),
                            orderDetailNote.getNotes(),
                            orderEntity.getOperationDate(),
                            orderEntity.getLocalTime(),
                            orderEntity.getOriginId(),
                            orderEntity.getOriginName(),
                            orderEntity.getOrderNumber(),
                            orderEntity.getUuid(),
                            orderIntegrationDTO.getIntegrationId(),
                            orderIntegrationDTO.getUserName(),
                            OrderIsIntegration,
                            orderIntegrationDTO.getCode(),
                            orderFiscalIdentifierDTO,
                            getOrderPaymentDTOByOrderIds.get(orderEntity.getId()),
                            orderEntity.getPosId(),
                            prefix,
                            orderEntity.getPrinted(),
                            getOrderFinalProductDTOS.get(orderEntity.getId()),
                            getOrderServicesDetailsDTOList,
                            status.getId(),
                            status.getName(),
                            orderEntity.getStoreId(),
                            orderEntity.getStoreName(),
                            orderEntity.getSubtotal(),
                            orderEntity.getTaxes(),
                            orderEntity.getTotal(),
                            orderEntity.getTransactionId(),
                            transactionInfo.get("TRANSACTION_UUID"),
                            orderEntity.getUid(),
                            orderDetailUser
                    )
            );
        }

        return orderDetailOrderDTOS;

    }
}
