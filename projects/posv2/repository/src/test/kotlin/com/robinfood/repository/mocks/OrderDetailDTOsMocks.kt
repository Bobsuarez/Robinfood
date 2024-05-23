package com.robinfood.repository.mocks

import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.OrderBuyerDTO
import com.robinfood.core.dtos.OrderDetailBrandDTO
import com.robinfood.core.dtos.OrderDetailCategory
import com.robinfood.core.dtos.OrderDetailDTO
import com.robinfood.core.dtos.OrderDetailDiscountDTO
import com.robinfood.core.dtos.OrderDetailPaymentMethodDTO
import com.robinfood.core.dtos.OrderDetailProductDTO
import com.robinfood.core.dtos.OrderDetailProductDiscountDTO
import com.robinfood.core.dtos.OrderDetailProductGroupDTO
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO
import com.robinfood.core.dtos.OrderDetailProductTaxDTO
import com.robinfood.core.dtos.OrderDetailSizeDTO
import com.robinfood.core.dtos.OrderDetailStoreDTO
import com.robinfood.core.dtos.OrderUserDTO
import com.robinfood.core.dtos.OrderThirdPartyDTO
import com.robinfood.core.entities.OrderDetailProductArticleDTO
import java.math.BigDecimal
import java.util.Collections

class OrderDetailDTOsMocks {

    private val usersDTO = OrderUserDTO(
            email = "test@email.com",
            firstName = "User",
            id = 1L,
            lastName = "Test",
            loyaltyStatus = 1L,
            mobile = "123456789"
    )

    private val buyerDTO = OrderBuyerDTO(
            identifier = "12345678901"
    )

    private val orderDetailProductDiscountDTOs = listOf(
            OrderDetailProductDiscountDTO(
                    id = 1L,
                    typeId = 1L,
                    value = BigDecimal.valueOf(500.0)
            )
    )

    private val orderDetailProductGroupPortionDTOs = listOf(
            OrderDetailProductGroupPortionDTO(
                    isIncluded = true,
                    changedPortion = null,
                    discount = BigDecimal.ZERO,
                    free = 1,
                    id = 1L,
                    name = "Portion",
                    parentId = 1L,
                    price = BigDecimal.valueOf(1000.0),
                    quantity = 1,
                    sku = "sku",
                    unitId = 1L,
                    weight = 1.0
            )
    )

    private val orderDetailProductGroupDTOs = listOf(
            OrderDetailProductGroupDTO(
                    id = 1L,
                    name = "Group",
                    portions = orderDetailProductGroupPortionDTOs,
                    removedPortions = listOf(),
                    sku = "sku"
            )
    )

    private val orderDetailProductTaxDTOs = listOf(
            OrderDetailProductTaxDTO(
                    familyTypeId = 1,
                    id = 1L,
                    price = BigDecimal.valueOf(500.0),
                    value = BigDecimal.valueOf(10.0)
            )
    )

    private val orderDetailProductDTOs = listOf(
            OrderDetailProductDTO(
                    article = OrderDetailProductArticleDTO(
                            id = 1L,
                            menuHallProductId = 1L,
                            typeId = 1L,
                            typeName = "ARTICLE"
                    ),
                    basePrice = BigDecimal.valueOf(8900.0),
                    brand = OrderDetailBrandDTO(
                            id = 1L,
                            name = "Brand"
                    ),
                    category = OrderDetailCategory(
                            id = 1L,
                            name = "Category"
                    ),
                    co2Total = BigDecimal.ZERO,
                    discounts = orderDetailProductDiscountDTOs,
                    displayType = 1L,
                    id = 1L,
                    image = "image.png",
                    name = "Product",
                    quantity = 1,
                    size = OrderDetailSizeDTO(
                            id = 1L,
                            groups = orderDetailProductGroupDTOs,
                            name = "Size",
                            suggestedGroups = emptyList()
                    ),
                    sku = "sku",
                    taxes = orderDetailProductTaxDTOs,
                    unitPrice = BigDecimal.valueOf(8900.0)
            )
    )

    private val orderDetailDiscountDTOs = listOf(
            OrderDetailDiscountDTO(
                    id = 1L,
                    typeId = 1L,
                    value = BigDecimal.valueOf(1000.0)
            )
    )

    private val orderDetailPaymentMethodDTOs = listOf(
            OrderDetailPaymentMethodDTO(
                    discount = BigDecimal.valueOf(200.0),
                    id = 1L,
                    originId = 1L,
                    subtotal = BigDecimal.valueOf(1000.0),
                    tax = BigDecimal.valueOf(200.0),
                    value = BigDecimal.valueOf(1200.0)
            )
    )

    private val orderThirdPartyDTO = OrderThirdPartyDTO(
        documentNumber = "123456",
        documentType = 1L,
        email = "test@gmail.com",
        fullName = "full name",
        phone = "3112222222"
    )

    private val electronicBill = ElectronicBillDTO(
        orderThirdParty = orderThirdPartyDTO
    )

    val orderDetailDto = OrderDetailDTO(
            buyer = buyerDTO,
            co2Total = BigDecimal.ZERO,
            crossSellingCategoryToOffer = 1L,
            crossSellingCategoriesToOffer = listOf(1L),
            currency = "COP",
            coupons = Collections.emptyList(),
            discount = BigDecimal.valueOf(2500.0),
            discounts = orderDetailDiscountDTOs,
            electronicBill = electronicBill,
            flowId = 1L,
            id = 1L,
            invoice = "123",
            orderNumber = "123",
            originId = 1L,
            originName = "Origin",
            orderUuid = "orderUuid",
            paymentMethods = orderDetailPaymentMethodDTOs,
            products = orderDetailProductDTOs,
            store = OrderDetailStoreDTO(
                    1L,
                    "Store"
            ),
            subtotal = BigDecimal.valueOf(9900.0),
            tax = BigDecimal.valueOf(100.0),
            total = BigDecimal.valueOf(10000.0),
            transactionId = 1L,
            user = usersDTO,
            transactionUuid = "transactionUuid"
    )
}