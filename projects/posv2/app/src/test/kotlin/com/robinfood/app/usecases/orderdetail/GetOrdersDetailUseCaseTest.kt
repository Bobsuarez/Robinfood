package com.robinfood.app.usecases.orderdetail

import com.robinfood.app.usecases.menuproductdetail.GetProductDetailUseCase
import com.robinfood.core.dtos.*
import com.robinfood.core.dtos.flags.FeatureFlagsDTO
import com.robinfood.core.dtos.flags.FlagsDTO
import com.robinfood.core.dtos.menu.response.MenuGroupProductDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.dtos.menu.response.MenuProductSizeDTO
import com.robinfood.core.dtos.menu.response.MenuSizeGroupDTO
import com.robinfood.core.entities.OrderDetailProductArticleDTO
import com.robinfood.core.entities.menu.MenuArticleDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.orderdetail.IOrderDetailRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@ExtendWith(MockitoExtension::class)
class GetOrdersDetailUseCaseTest {

        @Mock
        private lateinit var orderDetailRepository: IOrderDetailRepository

        @InjectMocks
        private lateinit var getOrdersDetailUseCase: GetOrdersDetailUseCase

        @Mock
        private lateinit var getProductDetailUseCase: GetProductDetailUseCase

        private val orderIds: List<Long> = listOf(1)

        private val token = "token"

        private val buyerDTO = OrderBuyerDTO(
                identifier = "12345678901"
        )

        private val orderDetailProductGroupPortionDTOs = listOf(
                OrderDetailProductGroupPortionDTO(
                        changedPortion = null,
                        discount = BigDecimal.ZERO,
                        free = 1,
                        id = 1L,
                        isIncluded = false,
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

        private val listMenuSizeGroupDTO = listOf(
                MenuSizeGroupDTO(
                        id = 1,
                        name = "name",
                        namePlural = "name plural",
                        nameSingular = "name singular",
                        portions = emptyList(),
                        selectFree = 1,
                        selectMax = 1,
                        selectMin = 1,
                        selectionNamePlural = "selectionNamePlural",
                        selectionNameSingular = "selectionNameSingular",
                        sku = "sku",
                        subsidy = 123
                )
        )

        private val orderDetailProductGroupDTOsWithCrossSelling = listOf(
                OrderDetailProductGroupDTO(
                        id = 103L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 6L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
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

        private val orderDetailProductDTOs = listOf(
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 1L,
                                name = "Category"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOs,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                ),
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 2L,
                                menuHallProductId = 2L,
                                typeId = 2L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(7000.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 1L,
                                name = "Category"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = listOf(
                                OrderDetailProductDiscountDTO(
                                        1L,
                                        1L,
                                        BigDecimal.valueOf(18000.0)
                                )
                        ),
                        displayType = 1L,
                        id = 2L,
                        image = "image.png",
                        name = "Product",
                        quantity = 3,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOs,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        )

        private val orderDetailProductDTOsResponse = listOf(
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 1L,
                                name = "Category"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOs,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                ),
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 2L,
                                menuHallProductId = 2L,
                                typeId = 2L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(7000.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 1L,
                                name = "Category"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = listOf(
                                OrderDetailProductDiscountDTO(
                                        1L,
                                        1L,
                                        BigDecimal.valueOf(6000.0)
                                )
                        ),
                        displayType = 1L,
                        id = 2L,
                        image = "image.png",
                        name = "Product",
                        quantity = 3,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOs,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        )

        private val orderDetailProductDTOsWithProductsCrossSelling = listOf(
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 5L,
                                name = "Category"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = emptyList(),
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                ),
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 4L,
                                name = "Category"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = emptyList(),
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        )

        private val orderDetailProductDTOsWithGroupsCrossSelling = listOf(
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 1L,
                                name = "Category"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOsWithCrossSelling,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        )

        private val orderDetailProductsWithGroupsCrossSellingDTOs = listOf(
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 1L,
                                name = "Category"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOsWithCrossSelling,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        )

        private val orderDetailProductsWithCrossSellingDTOs = listOf(
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 5L,
                                name = "Desserts"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOsWithCrossSelling,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                ),
                OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 4L,
                                name = "Drinks"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOs,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroupDTO
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )

        )

        private val userDTO = OrderUserDTO(
                email = "test@email.com",
                firstName = "User",
                id = 1L,
                lastName = "Test",
                loyaltyStatus = 1L,
                mobile = "123456789"
        )


        private val orderDetailDto = OrderDetailDTO(
                buyer = buyerDTO,
                co2Total = BigDecimal.ZERO,
                crossSellingCategoryToOffer = -1L,
                crossSellingCategoriesToOffer = emptyList(),
                currency = "COP",
                coupons = emptyList(),
                discount = BigDecimal.valueOf(2500.0),
                discounts = emptyList(),
                electronicBill = electronicBill,
                flowId = 1L,
                id = 1L,
                invoice = "123",
                orderNumber = "123",
                orderUuid = "orderUuid",
                originId = 1L,
                originName = "Origin",
                paymentMethods = emptyList(),
                products = orderDetailProductDTOs,
                store = OrderDetailStoreDTO(
                        id = 1L,
                        name = "Store"
                ),
                subtotal = BigDecimal.valueOf(9900.0),
                tax = BigDecimal.valueOf(100.0),
                total = BigDecimal.valueOf(10000.0),
                transactionId = 1L,
                user = userDTO,
                transactionUuid = "transactionUuid",
        )

        private val orderDetailDtoWithProductsCrossSelling = OrderDetailDTO(
                buyer = buyerDTO,
                co2Total = BigDecimal.ZERO,
                crossSellingCategoryToOffer = -1L,
                crossSellingCategoriesToOffer = emptyList(),
                currency = "COP",
                coupons = emptyList(),
                discount = BigDecimal.valueOf(2500.0),
                discounts = emptyList(),
                electronicBill = electronicBill,
                flowId = 1L,
                id = 1L,
                invoice = "123",
                orderNumber = "123",
                orderUuid = "orderUuid",
                originId = 1L,
                originName = "Origin",
                paymentMethods = emptyList(),
                products = orderDetailProductDTOsWithProductsCrossSelling,
                store = OrderDetailStoreDTO(
                        id = 1L,
                        name = "Store"
                ),
                subtotal = BigDecimal.valueOf(9900.0),
                tax = BigDecimal.valueOf(100.0),
                total = BigDecimal.valueOf(10000.0),
                transactionId = 1L,
                user = userDTO,
                transactionUuid = "transactionUuid",
        )

        private val orderDetailDtoWithGroupsCrossSelling = OrderDetailDTO(
                buyer = buyerDTO,
                co2Total = BigDecimal.ZERO,
                crossSellingCategoryToOffer = -1L,
                crossSellingCategoriesToOffer = emptyList(),
                currency = "COP",
                coupons = emptyList(),
                discount = BigDecimal.valueOf(2500.0),
                discounts = emptyList(),
                electronicBill = electronicBill,
                flowId = 1L,
                id = 1L,
                invoice = "123",
                orderNumber = "123",
                orderUuid = "orderUuid",
                originId = 1L,
                originName = "Origin",
                paymentMethods = emptyList(),
                products = orderDetailProductDTOsWithGroupsCrossSelling,
                store = OrderDetailStoreDTO(
                        id = 2L,
                        name = "Store"
                ),
                subtotal = BigDecimal.valueOf(9900.0),
                tax = BigDecimal.valueOf(100.0),
                total = BigDecimal.valueOf(10000.0),
                transactionId = 1L,
                user = userDTO,
                transactionUuid = "transactionUuid",
        )

        private val orderDetailDTOsResponse = listOf(
                OrderDetailDTO(
                        buyer = buyerDTO,
                        co2Total = BigDecimal.ZERO,
                        crossSellingCategoryToOffer = 4L,
                        crossSellingCategoriesToOffer = listOf(5L, 4L),
                        currency = "COP",
                        coupons = emptyList(),
                        discount = BigDecimal.valueOf(2500.0),
                        discounts = emptyList(),
                        electronicBill = electronicBill,
                        flowId = 1L,
                        id = 1L,
                        invoice = "123",
                        orderNumber = "123",
                        orderUuid = "orderUuid",
                        originId = 1L,
                        originName = "Origin",
                        paymentMethods = emptyList(),
                        products = orderDetailProductDTOsResponse,
                        showCrossSelling = false,
                        store = OrderDetailStoreDTO(
                                id = 1L,
                                name = "Store"
                        ),
                        subtotal = BigDecimal.valueOf(9900.0),
                        tax = BigDecimal.valueOf(100.0),
                        total = BigDecimal.valueOf(10000.0),
                        transactionId = 1L,
                        user = userDTO,
                        transactionUuid = "transactionUuid",
                )
        )

        private val orderDetailDtoWithCrossSelling = OrderDetailDTO(
                buyer = buyerDTO,
                co2Total = BigDecimal.ZERO,
                crossSellingCategoryToOffer = -1L,
                crossSellingCategoriesToOffer = emptyList(),
                currency = "COP",
                coupons = emptyList(),
                discount = BigDecimal.valueOf(2500.0),
                discounts = emptyList(),
                electronicBill = electronicBill,
                flowId = 1L,
                id = 1L,
                invoice = "123",
                orderNumber = "123",
                orderUuid = "orderUuid",
                originId = 1L,
                originName = "Origin",
                paymentMethods = emptyList(),
                products = orderDetailProductsWithCrossSellingDTOs,
                store = OrderDetailStoreDTO(
                        id = 1L,
                        name = "Store"
                ),
                subtotal = BigDecimal.valueOf(9900.0),
                tax = BigDecimal.valueOf(100.0),
                total = BigDecimal.valueOf(10000.0),
                transactionId = 1L,
                user = userDTO,
                transactionUuid = "transactionUuid",
        )

        private val orderDetailDtoWithProductsCrossSellingResponse = OrderDetailDTO(
                buyer = buyerDTO,
                co2Total = BigDecimal.ZERO,
                crossSellingCategoryToOffer = 4L,
                crossSellingCategoriesToOffer = listOf(5L, 4L),
                currency = "COP",
                coupons = emptyList(),
                discount = BigDecimal.valueOf(2500.0),
                discounts = emptyList(),
                electronicBill = electronicBill,
                flowId = 1L,
                id = 1L,
                invoice = "123",
                orderNumber = "123",
                orderUuid = "orderUuid",
                originId = 1L,
                originName = "Origin",
                paymentMethods = emptyList(),
                products = orderDetailProductDTOsWithProductsCrossSelling,
                showCrossSelling = false,
                store = OrderDetailStoreDTO(
                        id = 1L,
                        name = "Store"
                ),
                subtotal = BigDecimal.valueOf(9900.0),
                tax = BigDecimal.valueOf(100.0),
                total = BigDecimal.valueOf(10000.0),
                transactionId = 1L,
                user = userDTO,
                transactionUuid = "transactionUuid",
        )

        private val orderDetailDtoWithGroupsCrossSellingResponse = OrderDetailDTO(
                buyer = buyerDTO,
                co2Total = BigDecimal.ZERO,
                crossSellingCategoryToOffer = -1L,
                crossSellingCategoriesToOffer = emptyList(),
                currency = "COP",
                coupons = emptyList(),
                discount = BigDecimal.valueOf(2500.0),
                discounts = emptyList(),
                electronicBill = electronicBill,
                flowId = 1L,
                id = 1L,
                invoice = "123",
                orderNumber = "123",
                orderUuid = "orderUuid",
                originId = 1L,
                originName = "Origin",
                paymentMethods = emptyList(),
                products = orderDetailProductsWithGroupsCrossSellingDTOs,
                showCrossSelling = false,
                store = OrderDetailStoreDTO(
                        id = 2L,
                        name = "Store"
                ),
                subtotal = BigDecimal.valueOf(9900.0),
                tax = BigDecimal.valueOf(100.0),
                total = BigDecimal.valueOf(10000.0),
                transactionId = 1L,
                user = userDTO,
                transactionUuid = "transactionUuid",
        )

        private val orderDetailDtoWithCrossSellingResponse = OrderDetailDTO(
                buyer = buyerDTO,
                co2Total = BigDecimal.ZERO,
                crossSellingCategoryToOffer = -1L,
                crossSellingCategoriesToOffer = emptyList(),
                currency = "COP",
                coupons = emptyList(),
                discount = BigDecimal.valueOf(2500.0),
                discounts = emptyList(),
                electronicBill = electronicBill,
                flowId = 1L,
                id = 1L,
                invoice = "123",
                orderNumber = "123",
                orderUuid = "orderUuid",
                originId = 1L,
                originName = "Origin",
                paymentMethods = emptyList(),
                products = orderDetailProductsWithCrossSellingDTOs,
                showCrossSelling = false,
                store = OrderDetailStoreDTO(
                        id = 1L,
                        name = "Store"
                ),
                subtotal = BigDecimal.valueOf(9900.0),
                tax = BigDecimal.valueOf(100.0),
                total = BigDecimal.valueOf(10000.0),
                transactionId = 1L,
                user = userDTO,
                transactionUuid = "transactionUuid",
        )

        private val orderDetailDTOs = listOf(orderDetailDto)

        private val orderDetailDTOsWithGroupsCrossSelling = listOf(orderDetailDtoWithGroupsCrossSelling)

        private val orderDetailDTOsWithProductsCrossSelling = listOf(orderDetailDtoWithProductsCrossSelling)

        private val orderDetailWithCrossSellingDTOs = listOf(orderDetailDtoWithCrossSelling)

        private val orderDetailWithGroupsCrossSellingDTOsResponse = listOf(orderDetailDtoWithGroupsCrossSellingResponse)

        private val orderDetailWithProductsCrossSellingDTOsResponse =
                listOf(orderDetailDtoWithProductsCrossSellingResponse)

        private val orderDetailWithCrossSellingDTOsResponse = listOf(orderDetailDtoWithCrossSellingResponse)

        private val countryId = 1L
        private val flowId = 1L
        private val storeId = 1L
        private val platformId = 1L

        private val flags = addFlags(countryId, platformId)

        @BeforeEach
        fun setUp() {
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "dessertsCrossSellingCategoriesIds", listOf(5L))
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "drinksCrossSellingCategoriesIds", listOf(4L))
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "dessertsGroupsCrossSellingIds", listOf(103L))
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "drinksGroupsCrossSellingIds", listOf(6L))
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "toppingGroupsCrossSellingIds", listOf(11L, 12L))
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "portionIdsTop", listOf(1376L))
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "crossSellingStore", listOf(2, 27))
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "secondsInTimerCross", 5)
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "crossSellingStartTimePopup", 15)
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "crossSellingEndTimePopup", 23)
        }

        @Test
        fun `test that get orders detail use case returns correctly`() {
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTOs))

                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailDTOsResponse, flags)), result)
                }
        }

        @Test
        fun `test that get orders detail use case returns correctly cross-selling in groups`() {

                var zoneId = "America/Bogota";
                val featureFlag1 = FeatureFlagsDTO("showPopupCrossSellingDessertsAndDrinks", true,1)
                val featureFlag2 = FeatureFlagsDTO("showPopupCrossSellingToppings", true, 1)
                val featureFlag3 = FeatureFlagsDTO("timeToStartTimerCross",getCurrentDateTimeInZone(zoneId, 15, 0), 2)
                val featureFlag4 = FeatureFlagsDTO("timeToEndTimerCross", getCurrentDateTimeInZone(zoneId, 23, 0), 2)
                val featureFlag5 = FeatureFlagsDTO("secondsInTimerCross", 5, 3)
                val flagsDTO = FlagsDTO(
                        countryId = 3L,
                        featureFlags = listOf(featureFlag1, featureFlag2, featureFlag3, featureFlag4, featureFlag5),
                        platformId = platformId
                )

                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        3L,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTOsWithGroupsCrossSelling))

                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                3L,
                                flowId,
                                storeId,
                                platformId
                        )
                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailWithGroupsCrossSellingDTOsResponse, flagsDTO)), result)
                }
        }

        @Test
        fun `test that get orders detail use case returns correctly cross-selling in products`() {
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTOsWithProductsCrossSelling))

                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )
                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailWithProductsCrossSellingDTOsResponse, flags)), result)
                }
        }

        @Test
        fun `test that get orders detail use case returns correctly with cross selling`() {
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailWithCrossSellingDTOs))

                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailWithCrossSellingDTOsResponse, flags)), result)
                }
        }

        @Test
        fun `test that get orders detail use case returns error`() {
                runBlocking {
                        assertThrows<ResponseStatusException> {
                                `when`(
                                        orderDetailRepository.getOrdersDetail(
                                                token,
                                                countryId,
                                                flowId,
                                                orderIds
                                        )
                                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                                getOrdersDetailUseCase.invoke(
                                        token,
                                        orderIds,
                                        countryId,
                                        flowId,
                                        storeId,
                                        platformId
                                )
                        }
                }
        }

        // ---------------------- TEST-CROSS-SELLING-TOPPING----------------------------

        @Test
        fun `test that get orders have desserts detail witch cross selling topping use case returns correctly`() {

                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingDesserts, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))
                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                true,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders have drinks detail witch cross selling topping use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingDrinks, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))

                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                5L,
                                listOf(5L)
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                true,
                                true,
                                5L,
                                listOf(5L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders have drinks detail witch drink desserts and toppings returns correctly`() {

                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithDrinksAndDesserts, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))

                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                5L,
                                emptyList()
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                true,
                                true,
                                5L,
                                emptyList()
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders have drinks and topping detail witch cross selling topping use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingDrinksAndTopping, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))

                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                5L,
                                listOf(5L)
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                true,
                                true,
                                5L,
                                listOf(5L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders have desserts and use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingDesserts, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))

                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                4L,
                                listOf(4L)
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                true,
                                true,
                                4L,
                                listOf(4L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders have desserts topping and use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingDessertsAndTopping, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))

                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                4L,
                                listOf(4L)
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                true,
                                true,
                                4L,
                                listOf(4L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders have topping and use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingTopping, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))

                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                4L,
                                listOf(4L)
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                false,
                                false,
                                4L,
                                listOf(5, 4L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders have  topping detail witch cross selling topping use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingHaveTopping, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))

                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                4L,
                                listOf(5L, 4L)
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                false,
                                false,
                                4L,
                                listOf(5L, 4L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }



        @Test
        fun `test that get orders without drinks without desserts without topping detail witch cross selling topping use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingWithoutDrinkAndTopping, createOrderDetailCategory(36,"category_36"), createOrderDetailBrand(12, "brand_12")))

                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                4L,
                                false,
                                false,
                                false,
                                4L,
                                listOf(5L, 4L)
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                4L,
                                true,
                                true,
                                true,
                                4L,
                                listOf(5L, 4L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }


        @Test
        fun `test that get orders when products is null detail use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingDesserts, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))
                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                emptyList(),
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                emptyList(),
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                4L,
                                listOf(5L, 4L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders when products is greater than 1 detail use case returns correctly`() {

                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingDesserts, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")),
                                createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingDesserts, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))
                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        @Test
        fun `test that get orders when products incorrect brand detail use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingToppingIncorrectBrand(orderDetailProductGroupDTOsWithCrossSellingToppingDesserts))
                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }


        @Test
        fun `test that get orders when products incorrect category detail use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingToppingIncorrectCategory(orderDetailProductGroupDTOsWithCrossSellingToppingDesserts))
                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }


        @Test
        fun `test that get orders with detail product error use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingToppingDesserts, createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")))
                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                true,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }


        @Test
        fun `test that get orders with detail product the menu product size not found  use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping =
                        listOf(createOrderDetailProductDTOWithSizeIsEmpty(orderDetailProductGroupDTOsWithCrossSellingToppingDesserts))
                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                -1L,
                                emptyList()
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                true,
                                true,
                                4L,
                                listOf(5L, 4L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }


        @Test
        fun `test that get orders have group is empty cross selling topping use case returns correctly`() {
                val listOrderDetailProductsWithCrossSellingTopping = listOf(createOrderDetailProductDTOWithCrossSellingTopping(
                        emptyList(), createOrderDetailCategory(3,"category_3"), createOrderDetailBrand(1, "brand_1")
                ))
                ReflectionTestUtils.setField(getOrdersDetailUseCase, "toppingGroupsCrossSellingIds", listOf(15L))
                val orderDetailDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                false,
                                false,
                                false,
                                5L,
                                listOf(5L)
                        )
                )
                val orderDetailResponseDTO = listOf(
                        createOrderDetailDTO(
                                buyerDTO,
                                listOrderDetailProductsWithCrossSellingTopping,
                                userDTO,
                                2L,
                                true,
                                true,
                                true,
                                4L,
                                listOf(5L, 4L)
                        )
                )
                runBlocking {
                        `when`(
                                orderDetailRepository.getOrdersDetail(
                                        token,
                                        countryId,
                                        flowId,
                                        orderIds
                                )
                        ).thenReturn(Result.Success(orderDetailDTO))

                        val orderDetailProduct = orderDetailDTO.first().products.first()
                        `when`(
                                getProductDetailUseCase.invoke(
                                        token,
                                        countryId,
                                        orderDetailProduct.brand.id,
                                        flowId,
                                        orderDetailDTO.first().id,
                                        null,
                                        listOf(orderDetailProduct.article.menuHallProductId)
                                )
                        ).thenReturn(Result.Success(menuProductDetailDto))


                        val result = getOrdersDetailUseCase.invoke(
                                token,
                                orderIds,
                                countryId,
                                flowId,
                                storeId,
                                platformId
                        )

                        assertEquals(Result.Success(OrderDetailWithFlagsDTO(orderDetailResponseDTO, flags)), result)
                }
        }

        fun createOrderDetailDTO(
                buyer: OrderBuyerDTO,
                orderDetailProductDTOs: List<OrderDetailProductDTO>,
                userDTO: OrderUserDTO,
                originId: Long,
                showCrossSelling: Boolean,
                showCrossSellingTopping: Boolean,
                mixedFlowCrossSelling: Boolean,
                crossSellingCategoryToOffer: Long,
                crossSellingCategoriesToOffer: List<Long>): OrderDetailDTO {
                return OrderDetailDTO(
                        buyer = buyer,
                        co2Total = BigDecimal.ZERO,
                        crossSellingCategoryToOffer = crossSellingCategoryToOffer,
                        crossSellingCategoriesToOffer = crossSellingCategoriesToOffer,
                        currency = "COP",
                        coupons = emptyList(),
                        discount = BigDecimal.valueOf(2500.0),
                        discounts = emptyList(),
                        electronicBill = electronicBill,
                        flowId = 1L,
                        id = 1L,
                        invoice = "123",
                        orderNumber = "123",
                        orderUuid = "orderUuid",
                        originId = originId,
                        originName = "Origin",
                        paymentMethods = emptyList(),
                        products = orderDetailProductDTOs,
                        store = OrderDetailStoreDTO(
                                id = 1L,
                                name = "Store"
                        ),
                        subtotal = BigDecimal.valueOf(9900.0),
                        tax = BigDecimal.valueOf(100.0),
                        total = BigDecimal.valueOf(10000.0),
                        transactionId = 1L,
                        user = userDTO,
                        transactionUuid = "transactionUuid",
                        showCrossSelling = showCrossSelling,
                        showCrossSellingTopping = showCrossSellingTopping,
                        mixedFlowCrossSelling = mixedFlowCrossSelling
                )
        }

        private val listMenuSizeGroup = listOf(
                MenuSizeGroupDTO(
                        id = 1,
                        name = "name",
                        namePlural = "name plural",
                        nameSingular = "name singular",
                        portions = emptyList(),
                        selectFree = 1,
                        selectMax = 1,
                        selectMin = 1,
                        selectionNamePlural = "selectionNamePlural",
                        selectionNameSingular = "selectionNameSingular",
                        sku = "sku",
                        subsidy = 123
                )
        )

        private val orderDetailProductGroupDTOsWithCrossSellingToppingDesserts = listOf(
                OrderDetailProductGroupDTO(
                        id = 103L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 6L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )

        private val orderDetailProductGroupDTOsWithCrossSellingToppingDrinks = listOf(
                OrderDetailProductGroupDTO(
                        id = 1030L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 6L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )

        private val orderDetailProductGroupDTOsWithDrinksAndDesserts = listOf(
                OrderDetailProductGroupDTO(
                        id = 103L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 6L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 11L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )

        private val orderDetailProductGroupDTOsWithCrossSellingToppingDrinksAndTopping = listOf(
                OrderDetailProductGroupDTO(
                        id = 11L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 6L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )

        private val orderDetailProductGroupDTOsWithCrossSellingDesserts = listOf(
                OrderDetailProductGroupDTO(
                        id = 103L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )

        private val orderDetailProductGroupDTOsWithCrossSellingDessertsAndTopping = listOf(
                OrderDetailProductGroupDTO(
                        id = 103L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 11L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )

        private val orderDetailProductGroupDTOsWithCrossSellingTopping = listOf(
                OrderDetailProductGroupDTO(
                        id = 11L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )

        private val orderDetailProductGroupDTOsWithCrossSellingToppingHaveTopping = listOf(
                OrderDetailProductGroupDTO(
                        id = 11L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 1089L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )


        private val orderDetailProductGroupDTOsWithCrossSellingToppingWithoutDrinkAndTopping = listOf(
                OrderDetailProductGroupDTO(
                        id = 1190L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                ),
                OrderDetailProductGroupDTO(
                        id = 1089L,
                        name = "Group",
                        portions = orderDetailProductGroupPortionDTOs,
                        removedPortions = listOf(),
                        sku = "sku"
                )
        )

        fun createOrderDetailProductDTOWithCrossSellingTopping(orderDetailProductGroupDTOsWithCrossSellingTopping: List<OrderDetailProductGroupDTO>,
                category: OrderDetailCategory,
                brand: OrderDetailBrandDTO
        ): OrderDetailProductDTO {
                return OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = brand,
                        category = category,
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOsWithCrossSellingTopping,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroup
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        }

        fun createOrderDetailProductDTOWithSizeIsEmpty(
                orderDetailProductGroupDTOsWithCrossSellingTopping: List<OrderDetailProductGroupDTO>
        ): OrderDetailProductDTO {
                return OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 3L,
                                name = "Desserts"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1000L,
                                groups = emptyList(),
                                name = "Size",
                                suggestedGroups = emptyList()
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        }

        fun createOrderDetailProductDTOWithCrossSellingToppingIncorrectBrand(
                orderDetailProductGroupDTOsWithCrossSellingTopping: List<OrderDetailProductGroupDTO>
        ): OrderDetailProductDTO {
                return OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 10L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 3L,
                                name = "Desserts"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOsWithCrossSellingTopping,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroup
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        }

        fun createOrderDetailProductDTOWithCrossSellingToppingIncorrectCategory(
                orderDetailProductGroupDTOsWithCrossSellingTopping: List<OrderDetailProductGroupDTO>
        ): OrderDetailProductDTO {
                return OrderDetailProductDTO(
                        OrderDetailProductArticleDTO(
                                id = 1L,
                                menuHallProductId = 1L,
                                typeId = 1L,
                                typeName = "ARTICLE"
                        ),
                        basePrice = BigDecimal.valueOf(8900.0),
                        brand = OrderDetailBrandDTO(
                                id = 1L,
                                name = "Brand name"
                        ),
                        category = OrderDetailCategory(
                                id = 10L,
                                name = "Desserts"
                        ),
                        co2Total = BigDecimal.ZERO,
                        discounts = emptyList(),
                        displayType = 1L,
                        id = 1L,
                        image = "image.png",
                        name = "Product",
                        quantity = 1,
                        size = OrderDetailSizeDTO(
                                id = 1L,
                                groups = orderDetailProductGroupDTOsWithCrossSellingTopping,
                                name = "Size",
                                suggestedGroups = listMenuSizeGroup
                        ),
                        sku = "sku",
                        taxes = emptyList(),
                        unitPrice = BigDecimal.valueOf(8900.0)
                )
        }

        private val menuProductDetailDto: MenuProductDetailDTO = MenuProductDetailDTO(
                brand = MenuBrandDTO(
                        id = 1L,
                        name = "name"
                ),
                description = "description",
                displayType = 1L,
                flowType = "flowType",
                id = 1L,
                image = "image",
                name = "name",
                productCategoryId = 1L,
                sizes = listOf(
                        MenuProductSizeDTO(
                                article = MenuArticleDTO(
                                        id = 1L,
                                        menuHallProductId = 1L,
                                        typeId = 1L,
                                        typeName = "typeName"
                                ),
                                discount = BigDecimal("5"),
                                groups = listOf(MenuSizeGroupDTO(
                                        id = 11L,
                                        name = "valor",
                                        namePlural = "valor",
                                        nameSingular = "valor",
                                        portions = listOf(
                                                MenuGroupProductDTO(
                                                        appliesSubsidyIfRemoved = true,
                                                        discount = BigDecimal("123"),
                                                        id = 1L,
                                                        imageUrl = "imageurl",
                                                        isIncluded = true,
                                                        name = "name",
                                                        parentId = 1,
                                                        price = BigDecimal("123"),
                                                        selectionType = 1,
                                                        sku = "sku",
                                                        suggestedPortions = emptyList(),
                                                        unitId = 1L,
                                                        weight = 1.0
                                                ),
                                                MenuGroupProductDTO(
                                                        appliesSubsidyIfRemoved = true,
                                                        discount = BigDecimal("123"),
                                                        id = 2L,
                                                        imageUrl = "imageurl",
                                                        isIncluded = true,
                                                        name = "name",
                                                        parentId = 1,
                                                        price = BigDecimal("123"),
                                                        selectionType = 1,
                                                        sku = "sku",
                                                        suggestedPortions = emptyList(),
                                                        unitId = 2L,
                                                        weight = 1.0
                                                ),
                                                MenuGroupProductDTO(
                                                        appliesSubsidyIfRemoved = true,
                                                        discount = BigDecimal("123"),
                                                        id = 1376L,
                                                        imageUrl = "imageurl",
                                                        isIncluded = true,
                                                        name = "name",
                                                        parentId = 1,
                                                        price = BigDecimal("123"),
                                                        selectionType = 1,
                                                        sku = "sku",
                                                        suggestedPortions = emptyList(),
                                                        unitId = 2L,
                                                        weight = 1.0
                                                )
                                        ),
                                        selectFree = 1,
                                        selectMax = 1,
                                        selectMin = 1,
                                        selectionNamePlural = "valor",
                                        selectionNameSingular = "valor",
                                        sku = "valor",
                                        subsidy = 1
                                )),
                                id = 1L,
                                name = "name",
                                price = BigDecimal("123")
                        )
                ),
                sku = "sku"
        )


        private fun addFlags(countryId: Long,  platformId: Long?): FlagsDTO {
                var featureFlag1 = FeatureFlagsDTO("showPopupCrossSellingDessertsAndDrinks", false, 1)
                var featureFlag2 = FeatureFlagsDTO("showPopupCrossSellingToppings", false, 1)
                var featureFlag3 = FeatureFlagsDTO("timeToStartTimerCross", null, 2)
                var featureFlag4 = FeatureFlagsDTO("timeToEndTimerCross", null, 2)
                var featureFlag5 = FeatureFlagsDTO("secondsInTimerCross", null, 3)
                return FlagsDTO(
                        countryId = countryId,
                        featureFlags = listOf(featureFlag1, featureFlag2, featureFlag3, featureFlag4, featureFlag5),
                        platformId = platformId
                )
        }

        fun createOrderDetailCategory(): OrderDetailCategory {
                return OrderDetailCategory(
                        id = 3L,
                        name = "Desserts"
                )
        }

        private fun createOrderDetailCategory(id: Long, name: String): OrderDetailCategory {
                return OrderDetailCategory(
                        id = id,
                        name = name
                )
        }

        private fun createOrderDetailBrand(id: Long, name: String): OrderDetailBrandDTO{
                return OrderDetailBrandDTO(
                        id = id,
                        name = name
                )
        }
        private fun getCurrentDateTimeInZone(zoneId: String, hour: Int, minute: Int): String {
                val timeZone = ZoneId.of(zoneId)
                val currentDate = LocalDate.now(timeZone)
                val dateTime = ZonedDateTime.of(currentDate, LocalTime.of(hour, minute), timeZone)
                return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }
}