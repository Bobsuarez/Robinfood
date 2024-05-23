package com.robinfood.repository.mocks

import com.robinfood.core.entities.OrderBuyerEntity
import com.robinfood.core.entities.OrderDetailEntity
import com.robinfood.core.entities.OrderDetailDiscountEntity
import com.robinfood.core.entities.OrderDetailPaymentMethodEntity
import com.robinfood.core.entities.OrderDetailProductEntity
import com.robinfood.core.entities.OrderDetailProductDiscountEntity
import com.robinfood.core.entities.OrderDetailProductGroupEntity
import com.robinfood.core.entities.OrderDetailProductGroupPortionEntity
import com.robinfood.core.entities.OrderDetailProductTaxEntity
import com.robinfood.core.entities.OrderThirdPartyEntity
import com.robinfood.core.entities.ElectronicBillEntity
import com.robinfood.core.entities.OrderUserEntity
import java.math.BigDecimal
import java.util.Collections

class OrderDetailEntitiesMocks {

    private val usersEntity = OrderUserEntity(
        email = "test@email.com",
        firstName = "User",
        id = 1L,
        lastName = "Test",
        loyaltyStatus = 1L,
        mobile = "123456789"
    )

    private val buyerDTO = OrderBuyerEntity(
        identifier = "12345678901"
    )

    private val orderDetailProductDiscountEntities = listOf(
        OrderDetailProductDiscountEntity(
            id = 1L,
            typeId = 1L,
            value = BigDecimal.valueOf(500.0)
        )
    )

    private val orderDetailProductGroupPortionEntities = listOf(
        OrderDetailProductGroupPortionEntity(
            addition = true,
            changedPortion = null,
            discount = BigDecimal.ZERO,
            free = 0,
            id = 1L,
            name = "Portion",
            parentId = 1L,
            price = BigDecimal.valueOf(1000.0),
            quantity = 1,
            sku = "sku",
            units = 1L,
            weight = 1.0
        )
    )

    private val orderDetailProductGroupEntities = listOf(
        OrderDetailProductGroupEntity(
            id = 1L,
            name = "Group",
            portions = orderDetailProductGroupPortionEntities,
            removedPortions = listOf(),
            sku = "sku"
        )
    )

    private val orderDetailProductTaxEntities = listOf(
        OrderDetailProductTaxEntity(
            familyTypeId = 1,
            id = 1L,
            price = BigDecimal.valueOf(500.0),
            value = BigDecimal.valueOf(10.0)
        )
    )

    private val orderDetailProductEntities = listOf(
        OrderDetailProductEntity(
            articleId = 1L,
            articleName = "Article name",
            articleTypeId = 1L,
            basePrice = BigDecimal.valueOf(8900.0),
            brandMenuId = 1L,
            brandName = "Brand name",
            categoryId = 1L,
            categoryName = "Category",
            co2Total = BigDecimal.ZERO,
            discounts = orderDetailProductDiscountEntities,
            displayType = 1L,
            groups = orderDetailProductGroupEntities,
            id = 1L,
            image = "image.png",
            menuHallProductId = 1L,
            name = "Product",
            quantity = 1,
            sizeId = 1L,
            sizeName = "Size",
            sku = "sku",
            taxes = orderDetailProductTaxEntities,
            unitPrice = BigDecimal.valueOf(8900.0)
        )
    )

    private val orderDetailDiscountEntities = listOf(
        OrderDetailDiscountEntity(
            id = 1L,
            orderId = 1L,
            productId = 1L,
            typeId = 1L,
            value = BigDecimal.valueOf(1000.0)
        )
    )

    private val orderDetailPaymentMethodEntities = listOf(
        OrderDetailPaymentMethodEntity(
            discount = BigDecimal.valueOf(200.0),
            id = 1L,
            originId = 1L,
            subtotal = BigDecimal.valueOf(1000.0),
            tax = BigDecimal.valueOf(200.0),
            value = BigDecimal.valueOf(1200.0)
        )
    )

    private val orderThirdPartyEntity = OrderThirdPartyEntity(
        documentNumber = "123456",
        documentType = 1L,
        email = "test@gmail.com",
        fullName = "full name",
        phone = "3112222222"
    )

    private val electronicBillEntity = ElectronicBillEntity(
        orderThirdParty = orderThirdPartyEntity
    )

    val orderDetailEntity = OrderDetailEntity(
        buyer = buyerDTO,
        co2Total = BigDecimal.ZERO,
        currency = "COP",
        coupons = Collections.emptyList(),
        discount = BigDecimal.valueOf(2500.0),
        discounts = orderDetailDiscountEntities,
        electronicBill = electronicBillEntity,
        flowId = 1L,
        id = 1L,
        invoice = "123",
        orderNumber = "123",
        orderUuid = "orderUuid",
        originId = 1L,
        originName = "Origin",
        paymentMethods = orderDetailPaymentMethodEntities,
        products = orderDetailProductEntities,
        storeId = 1L,
        storeName = "Store",
        subtotal = BigDecimal.valueOf(9900.0),
        tax = BigDecimal.valueOf(100.0),
        total = BigDecimal.valueOf(10000.0),
        transactionId = 1L,
        user = usersEntity,
        transactionUuid = "transactionUuid"
    )

    val orderDetailIdNullEntity = OrderDetailEntity(
        buyer = buyerDTO,
        co2Total = BigDecimal.ZERO,
        currency = "COP",
        coupons = Collections.emptyList(),
        discount = BigDecimal.valueOf(2500.0),
        discounts = orderDetailDiscountEntities,
        electronicBill = electronicBillEntity,
        flowId = 1L,
        id = null,
        invoice = "123",
        orderNumber = "123",
        orderUuid = "orderUuid",
        originId = 1L,
        originName = "Origin",
        paymentMethods = orderDetailPaymentMethodEntities,
        products = orderDetailProductEntities,
        storeId = 1L,
        storeName = "Store",
        subtotal = BigDecimal.valueOf(9900.0),
        tax = BigDecimal.valueOf(100.0),
        total = BigDecimal.valueOf(10000.0),
        transactionId = 1L,
        user = usersEntity,
        transactionUuid = "transactionUuid"
    )
}