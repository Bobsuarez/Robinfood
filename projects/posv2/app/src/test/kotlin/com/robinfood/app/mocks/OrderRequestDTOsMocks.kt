package com.robinfood.app.mocks

import com.robinfood.core.dtos.OrderThirdPartyDTO
import com.robinfood.core.dtos.OrderThirdPartyRequestDTO
import com.robinfood.core.dtos.transactionrequest.ArticleDTO
import com.robinfood.core.dtos.transactionrequest.BrandDTO
import com.robinfood.core.dtos.transactionrequest.BuyerDTO
import com.robinfood.core.dtos.transactionrequest.CompanyDTO
import com.robinfood.core.dtos.transactionrequest.CorporateFlagDTO
import com.robinfood.core.dtos.transactionrequest.CouponDTO
import com.robinfood.core.dtos.transactionrequest.DeviceDTO
import com.robinfood.core.dtos.transactionrequest.DiscountDTO
import com.robinfood.core.dtos.transactionrequest.FinalProductDTO
import com.robinfood.core.dtos.transactionrequest.FlagsDTO
import com.robinfood.core.dtos.transactionrequest.GroupDTO
import com.robinfood.core.dtos.transactionrequest.IntegrationDataDTO
import com.robinfood.core.dtos.transactionrequest.OrderBrandDTO
import com.robinfood.core.dtos.transactionrequest.OrderDTO
import com.robinfood.core.dtos.transactionrequest.OriginDTO
import com.robinfood.core.dtos.transactionrequest.PaymentMethodDTO
import com.robinfood.core.dtos.transactionrequest.PitsFlagDTO
import com.robinfood.core.dtos.transactionrequest.PortionDTO
import com.robinfood.core.dtos.transactionrequest.PortionProductDTO
import com.robinfood.core.dtos.transactionrequest.ServiceDTO
import com.robinfood.core.dtos.transactionrequest.SizeDTO
import com.robinfood.core.dtos.transactionrequest.StoreDTO
import com.robinfood.core.dtos.transactionrequest.SubmarineFlagDTO
import com.robinfood.core.dtos.transactionrequest.TaxDTO
import com.robinfood.core.dtos.transactionrequest.TogoFlagDTO
import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO
import com.robinfood.core.dtos.transactionrequest.TransactionUserDTO
import java.math.BigDecimal
import java.util.*

class OrderRequestDTOsMocks {
    private val articleDTO = ArticleDTO(
            id = 1L,
            menuHallProductId = 1L,
            typeId = 1L,
            typeName = "ARTICLE"
    )

    private val brandDTO = BrandDTO(
            id = 1L,
            name = "MUY"
    )

    private val portionProductDTO = PortionProductDTO(
            id = 1L,
            name = "Jugo"
    )

    private val portionDTOs = listOf(
            PortionDTO(
                    discount = BigDecimal.ZERO,
                    free = 1,
                    id = 1L,
                    isIncluded = true,
                    name = "Jugo",
                    price = BigDecimal.valueOf(500.0),
                    product = portionProductDTO,
                    quantity = 1,
                    replacementPortion = null,
                    sku = "1234",
                    unitId = 1L,
                    unitNumber = 1.0
            )
    )

    private val groupDTOS = listOf(
            GroupDTO(
                    id = 1L,
                    name = "Bebidas",
                    portions = portionDTOs,
                    sku = "1234"
            )
    )

    private val orderBrandDTO = OrderBrandDTO(
            id = 1L,
            name = "RobinFood"
    )

    private val buyerDTO = BuyerDTO(
            identifier = "12345678901"
    )

    private val companyDTO = CompanyDTO(
            id = 1L,
            currency = "COP"
    )

    private val deviceDTO = DeviceDTO(
            ip = "0.0.0.0",
            platform = 4,
            timezone = "America",
            version = "1.0.0"
    )

    private val discountsDTOs = listOf(
            DiscountDTO(
                    id = 1L,
                    value = BigDecimal.valueOf(50.0)
            )
    )

    private val sizeDTO = SizeDTO(
            id = 1L,
            name = "MUY"
    )

    private val taxesDTOs = listOf(
            TaxDTO(
                    dicTaxId = 1L,
                    familyTaxTypeId = 1L,
                    taxPrice = BigDecimal.valueOf(250.0),
                    taxValue = BigDecimal.valueOf(10.0)
            )
    )

    private val finalProductsDTOs = listOf(
            FinalProductDTO(
                    article = articleDTO,
                    brand = brandDTO,
                    co2Total = BigDecimal.ZERO,
                    discounts = discountsDTOs,
                    groups = groupDTOS,
                    id = 1L,
                    image = "image.png",
                    name = "Final product name",
                    price = BigDecimal.valueOf(10000.0),
                    quantity = 1,
                    removedPortions = emptyList(),
                    sku = "1234",
                    size = sizeDTO,
                    taxes = taxesDTOs,
                    totalPrice = BigDecimal.valueOf(10000.0)
            )
    )

    private val flagsDTO = FlagsDTO(
            corporate = CorporateFlagDTO(
                    isActive = false
            ),
            pits = PitsFlagDTO(
                    carPlate = "ABC123",
                    isActive = true
            ),
            submarine = SubmarineFlagDTO(
                    isActive = false
            ),
            togo = TogoFlagDTO(
                    isActive = false,
                    statusId = 1L
            )
    )

    private val OrderThirdPartyDTO = OrderThirdPartyRequestDTO(
        documentNumber = "1018447320",
        documentType = 1L,
        email = "test-eduardo@hotmail.com",
        fullName = "Laura Gil Herrera",
        phone = "12345679"
    )

    private val storeDTO = StoreDTO(
            id = 1L,
            name = "Muy calle 83",
            posId = 1L
    )

    private val originDTO = OriginDTO(
            id = 1L,
            name = "POS V2"
    )

    private val servicesDTOs = listOf(
            ServiceDTO(
                    id = 1L,
                    value = BigDecimal.valueOf(500.0)
            )
    )

    private val couponDTOS = listOf(
            CouponDTO(
                    code = "TEST-COUPON",
                    value = BigDecimal.valueOf(2000.0)
            )
    )

    val ordersDTOs = listOf(
            OrderDTO(
                    billingResolutionId = 1L,
                    brand = orderBrandDTO,
                    co2Total = BigDecimal.ZERO,
                    couponCriteriaInfo = emptyMap(),
                    coupons = couponDTOS,
                    deliveryTypeId = 1L,
                    discounts = discountsDTOs,
                    finalProducts = finalProductsDTOs,
                    flags = flagsDTO,
                    flowId = 1L,
                    id = 1L,
                    notes = "Test notes",
                    origin = originDTO,
                    paymentModelId = 1L,
                    services = servicesDTOs,
                    store = storeDTO,
                    taxCriteria = emptyMap(),
                    thirdParty = OrderThirdPartyDTO,
                    total = BigDecimal.valueOf(10000.0),
                    uuid = "uuid"
            )
    )

    private val paymentMethodsDTOs = listOf(
            PaymentMethodDTO(
                    id = 1L,
                    originId = 1L,
                    value = BigDecimal.valueOf(10000.0),
                    detail = null
            )
    )

    private val userDTO = TransactionUserDTO(
            email = "test@email.com",
            id = 1L,
            firstName = "User",
            lastName = "Test",
            mobile = "123456789",
            phoneCode = "364551122"
    )

    private val integrationDataDTO = IntegrationDataDTO(
            integrationId = "1",
            userName = "Test user name"
    )

    private val originIntegrationDTO = OriginDTO(
            id = 11L,
            name = "Didi"
    )


    private val paymentMethodIntegrationsDTOs = listOf(
            PaymentMethodDTO(
                    id = 8L,
                    originId = 1L,
                    value = BigDecimal.valueOf(10000.0),
                    detail = null
            )
    )

    val transactionRequest = TransactionRequestDTO(
            buyer = buyerDTO,
            company = companyDTO,
            coupons = Collections.emptyList(),
            co2Total = BigDecimal.ZERO,
            device = deviceDTO,
            discounts = discountsDTOs,
            flowId = 1L,
            integrationData = integrationDataDTO,
            orders = emptyList(),
            origin = originDTO,
            paid = true,
            paymentMethods = paymentMethodsDTOs,
            paymentsPaid = Collections.emptyList(),
            total = BigDecimal.valueOf(10000.0),
            totalPaidPayments = BigDecimal.ZERO,
            updateOrder = false,
            user = userDTO,
            uuid = "uuid"
    )

    var transactionRequestIntegrations = TransactionRequestDTO(
            buyer = buyerDTO,
            company = companyDTO,
            coupons = Collections.emptyList(),
            co2Total = BigDecimal.ZERO,
            device = deviceDTO,
            discounts = discountsDTOs,
            flowId = 1L,
            integrationData = integrationDataDTO,
            orders = emptyList(),
            origin = originIntegrationDTO,
            paid = true,
            paymentMethods = paymentMethodsDTOs,
            paymentsPaid = Collections.emptyList(),
            total = BigDecimal.valueOf(10000.0),
            totalPaidPayments = BigDecimal.ZERO,
            updateOrder = false,
            user = userDTO,
            uuid = "uuid",
    )

    var transactionResponseIntegrations = TransactionRequestDTO(
            buyer = buyerDTO,
            company = companyDTO,
            coupons = Collections.emptyList(),
            co2Total = BigDecimal.ZERO,
            device = deviceDTO,
            discounts = discountsDTOs,
            flowId = 1L,
            integrationData = integrationDataDTO,
            orders = emptyList(),
            origin = originIntegrationDTO,
            paid = true,
            paymentMethods = paymentMethodIntegrationsDTOs,
            paymentsPaid = Collections.emptyList(),
            total = BigDecimal.valueOf(10000.0),
            totalPaidPayments = BigDecimal.ZERO,
            updateOrder = false,
            user = userDTO,
            uuid = "uuid",
    )
}