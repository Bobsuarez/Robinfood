package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.ElectronicBillDTO
import com.robinfood.core.dtos.OrderBuyerDTO
import com.robinfood.core.dtos.OrderDetailDTO
import com.robinfood.core.dtos.OrderDetailStoreDTO
import com.robinfood.core.dtos.OrderUserDTO
import com.robinfood.core.dtos.OrderThirdPartyDTO
import com.robinfood.core.entities.OrderDetailEntity
import java.math.BigDecimal

fun OrderDetailEntity.toOrderDetailDTO(): OrderDetailDTO? {
    return if (id == null) {
        null
    } else {
        val orderThirdPartyDTO = electronicBill?.orderThirdParty
        val electronicBillDTO = if (orderThirdPartyDTO == null) {
            null
        } else {
            ElectronicBillDTO(
                OrderThirdPartyDTO(
                    documentNumber = orderThirdPartyDTO.documentNumber.orEmpty(),
                    documentType = orderThirdPartyDTO.documentType ?: DEFAULT_LONG_VALUE,
                    email = orderThirdPartyDTO.email.orEmpty(),
                    fullName = orderThirdPartyDTO.fullName.orEmpty(),
                    phone =  orderThirdPartyDTO.phone.orEmpty()
                )
            )
        }

        return OrderDetailDTO(
                buyer = OrderBuyerDTO(
                        identifier = buyer?.identifier.orEmpty()
                ),
                co2Total = co2Total ?: BigDecimal.ZERO,
                crossSellingCategoryToOffer = DEFAULT_LONG_VALUE,
                crossSellingCategoriesToOffer = emptyList(),
                currency = currency.orEmpty(),
                coupons = coupons ?.map { orderCouponEntity ->
                    orderCouponEntity.toOrderCouponDTO()
                }?: emptyList(),
                discount = discount ?: BigDecimal.ZERO,
                discounts = discounts ?.mapNotNull { orderDetailDiscountEntity ->
                    orderDetailDiscountEntity.toOrderDetailDiscountDTO()
                }?: emptyList(),
                electronicBill = electronicBillDTO,
                flowId = flowId,
                id = id,
                invoice = invoice.orEmpty(),
                orderNumber = orderNumber.orEmpty(),
                orderUuid = orderUuid,
                originId = originId ?: DEFAULT_LONG_VALUE,
                originName = originName.orEmpty(),
                paymentMethods = paymentMethods ?.mapNotNull { orderDetailPaymentMethodEntity ->
                    orderDetailPaymentMethodEntity.toOrderDetailPaymentMethodDTO()
                }?: emptyList(),
                products = products.mapNotNull { orderDetailProductEntity ->
                    orderDetailProductEntity.toOrderDetailProductDTO()
                },
                store = OrderDetailStoreDTO(
                    id = storeId ?: DEFAULT_LONG_VALUE,
                    name = storeName.orEmpty()
                ),
                subtotal = subtotal ?: BigDecimal.ZERO,
                tax = tax ?: BigDecimal.ZERO,
                total = total ?: BigDecimal.ZERO,
                transactionId = transactionId,
                transactionUuid = transactionUuid,
                user = OrderUserDTO(
                        email = user?.email.orEmpty(),
                        firstName = user?.firstName.orEmpty(),
                        id = user?.id,
                        lastName = user?.lastName.orEmpty(),
                        loyaltyStatus = user?.loyaltyStatus ?: DEFAULT_LONG_VALUE,
                        mobile = user?.mobile.orEmpty()
                )
        )
    }
}