package com.robinfood.core.mappers;

import static com.robinfood.core.constants.GlobalConstants.WORKSHIFT_ID;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.DeviceDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.GroupDTO;
import com.robinfood.core.dtos.transactionrequestdto.NewUserDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.PortionDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import com.robinfood.core.entities.transactionrequestentities.DeviceEntity;
import com.robinfood.core.entities.transactionrequestentities.FinalProductRequestEntity;
import com.robinfood.core.entities.transactionrequestentities.GroupRequestEntity;
import com.robinfood.core.entities.transactionrequestentities.NewUserEntity;
import com.robinfood.core.entities.transactionrequestentities.OrderRequestEntity;
import com.robinfood.core.entities.transactionrequestentities.PortionRequestEntity;
import com.robinfood.core.entities.transactionrequestentities.TransactionRequestEntity;
import com.robinfood.core.models.domain.menu.Brand;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Slf4j
public final class TransactionRequestMappers {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private TransactionRequestMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static TransactionRequestEntity toTransactionRequestEntity(
            TransactionRequestDTO transactionRequestDTO,
            List<Brand> brands
    ) {

        List<OrderRequestEntity> orderToResponse = new ArrayList<>();

        for (OrderDTO orderRequest : transactionRequestDTO.getOrders()) {
            orderToResponse.add(
                    toOrderRequestEntity(
                            orderRequest,
                            transactionRequestDTO.getPaid(),
                            brands
                    )
            );
        }

        TransactionRequestEntity transactionRequestEntity = modelMapper.map(
                transactionRequestDTO, TransactionRequestEntity.class);

        final OrderFiscalIdentifierDTO orderFiscalIdentifierDTO = transactionRequestDTO.getOrderFiscalIdentifier();

        if (Objects.nonNull(orderFiscalIdentifierDTO)) {
            transactionRequestEntity.setBuyer(toOrderFiscalIdentifierEntity(orderFiscalIdentifierDTO));
        }

        transactionRequestEntity.setOrders(orderToResponse);

        transactionRequestEntity.setDevice(
                toDeviceEntity(transactionRequestDTO.getDevice())
        );

        if (transactionRequestDTO.getUser() != null) {
            transactionRequestEntity.setUser(toUserEntity(transactionRequestDTO.getUser()));
        }

        if (Objects.nonNull(transactionRequestDTO.getCoupons())) {
            transactionRequestEntity.setCouponResponseEntities(
                    transactionRequestDTO.getPerformCouponResponseDTOS()
                            .stream()
                            .map(TransactionRequestMappers::toCouponsApplied)
                            .collect(Collectors.toList()));
        }
        return transactionRequestEntity;
    }

    public static DeviceEntity toDeviceEntity(
            DeviceDTO deviceDTO
    ) {
        return new DeviceEntity(
                deviceDTO.getIp(),
                deviceDTO.getPlatform(),
                deviceDTO.getTimezone(),
                deviceDTO.getVersion()
        );
    }

    public static OrderRequestEntity toOrderRequestEntity(
            OrderDTO orderDTO,
            Boolean paid,
            List<Brand> brands
    ) {
        List<FinalProductRequestEntity> finalProductRequestEntities = new ArrayList<>();

        for (FinalProductDTO finalProductDTO : orderDTO.getFinalProducts()) {
            finalProductRequestEntities.add(toFinalProductRequestEntity(finalProductDTO, brands));
        }

        OrderRequestEntity orderRequest = modelMapper.map(orderDTO, OrderRequestEntity.class);
        orderRequest.setFinalProducts(finalProductRequestEntities);
        orderRequest.setTotalMinusProductsDiscounts(BigDecimal.ZERO);
        orderRequest.setTotalPaidPayments(BigDecimal.ZERO);
        orderRequest.setCo2Total(orderDTO.getCo2Total());
        orderRequest.setTotal(orderRequest.getTotal());
        orderRequest.setWorkshiftId(WORKSHIFT_ID);
        orderRequest.setPaid(paid);
        setFranchiseOrderEntity(orderDTO, brands, orderRequest);

        return orderRequest;
    }

    private static void setFranchiseOrderEntity(
            OrderDTO orderDTO,
            List<Brand> brands,
            OrderRequestEntity orderRequestEntity
    ) {
        if (orderDTO.getIsMultiBrand()) {
            orderRequestEntity.getBrand().setId(GlobalConstants.MULTI_BRAND_ID);
            orderRequestEntity.getBrand().setName(GlobalConstants.MULTI_BRAND_NAME);
        } else {
            brands.stream()
                    .filter(brand -> brand.getId().equals(orderDTO.getBrand().getId()))
                    .findFirst()
                    .map(Brand::getFranchiseId)
                    .ifPresent(franchiseId -> orderRequestEntity.getBrand().setId(franchiseId));
        }
    }

    public static FinalProductRequestEntity toFinalProductRequestEntity(
            FinalProductDTO finalProductDTO,
            List<Brand> brands
    ) {
        FinalProductRequestEntity finalProductRequest = modelMapper.map(
                finalProductDTO,
                FinalProductRequestEntity.class
        );
        finalProductRequest.setPortions(toPortionRequestEntities(finalProductDTO.getGroups()));
        finalProductRequest.setValue(finalProductDTO.getPrice());
        finalProductRequest.setCo2Total(finalProductDTO.getCo2Total());
        setFranchiseFinalProductEntity(finalProductDTO, brands, finalProductRequest);

        return finalProductRequest;
    }

    private static void setFranchiseFinalProductEntity(
            FinalProductDTO finalProductDTO,
            List<Brand> brands,
            FinalProductRequestEntity finalProductRequestEntity
    ) {
        brands.stream()
                .filter(brand -> brand.getId().equals(finalProductDTO.getBrand().getId()))
                .findFirst()
                .map(Brand::getFranchiseId)
                .ifPresent(franchiseId ->
                        finalProductRequestEntity.getBrand().setFranchiseId(franchiseId)
                );
    }

    public static List<PortionRequestEntity> toPortionRequestEntities(Iterable<GroupDTO> groupDTOs) {
        List<PortionRequestEntity> portionRequestEntities = new ArrayList<>();
        for (GroupDTO groupDto : groupDTOs) {
            for (PortionDTO portionDTO : groupDto.getPortions()) {
                portionRequestEntities.add(
                        toPortionRequestEntityFromGroupDTO(groupDto, portionDTO));
            }
        }
        return portionRequestEntities;
    }

    private static PortionRequestEntity toPortionRequestEntityFromGroupDTO(GroupDTO groupDTO,
            PortionDTO portionDTO) {

        PortionRequestEntity portionRequest = modelMapper.map(portionDTO,
                PortionRequestEntity.class);
        portionRequest.setGroup(modelMapper.map(groupDTO, GroupRequestEntity.class));

        return portionRequest;
    }

    public static NewUserEntity toUserEntity(NewUserDTO userDTO) {
        NewUserEntity userEntity = modelMapper.map(userDTO, NewUserEntity.class);
        userEntity.setName(userDTO.getFirstName());
        return userEntity;
    }

    public static OrderFiscalIdentifierEntity toOrderFiscalIdentifierEntity(
            OrderFiscalIdentifierDTO orderFiscalIdentifierDTO
    ) {

        OrderFiscalIdentifierEntity orderFiscalIdentifierEntity = modelMapper
                .map(orderFiscalIdentifierDTO, OrderFiscalIdentifierEntity.class);

        orderFiscalIdentifierEntity.setIdentifier(orderFiscalIdentifierDTO.getFiscalIdentifier());

        return orderFiscalIdentifierEntity;
    }

    public static PerformCouponResponseEntity toCouponsApplied(PerformCouponResponseDTO couponsDTO) {
        return PerformCouponResponseEntity.builder()
                .codeId(couponsDTO.getCodeId())
                .code(couponsDTO.getCode())
                .couponType(couponsDTO.getCouponType())
                .discount(couponsDTO.getDiscount())
                .redeemedId(couponsDTO.getRedeemedId())
                .build();
    }
}
