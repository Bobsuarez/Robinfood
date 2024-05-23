package com.robinfood.app.usecases.getorderdetailfinalproductgroup;

import com.robinfood.app.usecases.getorderdetailfinalproductportion.IGetOrderDetailPortionsByProductIdsUseCase;
import com.robinfood.app.usecases.getorderremovedportionsbyproductid.IGetOrderRemovedPortionsByProductIdUseCase;
import com.robinfood.core.dtos.GetOrderDetailFinalProductGroupDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO;
import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of IGetOrderDetailGroupWithPortionsByProductIdsUseCase
 */
@Component
@Slf4j
public class GetOrderDetailGroupWithPortionsByProductIdsUseCase
        implements IGetOrderDetailGroupWithPortionsByProductIdsUseCase {

    private final IGetOrderDetailPortionsByProductIdsUseCase getOrderDetailFinalProductPortionByGroupIdUseCase;
    private final IGetOrderRemovedPortionsByProductIdUseCase getOrderRemovedPortionsByProductIdUseCase;

    public GetOrderDetailGroupWithPortionsByProductIdsUseCase(
            IGetOrderDetailPortionsByProductIdsUseCase getOrderDetailFinalProductPortionByGroupIdUseCase,
            IGetOrderRemovedPortionsByProductIdUseCase getOrderRemovedPortionsByProductIdUseCase
    ) {
        this.getOrderDetailFinalProductPortionByGroupIdUseCase = getOrderDetailFinalProductPortionByGroupIdUseCase;
        this.getOrderRemovedPortionsByProductIdUseCase = getOrderRemovedPortionsByProductIdUseCase;
    }

    @Override
    public Map<Long, List<GetOrderDetailFinalProductGroupDTO>> invoke(
            List<Long> orderFinalProductIds
    ) {

        log.info("Starting process to get order detail group with portions by product ids: {}", orderFinalProductIds);

        final Map<Long, List<GetOrderDetailFinalProductPortionDTO>> portionOrderFinalProductDTOS =
                getOrderDetailFinalProductPortionByGroupIdUseCase
                        .invoke(orderFinalProductIds);

        final Map<Long, List<GetOrderDetailFinalProductGroupDTO>> orderFinalProductGroupDTOS = new HashMap<>();

        for (Map.Entry<Long, List<GetOrderDetailFinalProductPortionDTO>> entry :
                portionOrderFinalProductDTOS.entrySet()) {
            final List<GetOrderDetailFinalProductPortionDTO> getOrderDetailFinalProductPortionDTOS = entry.getValue();
            final Long finalProductId = entry.getKey();

            final Map<Long, List<GetOrderDetailFinalProductPortionDTO>> getGroupsByFinalProductId =
                    CollectionsKt.groupBy(
                            getOrderDetailFinalProductPortionDTOS,
                            GetOrderDetailFinalProductPortionDTO::getGroupId
                    );

            final Map<Long, List<OrderDetailRemovedPortionDTO>> groupedPortionsByGroup = CollectionsKt.groupBy(
                    getOrderRemovedPortionsByProductIdUseCase.invoke(orderFinalProductIds),
                    OrderDetailRemovedPortionDTO::getOrderFinalProductId
            );

            List<GetOrderDetailFinalProductGroupDTO> getGroupTemp = new ArrayList<>();

            for (Map.Entry<Long, List<GetOrderDetailFinalProductPortionDTO>> portionEntry :
                    getGroupsByFinalProductId.entrySet()) {
                for (GetOrderDetailFinalProductPortionDTO portionDTO : portionEntry.getValue()) {
                    final GetOrderDetailFinalProductGroupDTO groupDTO = new GetOrderDetailFinalProductGroupDTO(
                            portionDTO.getFinalProductId(),
                            portionDTO.getGroupId(),
                            portionDTO.getGroupName(),
                            portionDTO.getOrderId(),
                            getGroupsByFinalProductId.get(portionDTO.getGroupId()),
                            CollectionsKt.filter(
                                    groupedPortionsByGroup.getOrDefault(portionDTO.getFinalProductId(),
                                            new ArrayList<>()
                                    ),
                                    (OrderDetailRemovedPortionDTO removedPortionDTO) ->
                                            removedPortionDTO.getGroupId().equals(portionDTO.getGroupId())
                            ),
                            portionDTO.getSku()
                    );
                    getGroupTemp.add(groupDTO);
                }
            }

            final List<GetOrderDetailFinalProductGroupDTO> getGroupWithPortionUnique = CollectionsKt
                    .distinctBy(
                            getGroupTemp,
                            GetOrderDetailFinalProductGroupDTO::getId
                    );

            orderFinalProductGroupDTOS.putIfAbsent(finalProductId, getGroupWithPortionUnique);
        }

        return orderFinalProductGroupDTOS;
    }
}
