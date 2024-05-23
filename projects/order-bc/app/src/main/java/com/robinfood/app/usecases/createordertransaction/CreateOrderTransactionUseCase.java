package com.robinfood.app.usecases.createordertransaction;

import com.robinfood.app.mappers.OrderDiscountMappers;
import com.robinfood.app.services.orderdiscarded.IOrderDiscardedService;
import com.robinfood.app.usecases.createdorderdeduction.ICreateOrderDeductionsUseCase;
import com.robinfood.app.usecases.createfoodcoins.ICreateOrderFoodCoinsUseCase;
import com.robinfood.app.usecases.createorder.ICreateOrderUseCase;
import com.robinfood.app.usecases.createorderaddress.ICreateOrderAddressUseCase;
import com.robinfood.app.usecases.createorderbrandhistory.ICreateOrderBrandHistoryUseCase;
import com.robinfood.app.usecases.createordercoupon.ICreateOrderCouponUseCase;
import com.robinfood.app.usecases.createorderdetail.ICreateOrderDetailUseCase;
import com.robinfood.app.usecases.createorderdevice.ICreateOrderDeviceUseCase;
import com.robinfood.app.usecases.createorderdiscount.ICreateOrderDiscountUseCase;
import com.robinfood.app.usecases.createorderdiscountproduct.ICreateOrderDiscountProductUseCase;
import com.robinfood.app.usecases.createorderexternaldiscount.ICreateOrderExternalDiscountUseCase;
import com.robinfood.app.usecases.createorderfiscalidentifier.ICreateOrderFiscalIdentifierUseCase;
import com.robinfood.app.usecases.createorderflag.ICreateOrderFlagUseCase;
import com.robinfood.app.usecases.createorderflagcorporate.ICreateOrderFlagCorporateUseCase;
import com.robinfood.app.usecases.createorderflagintegrations.ICreateOrderFlagIntegrationUseCase;
import com.robinfood.app.usecases.createorderflagsubmarine.ICreateOrderFlagSubmarineUseCase;
import com.robinfood.app.usecases.createorderflagtogo.ICreateOrderFlagTogoUseCase;
import com.robinfood.app.usecases.createorderhistory.ICreateOrderHistoryUseCase;
import com.robinfood.app.usecases.createorderintegrations.ICreateOrderIntegrationUseCase;
import com.robinfood.app.usecases.createorderpayments.ICreateOrderPaymentUseCase;
import com.robinfood.app.usecases.createorderuserdata.ICreateOrderUserDataUseCase;
import com.robinfood.app.usecases.createpayments.ICreatePaymentsUseCase;
import com.robinfood.app.usecases.createtransaction.ICreateTransactionUseCase;
import com.robinfood.app.usecases.flushordertransaction.IFlushOrderTransactionUseCase;
import com.robinfood.app.usecases.getsumpaymentmethos.IGetSumPaymentMethodsUseCase;
import com.robinfood.app.usecases.saveorderservices.ISaveOrderServiceUseCase;
import com.robinfood.app.usecases.sendordercreatedtoqueue.ISendOrderCreatedToQueueUseCase;
import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.dtos.OrderCouponDTO;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.dtos.request.order.DiscountDTO;
import com.robinfood.core.dtos.request.order.IntermediateOrderFlagDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.order.OrderFlagCorporateDTO;
import com.robinfood.core.dtos.request.order.OrderFlagIntegrationDTO;
import com.robinfood.core.dtos.request.order.OrderFlagPitsDTO;
import com.robinfood.core.dtos.request.order.OrderFlagSubmarineDTO;
import com.robinfood.core.dtos.request.order.OrderFlagTogoDTO;
import com.robinfood.core.dtos.request.order.OrderIntegrationDTO;
import com.robinfood.core.dtos.request.transaction.AddressDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import com.robinfood.core.dtos.request.transaction.RequestTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestUserDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.dtos.response.order.ResponseOrderDiscountDTO;
import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;
import com.robinfood.core.dtos.response.transaction.ResponseTransactionDTO;
import com.robinfood.core.entities.OrderDiscountEntity;
import com.robinfood.core.exceptions.CannotDivideByZeroException;
import com.robinfood.repository.orderdiscount.IOrderDiscountRepository;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.CORPORATE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_EMPTY_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DELIVERY_TYPE_EXTERNAL_ID;
import static com.robinfood.core.constants.GlobalConstants.DELIVERY_TYPE_INTERNAL_ID;
import static com.robinfood.core.constants.GlobalConstants.INTEGRATIONS;
import static com.robinfood.core.constants.GlobalConstants.PITS;
import static com.robinfood.core.constants.GlobalConstants.SUBMARINE;
import static com.robinfood.core.constants.GlobalConstants.TOGO;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ICreateOrderTransactionUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
@Transactional
public class CreateOrderTransactionUseCase implements ICreateOrderTransactionUseCase {

    private final ICreateOrderAddressUseCase createOrderAddressUseCase;
    private final ICreateOrderBrandHistoryUseCase createOrderBrandHistoryUseCase;
    private final ICreateOrderCouponUseCase createOrderCouponUseCase;
    private final ICreateOrderDetailUseCase createOrderDetailUseCase;
    private final ICreateOrderDeviceUseCase createOrderDevice;
    private final ICreateOrderDiscountUseCase createOrderDiscountUseCase;
    private final ICreateOrderDiscountProductUseCase createOrderDiscountProductUseCase;
    private final ICreateOrderExternalDiscountUseCase createOrderExternalDiscountUseCase;
    private final ICreateOrderFlagCorporateUseCase createOrderFlagCorporateUseCase;
    private final ICreateOrderFlagIntegrationUseCase createOrderFlagIntegrationsUseCase;
    private final ICreateOrderFlagSubmarineUseCase createOrderFlagSubmarineUseCase;
    private final ICreateOrderFlagTogoUseCase createOrderFlagTogoUseCase;
    private final ICreateOrderFlagUseCase createOrderFlagUseCase;
    private final ICreateOrderHistoryUseCase createOrderHistoryUseCase;
    private final ICreateOrderIntegrationUseCase createOrderIntegrationUseCase;
    private final ICreateOrderPaymentUseCase createOrderPaymentUseCase;
    private final ICreateOrderUseCase createOrderUseCase;
    private final ICreateOrderUserDataUseCase createOrderUserDataUseCase;
    private final ICreateOrderFiscalIdentifierUseCase createOrderFiscalIdentifierUseCase;
    private final ICreatePaymentsUseCase createPaymentsUseCase;
    private final ICreateTransactionUseCase createTransactionUseCase;
    private final IFlushOrderTransactionUseCase flushOrderTransactionUseCase;
    private final IGetSumPaymentMethodsUseCase getIsPaymentTotalOrder;
    private final IOrderDiscountRepository orderDiscountRepository;
    private final ICreateOrderDeductionsUseCase createOrderDeductions;
    private final ISendOrderCreatedToQueueUseCase sendOrderCreatedToQueueUseCase;
    private final ICreateOrderFoodCoinsUseCase createOrderFoodCoinsUseCase;
    private final ISaveOrderServiceUseCase saveOrderServiceUseCase;
    private final IOrderDiscardedService orderDiscardedService;

    @Override
    public ResponseCreatedOrderTransactionDTO invoke(
            RequestOrderTransactionDTO orderTransactionDTO
    ) throws CannotDivideByZeroException {

        log.info("Starting process to create order transaction with request: {}",
                objectToJson(orderTransactionDTO));

        final RequestUserDTO userDataDTO = orderTransactionDTO.getUser();
        final List<OrderDTO> orderDTOS = orderTransactionDTO.getOrders();

        // Save transaction and get transactionId
        final RequestTransactionDTO transactionDTO = new RequestTransactionDTO(
                orderTransactionDTO.getId(),
                orderTransactionDTO.getFlowId(),
                orderTransactionDTO.getPaid(),
                orderTransactionDTO.getTotal(),
                orderTransactionDTO.getUuid().toString(),
                userDataDTO.getId()
        );

        final CompletableFuture<Void> resultFlushOrderTransactionUseCase = flushOrderTransactionUseCase
                .invoke(orderTransactionDTO);

        final ResponseTransactionDTO responseTransactionDTO = createTransactionUseCase.invoke(
                transactionDTO).join();

        List<OrderCouponDTO> couponsApplied = orderTransactionDTO.getCouponResponseEntities();

        responseTransactionDTO.setCoupons(couponsApplied);

        final Long transactionId = responseTransactionDTO.getId();
        final List<RequestPaymentMethodDTO> requestPaymentMethodDTOList = orderTransactionDTO.getPaymentMethods();
        requestPaymentMethodDTOList.addAll(orderTransactionDTO.getPaymentsPaid());

        //Save coupons
        if (Objects.nonNull(orderTransactionDTO.getCouponResponseEntities())) {
            createOrderCouponUseCase.invoke(orderTransactionDTO);
        }

        // Save transaction payments
        final CompletableFuture<Boolean> resultCreatePaymentsUseCase = createPaymentsUseCase.invoke(
                requestPaymentMethodDTOList,
                transactionId
        );

        // getSumPaymentMethods
        final Double totalPaymentMethods = getIsPaymentTotalOrder.invoke(
                orderTransactionDTO.getPaymentMethods());

        AddressDTO address = orderTransactionDTO.getAddress();

        for (OrderDTO item : orderTransactionDTO.getOrders()) {
            orderDiscardedService
                    .validateAndUpdate(item.getUuid());
        }

        // Save orders
        final List<ResponseCreatedOrderDTO> responseCreatedOrderDTOS = createOrderUseCase
                .invoke(orderTransactionDTO, orderDTOS, transactionId, totalPaymentMethods);

        if (Objects.nonNull(orderTransactionDTO.getAddress())) {
            //Save address
            responseCreatedOrderDTOS.forEach(responseCreatedOrderDTO -> createOrderAddressUseCase.invoke(
                    getOrderAddressDTO(responseTransactionDTO, address, responseCreatedOrderDTO)
            ));
        }

        //Fiscal Identifier
        final OrderFiscalIdentifierDTO orderFiscalIdentifierDTO = orderTransactionDTO.getOrderFiscalIdentifier();

        if (Objects.nonNull(orderFiscalIdentifierDTO)) {
            createOrderFiscalIdentifierUseCase.invoke(orderFiscalIdentifierDTO, transactionId);
        }

        final List<Long> orderIds = CollectionsKt.map(responseCreatedOrderDTOS,
                ResponseCreatedOrderDTO::getId);

        final CompletableFuture<Boolean> resultCreateOrderDetailUseCase = createOrderDetailUseCase.invoke(
                orderDTOS,
                responseCreatedOrderDTOS
        );

        final List<RequestUserDTO> userDTOList = new ArrayList<>();
        final List<OrderIntegrationDTO> orderIntegrationDTOList = new ArrayList<>();
        final List<OrderFlagSubmarineDTO> orderFlagSubmarineDTOList = new ArrayList<>();
        final List<OrderFlagTogoDTO> orderFlagTogoDTOList = new ArrayList<>();
        final List<OrderFlagCorporateDTO> orderFlagCorporateDTOList = new ArrayList<>();
        final List<OrderFlagIntegrationDTO> orderFlagIntegrationDTOList = new ArrayList<>();
        final List<IntermediateOrderFlagDTO> intermediateOrderFlagDTOList = new ArrayList<>();

        for (int i = 0; i < responseCreatedOrderDTOS.size(); i++) {

            final ResponseCreatedOrderDTO responseCreatedOrderDTO = responseCreatedOrderDTOS.get(i);
            final Long orderId = responseCreatedOrderDTO.getId();
            OrderDTO orderDTO = orderDTOS.get(i);

            final CompletableFuture<Boolean> resultCreateOrderHistory = createOrderHistoryUseCase.invoke(
                    orderDTO.getNotes(),
                    orderId,
                    orderDTO.getTotal(),
                    orderDTO.getPaid(),
                    orderTransactionDTO,
                    totalPaymentMethods,
                    userDataDTO.getId()
            );

            final CompletableFuture<Boolean> resultCreateOrderBrandHistory = createOrderBrandHistoryUseCase.invoke(
                    orderDTO.getFinalProducts(),
                    orderId,
                    orderDTO.getTotal(),
                    orderDTO.getPaid(),
                    orderTransactionDTO,
                    totalPaymentMethods,
                    userDataDTO.getId()
            );

            userDTOList.add(
                    RequestUserDTO.builder()
                            .email(userDataDTO.getEmail())
                            .id(userDataDTO.getId())
                            .lastName(userDataDTO.getLastName())
                            .mobile(userDataDTO.getMobile())
                            .name(userDataDTO.getName())
                            .orderId(orderId)
                            .phoneCode(userDataDTO.getPhoneCode())
                            .storeId(orderDTO.getStore().getId())
                            .build()
            );

            String fullNameUser = orderTransactionDTO.getUser().getFullName();

            if (DELIVERY_TYPE_INTERNAL_ID.equals(orderDTO.getDeliveryTypeId()) &&
                Objects.nonNull(orderTransactionDTO.getDelivery())) {
                fullNameUser = orderTransactionDTO.getDelivery().getUserName();
            }

            final List<Long> deliveryTypes = getDeliveryTypesIds();

            if (deliveryTypes.contains(orderDTO.getDeliveryTypeId()) &&
                    Objects.nonNull(orderTransactionDTO.getDelivery())) {

                orderIntegrationDTOList.add(new OrderIntegrationDTO(
                        orderTransactionDTO.getDelivery().getAddressCity(),
                        orderTransactionDTO.getDelivery().getAddressDescription(),
                        Date.valueOf(orderTransactionDTO.getDelivery().getArrivalDate()),
                        Time.valueOf(orderTransactionDTO.getDelivery().getArrivalTime()),
                        orderDTO.getBrand().getId(),
                        orderDTO.getBrand().getName(),
                        orderTransactionDTO.getDelivery().getCode(),
                        orderTransactionDTO.getDelivery().getIntegrationId(),
                        orderTransactionDTO.getDelivery().getNotes(),
                        orderId,
                        orderDTO.getOrigin().getId(),
                        orderDTO.getOrigin().getName(),
                        orderTransactionDTO.getDelivery().getOrderType(),
                        orderTransactionDTO.getDelivery().getPaymentMethod(),
                        orderDTO.getStore().getId(),
                        orderDTO.getStore().getName(),
                        orderTransactionDTO.getDelivery().getTotalDelivery(),
                        CollectionsKt.sumByDouble(orderDTO.getDiscounts(), DiscountDTO::getValue),
                        orderTransactionDTO.getTotal(),
                        orderTransactionDTO.getDelivery().getTotalTip(),
                        fullNameUser,
                        orderTransactionDTO.getUser().getMobile()
                ));

                if (orderTransactionDTO.getDelivery().getTotalDiscount() > DEFAULT_DOUBLE_EMPTY_VALUE) {
                    createOrderExternalDiscountUseCase
                            .invoke(orderTransactionDTO.getDelivery(), orderId);
                }

                final OrderFlagIntegrationDTO orderFlagIntegrationDTO = new OrderFlagIntegrationDTO(
                        null,
                        orderId
                );

                orderFlagIntegrationDTOList.add(orderFlagIntegrationDTO);
                intermediateOrderFlagDTOList.add(
                        new IntermediateOrderFlagDTO(INTEGRATIONS, null, orderId)
                );
            }

            if (orderDTO.getFlags() != null) {
                final OrderFlagSubmarineDTO orderFlagSubmarineDTO = orderDTO.getFlags()
                        .getSubmarine();
                if (orderFlagSubmarineDTO != null && orderFlagSubmarineDTO.getIsActive()) {
                    orderFlagSubmarineDTO.setOrderId(orderId);
                    orderFlagSubmarineDTOList.add(orderFlagSubmarineDTO);
                    intermediateOrderFlagDTOList.add(
                            new IntermediateOrderFlagDTO(SUBMARINE, null, orderId)
                    );
                }

                final OrderFlagPitsDTO orderFlagPitsDTO = orderDTO.getFlags().getPits();
                if (orderFlagPitsDTO != null && orderFlagPitsDTO.getIsActive()) {
                    orderFlagPitsDTO.setOrderId(orderId);
                    intermediateOrderFlagDTOList.add(
                            new IntermediateOrderFlagDTO(PITS, null, orderId));
                }

                final OrderFlagTogoDTO orderFlagTogoDTO = orderDTO.getFlags().getTogo();
                if (orderFlagTogoDTO != null && orderFlagTogoDTO.getIsActive()) {
                    orderFlagTogoDTO.setOrderId(orderId);
                    orderFlagTogoDTOList.add(orderFlagTogoDTO);
                    intermediateOrderFlagDTOList.add(
                            new IntermediateOrderFlagDTO(TOGO, null, orderId));
                }

                final OrderFlagCorporateDTO orderFlagCorporateDTO = orderDTO.getFlags()
                        .getCorporate();
                if (orderFlagCorporateDTO != null && orderFlagCorporateDTO.getIsActive()) {
                    orderFlagCorporateDTO.setOrderId(orderId);
                    orderFlagCorporateDTOList.add(orderFlagCorporateDTO);
                    intermediateOrderFlagDTOList.add(
                            new IntermediateOrderFlagDTO(CORPORATE, null, orderId)
                    );
                }
            }

            CompletableFuture.allOf(
                    resultCreateOrderHistory,
                    resultCreateOrderBrandHistory
            ).join();
        }

        final CompletableFuture<Boolean> resultCreateOrderUserData = createOrderUserDataUseCase.invoke(
                userDTOList);

        final CompletableFuture<Boolean> resultCreateOrderIntegrations = createOrderIntegrationUseCase
                .invoke(orderIntegrationDTOList);

        final CompletableFuture<Boolean> resultCreateOrderFlagSubmarines = createOrderFlagSubmarineUseCase
                .invoke(orderFlagSubmarineDTOList);

        final CompletableFuture<Boolean> resultCreateOrderFlagTogos = createOrderFlagTogoUseCase
                .invoke(orderFlagTogoDTOList);

        final CompletableFuture<Boolean> resultCreateOrderFlagCorporates = createOrderFlagCorporateUseCase
                .invoke(orderFlagCorporateDTOList);

        final CompletableFuture<Boolean> resultOrderDiscountDTO = createOrderDiscountUseCase
                .invoke(orderDTOS, orderIds);

        final CompletableFuture<Boolean> resultOrderDiscountDTOByProduct = createOrderDiscountProductUseCase
                .invoke(orderDTOS, orderIds);

        final CompletableFuture<Boolean> resultCreateOrderFlagIntegrations = createOrderFlagIntegrationsUseCase
                .invoke(orderFlagIntegrationDTOList);

        final CompletableFuture<Boolean> resultOrderFlags = createOrderFlagUseCase
                .invoke(intermediateOrderFlagDTOList);

        CompletableFuture<Boolean> resultCreateOrderPaymentUseCase = createOrderPaymentUseCase.invoke(
                requestPaymentMethodDTOList,
                responseCreatedOrderDTOS
        );

        final CompletableFuture<Void> resultCreateDevice = createOrderDevice
                .invoke(orderTransactionDTO.getDevice(), orderIds);

        final CompletableFuture<Boolean> resultOrderDeduction = createOrderDeductions
                .invoke(orderTransactionDTO, transactionId, orderIds);

        final CompletableFuture<Boolean> resultOrderFoodCoins = createOrderFoodCoinsUseCase
                .invoke(orderTransactionDTO, orderIds);

        final CompletableFuture<Boolean> resultOrderService = saveOrderServiceUseCase.invoke(
                orderTransactionDTO, orderIds);

        CompletableFuture.allOf(
                resultFlushOrderTransactionUseCase,
                resultCreateOrderUserData,
                resultCreateDevice,
                resultCreatePaymentsUseCase,
                resultCreateOrderPaymentUseCase,
                resultCreateOrderDetailUseCase,
                resultCreateOrderIntegrations,
                resultCreateOrderFlagSubmarines,
                resultCreateOrderFlagTogos,
                resultCreateOrderFlagCorporates,
                resultOrderDiscountDTO,
                resultOrderDiscountDTOByProduct,
                resultCreateOrderFlagIntegrations,
                resultOrderFlags,
                resultOrderDeduction,
                resultOrderFoodCoins,
                resultOrderService
        ).join();

        responseCreatedOrderDTOS.forEach((ResponseCreatedOrderDTO responseCreatedOrderDTO) -> {

            final List<OrderDiscountEntity> orderDiscountEntities = orderDiscountRepository.getLocalOrderDiscounts()
                    .stream().filter(orderDiscountEntity -> orderDiscountEntity.getOrderId()
                            .equals(responseCreatedOrderDTO.getId())).collect(Collectors.toList());

            final List<ResponseOrderDiscountDTO> responseOrderDiscounts = CollectionsKt.map(
                    orderDiscountEntities,
                    OrderDiscountMappers::toResponseOrderDiscountDTO
            );
            responseCreatedOrderDTO.setDiscounts(responseOrderDiscounts);
        });

        ResponseCreatedOrderTransactionDTO responseCreatedOrder = new ResponseCreatedOrderTransactionDTO(
                responseTransactionDTO.getCreatedAt(),
                responseTransactionDTO.getId(),
                responseCreatedOrderDTOS,
                responseTransactionDTO.getUniqueIdentifier()
        );

        final boolean isValidateOrdersIsNotNull = orderTransactionDTO.getOrders().stream()
                .anyMatch(order -> Objects.nonNull(order.getId()));

        if (Objects.nonNull(orderTransactionDTO.getId()) && isValidateOrdersIsNotNull) {

            CompletableFuture.runAsync(
                    () -> sendOrderCreatedToQueueUseCase.invoke(
                            orderTransactionDTO,
                            responseCreatedOrder
                    )
            );
        }

        return responseCreatedOrder;
    }

    private OrderAddressDTO getOrderAddressDTO(
            ResponseTransactionDTO responseTransactionDTO,
            AddressDTO address,
            ResponseCreatedOrderDTO responseCreatedOrderDTO) {

        return OrderAddressDTO.builder()
                .address(address.getAddress())
                .cityId(address.getCityId())
                .countryId(address.getCountryId())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .notes(address.getExtra())
                .orderId(responseCreatedOrderDTO.getId())
                .transactionId(responseTransactionDTO.getId().intValue())
                .zipCode(address.getZipCode())
                .build();
    }

    public List<Long> getDeliveryTypesIds() {
        return List.of(DELIVERY_TYPE_EXTERNAL_ID, DELIVERY_TYPE_INTERNAL_ID);
    }
}
