package com.robinfood.app.usecases.getposresolution;

import com.google.gson.Gson;
import com.robinfood.app.mappers.TranslationPosResolutionMapper;
import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.core.utilities.CalculateDateDisplacementUTCUtil;
import com.robinfood.repository.orderpos.IOrderPosRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.FOUR_ZEROS_STRING;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;


@Component
@Slf4j
public class GetPosResolutionUseCase implements IGetPosResolutionUseCase {

    private final IOrderPosRepository orderPosRepository;
    private final IOrdersRepository ordersRepository;

    @Value("#{'${pos.orders.status-cancelled}'.split(',')}")
    private List<Long> IDS_ORDERS_STATUS_CANCELLED;

    public GetPosResolutionUseCase (
            IOrderPosRepository orderPosRepository,
            IOrdersRepository ordersRepository,
            List<Long> idsOrdersStatusCancelled
    ) {
        this.orderPosRepository = orderPosRepository;
        this.ordersRepository = ordersRepository;
        IDS_ORDERS_STATUS_CANCELLED = idsOrdersStatusCancelled;
    }

    @Override
    public GetPosResolutionsDTO invoke (final DataPosResolutionRequestDTO dataPosResolutionRequestDTO) {

        log.info(
                "Start of the process that obtains the resolution with {}",
                new Gson().toJson(dataPosResolutionRequestDTO)
        );

        final GetPosResolutionsDTO posResolutionsDTO = orderPosRepository.findByPosIdAndCurrent(
                        dataPosResolutionRequestDTO.getPosId(),
                        DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT)
                .map(TranslationPosResolutionMapper::buildGetPosResponseDTO)
                .orElseThrow(() -> new GenericOrderBcException("Pos resolution not found"));

        final Map<String, LocalDateTime> localDateTimeZone =
                CalculateDateDisplacementUTCUtil.getLocalDateByRange(
                        dataPosResolutionRequestDTO.getLocalDateEnd(),
                        dataPosResolutionRequestDTO.getLocalDateStart(),
                        dataPosResolutionRequestDTO.getTimeZone());

        final List<OrderEntity> resultOrdersPayment =
                searchTheListOfPaidOrders(dataPosResolutionRequestDTO, localDateTimeZone);

        findTheMaXAndMinInvoiceNumber(resultOrdersPayment, posResolutionsDTO);

        searchCountOfEffectiveAndCanceledOrders(resultOrdersPayment, posResolutionsDTO);

        log.info("Pos resolution found {}", new Gson().toJson(posResolutionsDTO));

        return posResolutionsDTO;
    }

    private List<OrderEntity> searchTheListOfPaidOrders (
            final DataPosResolutionRequestDTO dataPosRequestDTO,
            final Map<String, LocalDateTime> localDateTimeMap
    ) {

        return ordersRepository
                .findByCreatedAtBetweenAndPaidAndPosId(
                        localDateTimeMap.get(LOCAL_DATE_START),
                        localDateTimeMap.get(LOCAL_DATE_END),
                        ORDER_PAID,
                        dataPosRequestDTO.getPosId())
                .orElseThrow(() -> new GenericOrderBcException("orders not found"));

    }

    private void findTheMaXAndMinInvoiceNumber (
            List<OrderEntity> orderListInvoicePaid,
            GetPosResolutionsDTO getPosResolutionsDTO
    ) {

        Integer orderMinInvoiceNumber =
                orderListInvoicePaid
                        .stream()
                        .filter(item -> !item.getOrderInvoiceNumber().isEmpty() &&
                                !item.getOrderInvoiceNumber().equals(FOUR_ZEROS_STRING))
                        .mapToInt(v -> Integer.parseInt(v.getOrderInvoiceNumber()))
                        .min().orElse(DEFAULT_INTEGER_VALUE);

        Integer orderMaxInvoiceNumber =
                orderListInvoicePaid
                        .stream().filter(item -> !item.getOrderInvoiceNumber().isEmpty() &&
                                !item.getOrderInvoiceNumber().equals(FOUR_ZEROS_STRING))
                        .mapToInt(v -> Integer.parseInt(v.getOrderInvoiceNumber()))
                        .max().orElse(DEFAULT_INTEGER_VALUE);

        getPosResolutionsDTO.setStartNumber(orderMinInvoiceNumber);
        getPosResolutionsDTO.setEndNumber(orderMaxInvoiceNumber);
    }

    private void searchCountOfEffectiveAndCanceledOrders (
            List<OrderEntity> orderListInvoicePaid,
            GetPosResolutionsDTO posResolutionsDTO
    ) {

        Long countEffectiveInvoices = orderListInvoicePaid
                .stream()
                .filter(item -> !item.getOrderInvoiceNumber().equals(FOUR_ZEROS_STRING))
                .count();

        Long countCancelledInvoices = orderListInvoicePaid
                .stream()
                .filter(item -> IDS_ORDERS_STATUS_CANCELLED.contains(item.getStatusId())
                        && !item.getOrderInvoiceNumber().equals(FOUR_ZEROS_STRING)
                ).count();

        posResolutionsDTO.setEffectiveInvoices(countEffectiveInvoices);
        posResolutionsDTO.setCancelledInvoices(countCancelledInvoices);
    }

}
