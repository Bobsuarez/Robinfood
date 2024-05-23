package com.robinfood.app.usecases.getorderhistory;

import com.robinfood.app.usecases.getdeliverytypes.IGetDeliveryTypesUseCase;
import com.robinfood.app.usecases.getorderdetailbyids.IGetOrderDetailByIdsUseCase;
import com.robinfood.app.usecases.getorderhistoryitems.IGetOrderHistoryItemsUseCase;
import com.robinfood.app.usecases.getstatusbyorderids.IGetStatusByOrderIds;
import com.robinfood.app.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import com.robinfood.core.dtos.DeliveryTypeDTO;
import com.robinfood.core.dtos.HistoryDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.PropertyDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.extensions.ListExtensions;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of IGetOrderHistoryUseCase
 */
@Component
@Slf4j
public class GetOrderHistoryUseCase implements IGetOrderHistoryUseCase {

    private final IGetDeliveryTypesUseCase getDeliveryTypesUseCase;
    private final IGetOrderDetailByIdsUseCase getOrderDetailByIdsUseCase;
    private final IGetOrderHistoryItemsUseCase getOrderHistoryItemsUseCase;
    private final IGetStatusByOrderIds getStatusByOrderIds;
    private final IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;

    public GetOrderHistoryUseCase(
            IGetDeliveryTypesUseCase getDeliveryTypesUseCase,
            IGetOrderDetailByIdsUseCase getOrderDetailByIdsUseCase,
            IGetOrderHistoryItemsUseCase getOrderHistoryItemsUseCase,
            IGetStatusByOrderIds getStatusByOrderIds,
            IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase
    ) {
        this.getDeliveryTypesUseCase = getDeliveryTypesUseCase;
        this.getOrderDetailByIdsUseCase = getOrderDetailByIdsUseCase;
        this.getOrderHistoryItemsUseCase = getOrderHistoryItemsUseCase;
        this.getStatusByOrderIds = getStatusByOrderIds;
        this.getUserDataByOrderIdsUseCase = getUserDataByOrderIdsUseCase;
    }

    @Override
    public HistoryDTO<OrderHistoryItemDTO> invoke(
            String timeZone,
            LocalDate createdAt,
            Integer currentPage,
            Boolean isIntegration,
            Boolean isInternalDelivery,
            Boolean isOnPremise,
            Long originId,
            Boolean isPaid,
            Integer perPage,
            Long storeId
    ) {

        log.info("Starting process to get order history with origin id [{}] and store id [{}]",
                originId, storeId);

        var createAtToUtc = validateCreateAtAndTransformToUtc(timeZone, createdAt);

        final List<DeliveryTypeDTO> deliveryTypes = getDeliveryTypesUseCase.invoke(
                isIntegration,
                isInternalDelivery,
                isOnPremise
        );
        final List<Long> deliveryTypeIds = CollectionsKt.map(deliveryTypes, DeliveryTypeDTO::getId);
        final Page<OrderHistoryItemDTO> orderHistoryItems = getOrderHistoryItemsUseCase.invoke(
                createAtToUtc,
                deliveryTypeIds,
                originId,
                PageRequest.of(currentPage - 1, perPage),
                isPaid,
                storeId
        );
        final List<Long> orderHistoryItemIds = CollectionsKt.map(orderHistoryItems,
                OrderHistoryItemDTO::getId);
        final List<UserDataDTO> userDataItems = getUserDataByOrderIdsUseCase.invoke(
                orderHistoryItemIds);
        final List<OrderDetailDTO> orderDetails = getOrderDetailByIdsUseCase.invoke(
                orderHistoryItemIds);
        final List<Long> orderHistoryStatusesIds = CollectionsKt.map(
                orderHistoryItems,
                OrderHistoryItemDTO::getStatusId
        );
        final List<OrderStatusDTO> statuses = getStatusByOrderIds.invoke(orderHistoryStatusesIds);
        for (OrderHistoryItemDTO orderHistoryItem : orderHistoryItems) {
            final UserDataDTO userDataForCurrentOrder = ListExtensions
                    .find(userDataItems, (UserDataDTO userData) ->
                            Objects.equals(userData.getOrderId(), orderHistoryItem.getId()));
            final OrderDetailDTO orderDetailForCurrentOrder = ListExtensions
                    .find(orderDetails, (OrderDetailDTO orderDetail) ->
                            Objects.equals(orderDetail.getId(), orderHistoryItem.getId()));
            final OrderStatusDTO statusForCurrentOrder = ListExtensions
                    .find(statuses, (OrderStatusDTO status) -> status.getId()
                            .equals(orderHistoryItem.getStatusId()));
            if (Objects.nonNull(userDataForCurrentOrder)) {
                orderHistoryItem.setCustomerName(userDataForCurrentOrder.getFullName());
            }
            if (Objects.nonNull(orderDetailForCurrentOrder)) {
                orderHistoryItem.setInvoiceNumber(orderDetailForCurrentOrder.getInvoice());
            }
            if (Objects.nonNull(statusForCurrentOrder)) {
                orderHistoryItem.setStatus(statusForCurrentOrder.getName());
            }
        }
        final List<OrderHistoryItemDTO> pagesContent = orderHistoryItems.getContent();

        log.info("[{}] history of orders obtained", pagesContent.size());

        return new HistoryDTO<>(
                pagesContent,
                new PropertyDTO(
                        currentPage,
                        perPage,
                        orderHistoryItems.getTotalPages(),
                        pagesContent.size()
                )
        );
    }

    /**
     * Method that valid createdAt field, and it's transform to UTC
     *
     * @param timeZone  time zone of request
     * @param createdAt the date to filter history
     * @return transformed date
     */
    private static LocalDate validateCreateAtAndTransformToUtc(String timeZone, LocalDate createdAt) {
        if (Objects.nonNull(createdAt)) {
            final ZonedDateTime date = createdAt.atStartOfDay(ZoneId.of(timeZone));
            return LocalDate.ofInstant(date.toInstant(), ZoneOffset.UTC);
        }

        return LocalDate.now().atStartOfDay(ZoneOffset.UTC).toLocalDate();
    }
}
