package com.robinfood.core.mappers;

import com.robinfood.core.dtos.redeemcoupon.RedeemCouponPortionDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.GroupDTO;
import com.robinfood.core.dtos.transactionrequestdto.PortionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class PerformCouponProductMappers {

    private PerformCouponProductMappers() {
    }

    public static RedeemCouponProductDTO toRedeemCouponProductDTO(
            FinalProductDTO finalProduct
    ) {
        return new RedeemCouponProductDTO(
                finalProduct.getBrand().getId(),
                finalProduct.getId(),
                PerformCouponProductMappers.toListPortionDTO(
                        finalProduct.getGroups()
                )
        );
    }

    public static List<RedeemCouponPortionDTO> toListPortionDTO(
            List<GroupDTO> groups
    ) {
        List<PortionDTO> portionDTOS = new ArrayList<>();

        groups.forEach((GroupDTO groupDTO) ->
                portionDTOS.addAll(groupDTO.getPortions())
        );

        return PerformCouponProductMappers.toListRedeemCouponPortionDTO(portionDTOS);
    }

    public static List<RedeemCouponPortionDTO> toListRedeemCouponPortionDTO(
            List<PortionDTO> portions
    ) {
        return portions.stream()
                .map(PerformCouponProductMappers::toRedeemCouponPortionDTO)
                .collect(Collectors.toList());
    }

    public static RedeemCouponPortionDTO toRedeemCouponPortionDTO(
            PortionDTO portionDTO
    ) {
        return new RedeemCouponPortionDTO(
                portionDTO.getId()
        );
    }
}
