package com.robinfood.ordereports_bc_muyapp.models.mapper;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.dto.OrderAddressDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderCouponDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderProductTaxDTO;
import com.robinfood.ordereports_bc_muyapp.dto.UserDataDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseBrandDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseCouponsDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseFinalProductDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseFinalProductGroupDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseGroupIngredientDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderAddressDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOriginDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponsePaymentDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponsePaymentDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponsePaymentMethodDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseServiceDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseStoreDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseTaxesDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseUserDataDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderEntity;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductEntity;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductPortionEntity;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderServicesEntity;
import com.robinfood.ordereports_bc_muyapp.models.entities.PaymentMethodEntity;
import com.robinfood.ordereports_bc_muyapp.models.entities.ServiceEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.app.library.constanst.GlobalConstants.DEFAULT_STRING_VALUE;
import static java.util.Objects.nonNull;

@Component
public class OrderDetailOrderMapper {

    public static ResponseGroupIngredientDTO mapIngredientToResponseDTO(
            OrderFinalProductPortionEntity portions
    ) {
        return ResponseGroupIngredientDTO.builder()
                .addition(portions.getAddition())
                .discount(portions.getDiscount())
                .free(portions.getQuantityFree())
                .id(portions.getId())
                .image(DEFAULT_STRING_VALUE)
                .name(portions.getPortionName())
                .quantity(portions.getQuantity())
                .value(portions.getBasePrice())
                .build();
    }

    public ResponseOrderDetailDTO mapOrderEntityToResponseDTO(OrderEntity order) {
        return ResponseOrderDetailDTO.builder()
                .createdAt(order.getCreatedAt().toString())
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .origin(
                        ResponseOriginDTO.builder()
                                .id(order.getOriginId())
                                .companyId(order.getCompanyId())
                                .store(ResponseStoreDTO.builder()
                                               .id(order.getStoreId())
                                               .image(DEFAULT_STRING_VALUE)
                                               .name(order.getStoreName())
                                               .build()
                                )
                                .build()
                )
                .paid(order.getPaid())
                .paymentModelId(order.getPaymentModelId())
                .payment(
                        ResponsePaymentDTO.builder()
                                .co2Total(order.getCo2Total())
                                .discount(order.getDiscounts())
                                .subtotal(order.getSubtotal())
                                .tax(order.getTaxes())
                                .total(order.getTotal())
                                .build()
                )
                .printed(order.getPrinted())
                .statusId(order.getStatusId())
                .transactionId(order.getTransactionId())
                .uid(order.getUid())
                .build();
    }


    public ResponsePaymentMethodDTO mapOrderPaymentMethodToResponseDTO(
            OrderPaymentDTO orderPayment,
            PaymentMethodEntity paymentMethod
    ) {
        return ResponsePaymentMethodDTO.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName())
                .value(orderPayment.getValue())
                .build();
    }

    public ResponseServiceDTO mapOrderServiceToResponseDTO(
            OrderServicesEntity orderService,
            ServiceEntity service
    ) {
        return ResponseServiceDTO.builder()
                .discount(orderService.getDiscount())
                .id(service.getId())
                .name(service.getName())
                .subtotal(orderService.getPriceNt())
                .tax(orderService.getTaxPrice())
                .total(orderService.getTotal())
                .build();
    }

    public ResponsePaymentDiscountDTO mapOrderDiscountToResponseDTO(OrderDiscountDTO orderDiscount) {
        return ResponsePaymentDiscountDTO.builder()
                .id(orderDiscount.getId())
                .value(orderDiscount.getDiscountValue())
                .build();
    }

    public ResponseOrderAddressDTO mapToResponseOrderAddressDTO(OrderAddressDTO orderAddressDTO) {
        if (nonNull(orderAddressDTO)) {
            return ResponseOrderAddressDTO.builder()
                    .address(orderAddressDTO.getAddress())
                    .latitude(orderAddressDTO.getLatitude())
                    .longitude(orderAddressDTO.getLongitude())
                    .notes(orderAddressDTO.getNotes())
                    .zipCode(orderAddressDTO.getZipCode())
                    .build();
        }
        return null;
    }

    public ResponseBrandDTO getBrandFinalProduct(OrderFinalProductEntity finalProduct) {
        return ResponseBrandDTO.builder()
                .brandMenuId(finalProduct.getBrandMenuId())
                .id(finalProduct.getBrandId())
                .name(finalProduct.getBrandName())
                .image(DEFAULT_STRING_VALUE)
                .build();
    }

    public ResponseFinalProductDTO mapFinalProductToResponseDTO(OrderFinalProductEntity finalProduct) {
        return ResponseFinalProductDTO.builder()
                .basePrice(finalProduct.getBasePrice())
                .brandId(finalProduct.getBrandId())
                .co2Total(finalProduct.getCo2Total())
                .discount(BigDecimal.valueOf(finalProduct.getDiscountPrice()))
                .id(finalProduct.getId())
                .image(finalProduct.getImage())
                .name(finalProduct.getFinalProductName())
                .price(finalProduct.getBasePrice())
                .productPrice(finalProduct.getProductsPrice())
                .quantity(finalProduct.getQuantity())
                .sizeId(finalProduct.getSizeId())
                .sizeName(finalProduct.getSizeName())
                .totalPrice(finalProduct.getTotal())
                .build();
    }

    public List<ResponseOrderDiscountDTO> mapFinalProductToResponseDiscountDTO(
            List<OrderDiscountDTO> orderDiscountDTO
    ) {
        return orderDiscountDTO
                .stream()
                .map(orderDiscount -> ResponseOrderDiscountDTO.builder()
                        .productId(orderDiscount.getOrderFinalProductId())
                        .typeId(orderDiscount.getOrderDiscountTypeId())
                        .value(orderDiscount.getDiscountValue())
                        .build())
                .toList();
    }

    public ResponseCouponsDTO mapFinalProductToResponseCouponDTO(
            OrderCouponDTO orderCouponDTO
    ) {
        return ResponseCouponsDTO
                .builder()
                .code(orderCouponDTO.getCode())
                .codeType(orderCouponDTO.getCouponType())
                .value(orderCouponDTO.getValue())
                .build();
    }

    public List<ResponseFinalProductGroupDTO> mapFinalProductGroupsToResponseDTO(
            List<OrderFinalProductPortionEntity> portions
    ) {
        return portions.stream()
                .collect(
                        Collectors.groupingBy(OrderFinalProductPortionEntity::getGroupId)
                )
                .values()
                .stream()
                .map(this::mapGroupToResponseDTO)
                .collect(Collectors.toList());
    }

    public ResponseFinalProductGroupDTO mapGroupToResponseDTO(
            List<OrderFinalProductPortionEntity> portions
    ) {
        Short groupId = portions.stream()
                .map(OrderFinalProductPortionEntity::getGroupId)
                .findAny()
                .orElseThrow(() -> new TransactionExecutionException("Group not found"));

        String groupName = portions.stream()
                .map(OrderFinalProductPortionEntity::getGroupName)
                .findAny()
                .orElseThrow(() -> new TransactionExecutionException("Group not found"));

        return ResponseFinalProductGroupDTO.builder()
                .id(groupId)
                .ingredients(
                        portions.stream()
                                .map(OrderDetailOrderMapper::mapIngredientToResponseDTO)
                                .collect(Collectors.toList())
                )
                .name(groupName)
                .build();
    }

    public ResponseUserDataDTO mapUserDataDTOToResponseDTO(
            UserDataDTO userDataDTO
    ) {
        return ResponseUserDataDTO.builder()
                .id(userDataDTO.getId())
                .email(userDataDTO.getEmail())
                .mobile(userDataDTO.getMobile())
                .firstName(userDataDTO.getFirstName())
                .lastName(userDataDTO.getLastName())
                .build();
    }

    public List<ResponseTaxesDTO> mapTaxesDTOToResponseDTO(
            List<OrderProductTaxDTO> orderProductTaxDTO
    ) {
        return orderProductTaxDTO.
                stream()
                .map(data -> ResponseTaxesDTO.builder()
                        .id(data.getId())
                        .familyTypeId(data.getFamilyTaxTypeId())
                        .price(data.getTaxPrice())
                        .taxTypeId(data.getTaxTypeId())
                        .taxTypeName(data.getTaxTypeName())
                        .value(data.getTaxValue())
                        .build())
                .toList();
    }
}
