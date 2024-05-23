package com.robinfood.app.usecases.getorderdetail;

import com.robinfood.app.usecases.completeorderproductsdata.ICompleteOrderProductsDataFromMenuCurrentUseCase;
import com.robinfood.app.usecases.completeproducthalldata.ICompleteMenuProductsDataFromMenuHallsUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.OrderDetailChangedPortionDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderdetail.IOrderDetailRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of IGetOrderDetailUseCase
 */
@Slf4j
public class GetOrderDetailUseCase implements IGetOrderDetailUseCase {

    private final IOrderDetailRepository orderDetailRepository;
    private final ICompleteMenuProductsDataFromMenuHallsUseCase completeMenuProductsDataFromMenuHallsUseCase;
    private final ICompleteOrderProductsDataFromMenuCurrentUseCase completeOrderProductsDataFromMenuCurrentUseCase;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public GetOrderDetailUseCase(
            IOrderDetailRepository orderDetailRepository,
            ICompleteMenuProductsDataFromMenuHallsUseCase completeMenuProductsDataFromMenuHallsUseCase,
            ICompleteOrderProductsDataFromMenuCurrentUseCase completeOrderProductsDataFromMenuCurrentUseCase,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        this.orderDetailRepository = orderDetailRepository;
        this.completeMenuProductsDataFromMenuHallsUseCase = completeMenuProductsDataFromMenuHallsUseCase;
        this.completeOrderProductsDataFromMenuCurrentUseCase = completeOrderProductsDataFromMenuCurrentUseCase;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    @Override
    public Result<List<OrderDetailDTO>> invoke(
            Long countryId,
            Long flowId,
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    ) {

        TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Starting process to get order details with :  countryId: {}, flowId: {}," +
                        " orderIds: [{}]",
                 countryId, flowId, orderIds);

        Result<List<OrderDetailDTO>> orderDetailListResult = orderDetailRepository.getOrderDetail(
                token.getAccessToken(),
                orderIds,
                orderUids,
                orderUuid
        );

        if (orderDetailListResult instanceof Result.Success) {
            List<OrderDetailDTO> orderDetailDTOList = ((Result.Success<List<OrderDetailDTO>>) orderDetailListResult)
                    .getData();

            for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
                orderDetailDTO
                        .getProducts()
                        .sort(Comparator.comparing(OrderDetailProductDTO::getBrandMenuId));

                invertReplacementPortions(orderDetailDTO.getProducts());

                completeOrderProductsDataFromMenuCurrentUseCase.invoke(
                        countryId,
                        Optional.of(orderDetailDTO.getFlowId()).orElse(flowId),
                        orderDetailDTO.getProducts(),
                        orderDetailDTO.getStoreId(),
                        token.getAccessToken()
                );

                completeMenuProductsDataFromMenuHallsUseCase.invoke(
                        countryId,
                        Optional.of(orderDetailDTO.getFlowId()).orElse(flowId),
                        orderDetailDTO.getProducts(),
                        orderDetailDTO.getStoreId(),
                        token.getAccessToken()
                );
            }
        }

        return orderDetailListResult;
    }

    /**
     * In case there is a replacement exchanges the original portion with changed
     *
     * @param orderDetailProducts order products to exchange replacement portions
     */
    public static void invertReplacementPortions(@NotNull List<OrderDetailProductDTO> orderDetailProducts) {

        log.info("Starting process to invert portions replacements with products [{}]", orderDetailProducts);
        final List<OrderDetailProductGroupPortionDTO> portionsWithReplacement = new ArrayList<>();

        orderDetailProducts.forEach((OrderDetailProductDTO orderDetailProductDTO) ->
                orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO orderDetailProductGroupDTO) ->
                        portionsWithReplacement.addAll(orderDetailProductGroupDTO.getPortions()
                                .stream()
                                .filter(OrderDetailProductGroupPortionDTO::hasReplacement)
                                .collect(Collectors.toList()))
                )
        );

        portionsWithReplacement.forEach(GetOrderDetailUseCase::replacePortions);
    }

    private static void replacePortions(OrderDetailProductGroupPortionDTO orderDetailProductGroupPortionDTO) {
        OrderDetailChangedPortionDTO incomingChangedPortionDTO =
                orderDetailProductGroupPortionDTO.getChangedPortion();
        Long originalId = incomingChangedPortionDTO.getId();
        String originalName = incomingChangedPortionDTO.getName();
        Long originalParentId = incomingChangedPortionDTO.getParentId();

        OrderDetailChangedPortionDTO actualChangedPortionDTO = new OrderDetailChangedPortionDTO(
                orderDetailProductGroupPortionDTO.getId(),
                orderDetailProductGroupPortionDTO.getName(),
                orderDetailProductGroupPortionDTO.getParentId(),
                orderDetailProductGroupPortionDTO.getSku(),
                orderDetailProductGroupPortionDTO.getUnits(),
                orderDetailProductGroupPortionDTO.getWeight()
        );
        orderDetailProductGroupPortionDTO.setChangedPortion(actualChangedPortionDTO);
        orderDetailProductGroupPortionDTO.setId(originalId);
        orderDetailProductGroupPortionDTO.setName(originalName);
        orderDetailProductGroupPortionDTO.setParentId(originalParentId);
    }
}
