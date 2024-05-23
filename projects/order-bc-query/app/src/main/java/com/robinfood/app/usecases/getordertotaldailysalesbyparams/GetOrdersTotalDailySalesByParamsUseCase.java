package com.robinfood.app.usecases.getordertotaldailysalesbyparams;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.GetOrderTotalDailySalesDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderPaymentEntity;
import com.robinfood.core.entities.OriginEntity;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.origin.IOriginRepository;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetOrdersTotalDailySalesByParamsUseCase implements IGetOrdersTotalDailySalesByParamsUseCase {

    private final IOrdersRepository ordersRepository;
    private final IPaymentMethodRepository paymentMethodRepository;
    private final IOrderPaymentRepository orderPaymentRepository;
    private final IOriginRepository originRepository;
    public static final Long CANCEL_ORDER = 6L;
    public static final Long DELETE_ORDER = 7L;
    public static final Long INTEGRATION_ORDER = 8L;
    public static final Long POS_DEBIT_CARD = 2L;
    public static final Long POS_CREDIT_CARD = 3L;
    public static final Long AG_DEBIT_CARD = 4L;
    public static final Long AG_CREDIT_CARD = 5L;
    public static final Long DAVIPLATA_AG = 11L;
    public static final Long NEQUI_AG = 10L;
    public static final Long DAVIPLATA_POS = 28L;
    public static final Long NEQUI_POS = 27L;

    public GetOrdersTotalDailySalesByParamsUseCase(IOrdersRepository ordersRepository,
                                                   IPaymentMethodRepository paymentMethodRepository,
                                                   IOrderPaymentRepository orderPaymentRepository,
                                                   IOriginRepository originRepository) {
        this.ordersRepository = ordersRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.orderPaymentRepository = orderPaymentRepository;
        this.originRepository = originRepository;
    }

    /**
     * Retrieve the total daily sales by store and specific date
     *
     * @param storeId   the store id to order
     * @param createdAt the create at to order
     * @return List<GetOrderTotalDailySalesDTO>
     * @author Marcos Manotas - CKS
     */
    @Override
    public List<GetOrderTotalDailySalesDTO> invoke(Long storeId, LocalDate createdAt) {
        log.info("Starting process to get total daily sales by [{}] storeId, [{}] createdAt", storeId, createdAt);

        List<OrderPaymentEntity> orderPayments = this.findAllOrderPayments(storeId, createdAt);

        return this.buildTotalDailySales(orderPayments);
    }

    @NotNull
    private List<GetOrderTotalDailySalesDTO> buildTotalDailySales(List<OrderPaymentEntity> orderPayments) {

        if (orderPayments.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> paymentMethodsIds = orderPayments
            .stream().map(OrderPaymentEntity::getPaymentMethodId).distinct().collect(Collectors.toList());

        List<Long> originIds = orderPayments
            .stream().map(OrderPaymentEntity::getOriginId).distinct().collect(Collectors.toList());

        Iterable<PaymentMethodEntity> paymentMethods = this.paymentMethodRepository.findAll();
        Iterable<OriginEntity> origins = this.originRepository.findAllById(originIds);

        List<GetOrderTotalDailySalesDTO> orderTotalDailySalesToReturn = new ArrayList<>();
        Map<Integer, Map<Double, Long>> mapOrderTotalDailyByCardTemp = new HashMap<>();

        paymentMethodsIds.forEach((Long paymentMethodId) ->
            this.validatePaymentMethod(orderPayments,
                paymentMethods,
                origins,
                orderTotalDailySalesToReturn,
                mapOrderTotalDailyByCardTemp,
                paymentMethodId));

        if (!mapOrderTotalDailyByCardTemp.isEmpty()) {
            orderTotalDailySalesToReturn.addAll(mapOrderTotalDailyByCardTemp.entrySet()
                .stream().map((Map.Entry<Integer, Map<Double, Long>> interMapEntry) ->
                    new GetOrderTotalDailySalesDTO(interMapEntry.getValue().keySet().stream().findFirst().get(),
                        interMapEntry.getKey(),
                        this.findPaymentMethodNameById(paymentMethods, interMapEntry.getKey()),
                        interMapEntry.getValue().entrySet().stream().findFirst().get().getValue())
                ).collect(Collectors.toList()));
        }
        return orderTotalDailySalesToReturn;
    }

    private String findPaymentMethodNameById (Iterable<PaymentMethodEntity> paymentMethods, Integer idPaymentMethod) {

        AtomicReference<String> paymentMethodName = new AtomicReference<>(GlobalConstants.DEFAULT_STRING_VALUE);

        paymentMethods.forEach((PaymentMethodEntity paymentMethodEntity) -> {
            if(paymentMethodEntity.getId().equals(idPaymentMethod.longValue())){
                paymentMethodName.set(paymentMethodEntity.getName());
            }
        });
        return paymentMethodName.get();
    }

    private void validatePaymentMethod
        (List<OrderPaymentEntity> orderPayments,
         Iterable<PaymentMethodEntity> paymentMethods,
         Iterable<OriginEntity> origins,
         List<GetOrderTotalDailySalesDTO> orderTotalDailySalesToReturn,
         Map<Integer, Map<Double, Long>> mapOrderTotalDailyByCardTemp,
         Long paymentMethodId) {

        if (INTEGRATION_ORDER.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByOrigin(orderPayments, orderTotalDailySalesToReturn, origins);
            return;
        }

        if (extractedTotalForDataPhonePOS(orderPayments, mapOrderTotalDailyByCardTemp, paymentMethodId)){
            return;
        }

        if (extractedTotalForDataPhoneAg(orderPayments, mapOrderTotalDailyByCardTemp, paymentMethodId)){
            return;
        }

        this.calculateTotalDailySales(orderPayments,
            paymentMethods, paymentMethodId, orderTotalDailySalesToReturn);
    }


    private boolean extractedTotalForDataPhonePOS(
        List<OrderPaymentEntity> orderPayments,
        Map<Integer, Map<Double, Long>> mapOrderTotalDailyByCardTemp,
        Long paymentMethodId)
    {
        if (POS_CREDIT_CARD.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByTypeCard(orderPayments,
                mapOrderTotalDailyByCardTemp, paymentMethodId, POS_DEBIT_CARD.intValue());
            return GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
        }

        if (POS_DEBIT_CARD.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByTypeCard(orderPayments,
                mapOrderTotalDailyByCardTemp, paymentMethodId, POS_DEBIT_CARD.intValue());
            return GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
        }

        if (NEQUI_POS.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByTypeCard(orderPayments,
                mapOrderTotalDailyByCardTemp, paymentMethodId, POS_DEBIT_CARD.intValue());
            return GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
        }

        if (DAVIPLATA_POS.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByTypeCard(orderPayments,
                mapOrderTotalDailyByCardTemp, paymentMethodId, POS_DEBIT_CARD.intValue());
            return GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
        }

        return GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
    }

    private boolean extractedTotalForDataPhoneAg(
        List<OrderPaymentEntity> orderPayments,
        Map<Integer, Map<Double, Long>> mapOrderTotalDailyByCardTemp,
        Long paymentMethodId)
    {
        if (AG_CREDIT_CARD.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByTypeCard(orderPayments,
                mapOrderTotalDailyByCardTemp, paymentMethodId, AG_DEBIT_CARD.intValue());
            return GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
        }

        if (AG_DEBIT_CARD.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByTypeCard(orderPayments,
                mapOrderTotalDailyByCardTemp, paymentMethodId, AG_DEBIT_CARD.intValue());
            return GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
        }

        if (NEQUI_AG.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByTypeCard(orderPayments,
                mapOrderTotalDailyByCardTemp, paymentMethodId, AG_DEBIT_CARD.intValue());
            return GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
        }

        if (DAVIPLATA_AG.equals(paymentMethodId)) {
            this.calculateTotalDailySalesByTypeCard(orderPayments,
                mapOrderTotalDailyByCardTemp, paymentMethodId, AG_DEBIT_CARD.intValue());
            return GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
        }

        return GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
    }

    private void calculateTotalDailySalesByTypeCard
        (List<OrderPaymentEntity> orderPayments,
         Map<Integer, Map<Double, Long>> orderTotalDailyByCardTemp,
         Long paymentMethodId,
         Integer typeCard) {

        Map<Double, Long> mapTotalDailyByTypeCard = orderTotalDailyByCardTemp.get(typeCard);
        double totalDailySalesByTypeCard = orderPayments.stream().filter((OrderPaymentEntity orderPaymentEntity) ->
                orderPaymentEntity.getPaymentMethodId().equals(paymentMethodId))
            .mapToDouble(OrderPaymentEntity::getValue).sum();

        long totalOrderByTypeCard = orderPayments.stream()
            .filter((OrderPaymentEntity orderPaymentEntity) ->
                orderPaymentEntity.getPaymentMethodId().equals(paymentMethodId)).count();

        validateMapOrderTotalDaily
            (orderTotalDailyByCardTemp,
                typeCard,
                mapTotalDailyByTypeCard,
                totalDailySalesByTypeCard,
                totalOrderByTypeCard);
    }

    private static void validateMapOrderTotalDaily
        (Map<Integer, Map<Double, Long>> orderTotalDailyByCardTemp,
         Integer typeCard,
         Map<Double, Long> mapTotalDailyByTypeCard,
         double totalDailySalesByTypeCard,
         long totalOrderByTypeCard) {
        if (mapTotalDailyByTypeCard != null && !mapTotalDailyByTypeCard.isEmpty()) {
            double totalDaily = mapTotalDailyByTypeCard.
                keySet().stream().findFirst().orElse(0.0);

            long totalOrder = mapTotalDailyByTypeCard.get(totalDaily);

            totalOrder += totalOrderByTypeCard;
            totalDaily += totalDailySalesByTypeCard;

            addOrderTotalDailyCalculated(totalDaily, totalOrder, orderTotalDailyByCardTemp, typeCard);
            return;
        }
        addOrderTotalDailyCalculated
            (totalDailySalesByTypeCard,
                totalOrderByTypeCard,
                orderTotalDailyByCardTemp,
                typeCard);
    }

    private static void addOrderTotalDailyCalculated
        (double totalDaily,
         long totalOrder,
         Map<Integer, Map<Double, Long>> orderTotalDailyByCardTemp,
         Integer typeCard) {
        Map<Double, Long> mapTotalDailyByTypeCard = new HashMap<>();
        mapTotalDailyByTypeCard.put(totalDaily, totalOrder);
        orderTotalDailyByCardTemp.put(typeCard, mapTotalDailyByTypeCard);
    }

    private void calculateTotalDailySalesByOrigin(List<OrderPaymentEntity> orderPayments,
                                                  List<GetOrderTotalDailySalesDTO> orderTotalDailySalesToReturn,
                                                  Iterable<OriginEntity> origins) {

        origins.forEach((OriginEntity originEntity) -> {
            if (orderPayments.stream().anyMatch((OrderPaymentEntity orderPaymentEntity) ->
                orderPaymentEntity.getPaymentMethodId().equals(INTEGRATION_ORDER) &&
                    orderPaymentEntity.getOriginId().equals(originEntity.getId()))) {
                this.addTotalDailyByOriginInList(orderPayments, orderTotalDailySalesToReturn, originEntity);
            }
        });
    }

    private static void addTotalDailyByOriginInList(List<OrderPaymentEntity> orderPayments,
                                                    List<GetOrderTotalDailySalesDTO> orderTotalDailySalesToReturn,
                                                    OriginEntity originEntity) {
        double totalDailySalesByOrigin = orderPayments.stream().
            filter((OrderPaymentEntity orderPaymentEntity) -> orderPaymentEntity.getPaymentMethodId()
                .equals(INTEGRATION_ORDER) && orderPaymentEntity.getOriginId().equals(originEntity.getId())).
            mapToDouble(OrderPaymentEntity::getValue).sum();

        long totalOrderByOrigin = orderPayments.stream().filter((OrderPaymentEntity orderPaymentEntity) ->
            orderPaymentEntity.getPaymentMethodId().equals(INTEGRATION_ORDER) &&
                orderPaymentEntity.getOriginId().equals(originEntity.getId())).count();

        orderTotalDailySalesToReturn.add(new GetOrderTotalDailySalesDTO(totalDailySalesByOrigin, INTEGRATION_ORDER,
            originEntity.getCode(), totalOrderByOrigin));
    }

    private void calculateTotalDailySales(List<OrderPaymentEntity> orderPayments,
                                          Iterable<PaymentMethodEntity> paymentMethods,
                                          Long paymentMethodId,
                                          List<GetOrderTotalDailySalesDTO> orderTotalDailySalesToReturn) {

        double totalDailySalesByPaymentMethod = orderPayments.stream()
            .filter((OrderPaymentEntity orderPaymentEntity) ->
                orderPaymentEntity.getPaymentMethodId().equals(paymentMethodId))
            .mapToDouble(OrderPaymentEntity::getValue).sum();

        long totalOrderByPaymentMethod = orderPayments.stream()
            .filter((OrderPaymentEntity orderPaymentEntity) ->
                orderPaymentEntity.getPaymentMethodId().equals(paymentMethodId)).count();

        AtomicReference<String> namePaymentMethod = new AtomicReference<>("");
        paymentMethods.forEach((PaymentMethodEntity paymentMethod) -> {
            if (paymentMethod.getId().equals(paymentMethodId)) {
                namePaymentMethod.set(paymentMethod.getName());
            }
        });
        orderTotalDailySalesToReturn.add(
            new GetOrderTotalDailySalesDTO(totalDailySalesByPaymentMethod,
                paymentMethodId,
                namePaymentMethod.get(),
                totalOrderByPaymentMethod));
    }

    private List<OrderPaymentEntity> findAllOrderPayments(Long storeId, LocalDate createdAt) {

        List<Long> statusIds = List.of(CANCEL_ORDER, DELETE_ORDER);

        List<OrderEntity> orderDailySales = this.ordersRepository.
            findAllByStoreIdAndOperationDateAndPaidAndStatusIdNotIn(storeId,
                createdAt,
                true,
                statusIds);

        List<Long> orderIds = orderDailySales.stream().map(OrderEntity::getId).collect(Collectors.toList());
        List<OrderPaymentEntity> orderPayments = this.orderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(
            orderIds);

        orderPayments.forEach((OrderPaymentEntity orderPaymentEntity) -> {

            OrderEntity orderFilter = orderDailySales.stream().
                filter((OrderEntity orderEntity) -> orderPaymentEntity.getOrderId().equals(orderEntity.getId()))
                .findFirst().orElse(null);

            if (orderFilter != null) {
                orderPaymentEntity.setOriginId(orderFilter.getOriginId());
            }
        });
        return orderPayments;
    }
}
