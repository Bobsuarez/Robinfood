package com.robinfood.app.usecases.getordercancellation;

import com.robinfood.app.mappers.ordercancellation.GetOrderCancellationMapper;
import com.robinfood.app.usecases.ordersquerystatement.IOrdersQueryStatementUseCase;
import com.robinfood.core.dtos.report.ordercancellation.DataToSearchIdsCanceledOrdersDTO;
import com.robinfood.core.dtos.report.ordercancellation.GetOrderCancellationResponseDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.dtos.response.PaginationDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderIntegrationEntity;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.core.entities.PosResolutionsEntity;
import com.robinfood.core.utilities.CalculateDateDisplacementUTCUtil;
import com.robinfood.repository.orderpos.IOrderPosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;
import static com.robinfood.core.constants.GlobalConstants.FOUR_ZEROS_STRING;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;

@Component
@Slf4j
public class GetOrderCancellationUseCase implements IGetOrderCancellationUseCase {

    private final IOrderPosRepository orderPosRepository;

    private final IOrdersQueryStatementUseCase ordersQueryStatementUseCase;

    public GetOrderCancellationUseCase(
            IOrderPosRepository orderPosRepository,
            IOrdersQueryStatementUseCase ordersQueryStatementUseCase
    ) {
        this.orderPosRepository = orderPosRepository;
        this.ordersQueryStatementUseCase = ordersQueryStatementUseCase;
    }

    @Override
    public EntityDTO<GetOrderCancellationResponseDTO> invoke(DataToSearchIdsCanceledOrdersDTO dataContainingTheSearchIds) {

        final Page<OrderEntity> orderEntityPage = ordersQueryStatementUseCase.invoke(dataContainingTheSearchIds);

        final List<GetOrderCancellationResponseDTO> pagesContent = orderEntityPage
                .getContent().stream()
                .map(this::buildGetOrderCancellationResponse)
                .collect(Collectors.toList());

        log.info("EntityDTO[GetOrderCancellationUseCase] response invoke :  {} ", pagesContent);

        return new EntityDTO<>(
                pagesContent,
                PaginationDTO.builder()
                        .perPage(dataContainingTheSearchIds.getPerPageDTO())
                        .page(dataContainingTheSearchIds.getCurrentPageDTO())
                        .lastPage(orderEntityPage.getTotalPages())
                        .total(orderEntityPage.getTotalElements())
                        .build()
        );
    }

    private GetOrderCancellationResponseDTO buildGetOrderCancellationResponse(OrderEntity orderEntity) {

        final String integrationId = toSearchIntegrationId(orderEntity);
        final String prefix = toSearchPrefixInResolution(orderEntity.getPosId());
        final OrderUserDataEntity orderUserData = orderEntity.getUserDataEntity()
                .stream()
                .findFirst()
                .orElse(OrderUserDataEntity.builder()
                        .firstName(DEFAULT_STRING_VALUE)
                        .lastName(DEFAULT_STRING_VALUE)
                        .mobile(DEFAULT_STRING_VALUE)
                        .build());

        return GetOrderCancellationMapper
                .toGetOrderCancellationResponseDTO(integrationId, orderEntity, orderUserData, prefix);
    }

    private String toSearchPrefixInResolution(Long posId) {

        return orderPosRepository
                .findByPosIdAndCurrent(posId, DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT)
                .stream().map(PosResolutionsEntity::getPrefix)
                .findFirst()
                .orElse(DEFAULT_STRING_VALUE);
    }

    private String toSearchIntegrationId(OrderEntity orderEntity) {

        return orderEntity.getOrderIntegrationEntityList()
                .stream().findFirst()
                .orElse(OrderIntegrationEntity.builder()
                        .integrationId(FOUR_ZEROS_STRING)
                        .build())
                .getIntegrationId();
    }
}
