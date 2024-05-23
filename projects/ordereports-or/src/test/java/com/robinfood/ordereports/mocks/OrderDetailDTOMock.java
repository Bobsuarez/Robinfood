package com.robinfood.ordereports.mocks;

import com.robinfood.ordereports.dtos.orders.AddressDTO;
import com.robinfood.ordereports.dtos.orders.BrandDTO;
import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;
import com.robinfood.ordereports.dtos.orders.CouponDTO;
import com.robinfood.ordereports.dtos.orders.DiscountDTO;
import com.robinfood.ordereports.dtos.orders.OriginDTO;
import com.robinfood.ordereports.dtos.orders.StoreDTO;
import com.robinfood.ordereports.dtos.orders.PaymentDTO;
import com.robinfood.ordereports.dtos.orders.PaymentMethodDTO;
import com.robinfood.ordereports.dtos.orders.ProductDTO;
import com.robinfood.ordereports.dtos.orders.ProductGroupDTO;
import com.robinfood.ordereports.dtos.orders.GroupIngredientDTO;
import com.robinfood.ordereports.dtos.orders.TaxesDTO;
import com.robinfood.ordereports.dtos.orders.ServiceDTO;
import com.robinfood.ordereports.dtos.orders.UserDTO;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public final class OrderDetailDTOMock {

    public static OrderDetailDTO getDataDefault() {

        return OrderDetailDTO.builder()
                .address(getAddressDTOMock())
                .brands(getBrandDTOMock())
                .coupons(getCouponDTOMock())
                .discount(Double.valueOf(0))
                .discounts(getDiscountDTOMock())
                .flowId(3L)
                .id(1l)
                .orderNumber("40142")
                .paid(Boolean.TRUE)
                .payment(getPaymentDTOMock())
                .paymentModelId(1L)
                .printed(Boolean.FALSE)
                .products(getProductDTOMock())
                .services(getServiceDTOMock())
                .transactionUuid("e2b4ed3e-7170-4f42-a2af-140930c7b9ca")
                .uid("p334wydb4iw2")
                .user(getUserDTOMock())
                .build();
    }

    private static AddressDTO getAddressDTOMock(){
        return AddressDTO.builder()
                .address("Cra. 72 Bis #73-30, Bogotá")
                .latitude("4.689121352264889")
                .longitude("-74.0944854542613")
                .notes("APARTAMENTO 403A ")
                .zipCode("111051").build();
    }

    private static List<BrandDTO> getBrandDTOMock(){
        BrandDTO brandDTO = BrandDTO.builder()
                .brandMenuId(1L)
                .id(1L)
                .image("")
                .name("MUY").build();
        return Collections.singletonList(brandDTO);
    }

    private static List<CouponDTO> getCouponDTOMock(){
        CouponDTO couponDTO = CouponDTO.builder()
                .code("MICUPON")
                .couponType(1L)
                .value(Double.valueOf(9283)).build();
        return Collections.singletonList(couponDTO);
    }

    private static List<DiscountDTO> getDiscountDTOMock(){
        DiscountDTO discountDTO = DiscountDTO.builder()
                .value(Double.valueOf(0)).build();
        return Collections.singletonList(discountDTO);
    }

    private static OriginDTO getOriginDTOMock(){
        return OriginDTO.builder()
                .companyId(1L)
                .id(1L)
                .platformId(1L)
                .store(getStoreDTOMock()).build();
    }

    private static StoreDTO getStoreDTOMock(){
        return StoreDTO.builder()
                .id(1L)
                .name("MUY Floresta")
                .image("").build();
    }

    private static PaymentDTO getPaymentDTOMock(){
        return PaymentDTO.builder()
                .co2Total(Double.valueOf(0))
                .methods(getPaymentMethodDTOMock())
                .subtotal(Double.valueOf(30925.9259))
                .tax(Double.valueOf(2474.0741))
                .total(Double.valueOf(33400)).build();
    }

    private static List<PaymentMethodDTO> getPaymentMethodDTOMock(){
        PaymentMethodDTO paymentMethodDTO = PaymentMethodDTO.builder()
                .id(3L)
                .name("pos_credit_card")
                .value(Double.valueOf(33400)).build();
        return Collections.singletonList(paymentMethodDTO);
    }

    private static List<ProductDTO> getProductDTOMock(){
        ProductDTO productDTO = ProductDTO.builder()
                .basePrice(Double.valueOf(8900))
                .brandId(1L)
                .co2Total(BigDecimal.valueOf(0))
                .discount(BigDecimal.valueOf(0))
                .groups(getProductGroupDTOMock())
                .id(24614417L)
                .image("image")
                .name("MUY MUY Chili con carne")
                .price(Double.valueOf(16900))
                .productPrice(Double.valueOf(1000))
                .quantity(1)
                .sizeId(1L)
                .sizeName("MUY")
                .taxes(getTaxesDTOMock())
                .totalPrice(BigDecimal.valueOf(26600))
                .build();
        return Collections.singletonList(productDTO);
    }

    private static List<ProductGroupDTO> getProductGroupDTOMock(){
        ProductGroupDTO productGroupDTO = ProductGroupDTO.builder()
                .id(281L)
                .name("¿Qué quieres agregar?")
                .ingredients(getGroupIngredientDTOMock())
                .build();
        return Collections.singletonList(productGroupDTO);
    }

    private static List<GroupIngredientDTO> getGroupIngredientDTOMock(){
        GroupIngredientDTO groupIngredientDTO = GroupIngredientDTO.builder()
                .addition(Boolean.FALSE)
                .discount(Double.valueOf(0))
                .free(0L)
                .id(161622587L)
                .image("")
                .name("Chicharrón")
                .quantity(1L)
                .value(Double.valueOf(2300))
                .build();
        return Collections.singletonList(groupIngredientDTO);
    }

    private static List<TaxesDTO> getTaxesDTOMock(){
        TaxesDTO taxesDTO = TaxesDTO.builder()
                .familyTypeId(1L)
                .id(33408848L)
                .price(Double.valueOf(659.2593))
                .taxTypeId(1L)
                .taxTypeName("Impoconsumo CO")
                .value(Double.valueOf(8))
                .build();
        return Collections.singletonList(taxesDTO);
    }

    private static List<ServiceDTO> getServiceDTOMock(){
        ServiceDTO serviceDTO = ServiceDTO.builder()
                .discount(Double.valueOf(0))
                .id(5L)
                .name("Domicilios JUSTO")
                .subtotal(Double.valueOf(4537.037))
                .tax(Double.valueOf(362.963))
                .total(Double.valueOf(4900)).build();
        return Collections.singletonList(serviceDTO);
    }

    private static UserDTO getUserDTOMock(){
        return UserDTO.builder()
                .email("c-3108731876@example.muy.co")
                .firstName("CARLOS")
                .id(713742L)
                .lastName(".")
                .mobile("3108731876")
                .build();
    }
}
