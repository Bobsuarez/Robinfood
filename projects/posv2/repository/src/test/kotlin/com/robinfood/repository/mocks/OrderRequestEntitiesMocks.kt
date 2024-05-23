package com.robinfood.repository.mocks

import com.robinfood.core.entities.transactionrequest.ArticleEntity
import com.robinfood.core.entities.transactionrequest.BrandEntity
import com.robinfood.core.entities.transactionrequest.BuyerEntity
import com.robinfood.core.entities.transactionrequest.CompanyEntity
import com.robinfood.core.entities.transactionrequest.CorporateFlagEntity
import com.robinfood.core.entities.transactionrequest.CouponEntity
import com.robinfood.core.entities.transactionrequest.DeviceEntity
import com.robinfood.core.entities.transactionrequest.DiscountEntity
import com.robinfood.core.entities.transactionrequest.FinalProductEntity
import com.robinfood.core.entities.transactionrequest.FlagsEntity
import com.robinfood.core.entities.transactionrequest.GroupEntity
import com.robinfood.core.entities.transactionrequest.IntegrationDataEntity
import com.robinfood.core.entities.transactionrequest.OrderBrandEntity
import com.robinfood.core.entities.transactionrequest.OrderEntity
import com.robinfood.core.entities.transactionrequest.OriginEntity
import com.robinfood.core.entities.transactionrequest.PaymentMethodEntity
import com.robinfood.core.entities.transactionrequest.PitsFlagEntity
import com.robinfood.core.entities.transactionrequest.PortionEntity
import com.robinfood.core.entities.transactionrequest.PortionProductEntity
import com.robinfood.core.entities.transactionrequest.ReplacementPortionEntity
import com.robinfood.core.entities.transactionrequest.ServiceEntity
import com.robinfood.core.entities.transactionrequest.SizeEntity
import com.robinfood.core.entities.transactionrequest.StoreEntity
import com.robinfood.core.entities.transactionrequest.SubmarineFlagEntity
import com.robinfood.core.entities.transactionrequest.TaxEntity
import com.robinfood.core.entities.transactionrequest.ThirdPartyEntity
import com.robinfood.core.entities.transactionrequest.TogoFlagEntity
import com.robinfood.core.entities.transactionrequest.TransactionRequestEntity
import com.robinfood.core.entities.transactionrequest.TransactionUserEntity
import java.math.BigDecimal
import java.util.*

class OrderRequestEntitiesMocks {
    private val articleEntity = ArticleEntity(
        id = 1,
        menuHallProductId = 1L,
        typeId = 1,
        typeName = "ARTICLE"
    )

    private val brandEntity = BrandEntity(
        id = 1,
        name = "MUY"
    )

    private val portionProductEntity = PortionProductEntity(
        id = 1,
        name = "Jugo"
    )

    private val replacementPortionEntity = ReplacementPortionEntity(
        id = 1,
        name = "Portion",
        product = portionProductEntity,
        sku = "sku",
        unitId = 1,
        unitNumber = 130.0
    )

    private val portionEntities = listOf(
        PortionEntity(
            discount = BigDecimal.ZERO,
            free = 1,
            id = 1,
            isIncluded = true,
            name = "Jugo",
            price = BigDecimal.valueOf(500.0),
            product = portionProductEntity,
            quantity = 1,
            replacementPortion = replacementPortionEntity,
            sku = "1223",
            unitId = 1,
            unitNumber = 1.0
        )
    )

    private val groupEntities = listOf(
        GroupEntity(
            id = 1,
            name = "Bebidas",
            portions = portionEntities,
            sku = "2332432"
        )
    )

    private val orderBrandEntity = OrderBrandEntity(
        id = 1,
        name = "RobinFood"
    )

    private val companyEntity = CompanyEntity(
        id = 1,
        currency = "COP"
    )

    private val deviceEntity = DeviceEntity(
        ip = "0.0.0.0",
        platform = 4,
        timezone = "America",
        version = "1.0.0"
    )

    private val discountsEntities = listOf(
        DiscountEntity(
            id = 1,
            value = BigDecimal.valueOf(50.0)
        )
    )

    private val sizeEntity = SizeEntity(
        id = 1,
        name = "MUY"
    )

    private val taxEntity = TaxEntity(
        dicTaxId = 1,
        familyTaxTypeId = 1,
        taxPrice = BigDecimal.valueOf(250.0),
        taxValue = BigDecimal.valueOf(10.0)
    )

    private val finalProductsEntities = listOf(
        FinalProductEntity(
            article = articleEntity,
            brand = brandEntity,
            co2Total = BigDecimal.ZERO,
            discounts = discountsEntities,
            groups = groupEntities,
            id = 1,
            image = "image.png",
            name = "Final product name",
            price = BigDecimal.valueOf(10000.0),
            quantity = 1,
            removedPortions = emptyList(),
            sku = "1234",
            size = sizeEntity,
            taxes = listOf(taxEntity),
            totalPrice = BigDecimal.valueOf(10000.0)
        )
    )

    private val corporateFlagEntity = CorporateFlagEntity(
        isActive = true
    )

    private val pitsFlagEntity = PitsFlagEntity(
        carPlate = "PLATE",
        isActive = true
    )

    private val submarineFlagEntity = SubmarineFlagEntity(
        isActive = true
    )

    private val togoFlagEntity = TogoFlagEntity(
        isActive = true,
        statusId = 1L
    )

    private val flagsEntity = FlagsEntity(
        corporate = corporateFlagEntity,
        pits = pitsFlagEntity,
        submarine = submarineFlagEntity,
        togo = togoFlagEntity
    )

    private val storeEntity = StoreEntity(
        id = 1,
        name = "Muy calle 83",
        posId = 1
    )

    private val originEntity = OriginEntity(
        id = 1,
        name = "POS V2"
    )

    private val servicesEntities = listOf(
        ServiceEntity(
            id = 1,
            value = BigDecimal.valueOf(500.0)
        )
    )

    private val couponEntities = listOf(
        CouponEntity(
            code = "TEST-COUPON",
            value = BigDecimal.valueOf(2000.0)
        )
    )

    private val ordersEntities = listOf(
        OrderEntity(
            billingResolutionId = 1,
            brand = orderBrandEntity,
            couponCriteriaInfo = emptyMap(),
            coupons = couponEntities,
            co2Total = BigDecimal.ZERO,
            deliveryTypeId = 1,
            discounts = discountsEntities,
            finalProducts = finalProductsEntities,
            flags = flagsEntity,
            flowId = 1L,
            id = 1L,
            notes = "Test",
            origin = originEntity,
            paymentModelId = 1,
            services = servicesEntities,
            store = storeEntity,
            taxCriteria = emptyMap(),
            thirdParty = ThirdPartyEntity(
                documentNumber = "1018447320",
                documentType = 1L,
                email = "test-eduardo@hotmail.com",
                fullName = "Laura Gil Herrera",
                phone = "12345679"
            ),
            total = BigDecimal.valueOf(10000.0)
        )
    )

    private val paymentMethodsEntities = listOf(
        PaymentMethodEntity(
            id = 1,
            originId = 1L,
            value = BigDecimal.valueOf(10000.0),
            detail = {}
        )
    )

    private val userEntity = TransactionUserEntity(
        email = "test@email.com",
        id = 1,
        firstName = "User",
        lastName = "Test",
        mobile = "123456789",
        phoneCode = "364551122"
    )

    private val integrationDataEntity = IntegrationDataEntity(
        code = "1",
        integrationId = "POS",
        userName = "Test user name"
    )

    private val buyerDTO = BuyerEntity(
        identifier = "12345678901"
    )

    val orderRequestEntity = TransactionRequestEntity(
        buyer = buyerDTO,
        coupons = Collections.emptyList(),
        company = companyEntity,
        co2Total = BigDecimal.ZERO,
        device = deviceEntity,
        discounts = discountsEntities,
        flowId = 1L,
        delivery = integrationDataEntity,
        orders = ordersEntities,
        paymentMethods = paymentMethodsEntities,
        paymentsPaid = Collections.emptyList(),
        total = BigDecimal.valueOf(10000.0),
        user = userEntity,
        origin = originEntity,
        totalPaidPayments = BigDecimal.ZERO,
        updateOrder = false
    )
}