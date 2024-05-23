package com.robinfood.app.usecases.orderdetail

import com.robinfood.app.usecases.menuproductdetail.IGetProductDetailUseCase
import com.robinfood.core.constants.GlobalConstants.COLOMBIA
import com.robinfood.core.dtos.OrderDetailDTO
import com.robinfood.core.dtos.OrderDetailProductDTO
import com.robinfood.core.dtos.OrderDetailWithFlagsDTO
import com.robinfood.core.dtos.flags.FeatureFlagsDTO
import com.robinfood.core.dtos.flags.FlagsDTO
import com.robinfood.core.dtos.menu.response.MenuGroupProductDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.dtos.menu.response.MenuSizeGroupDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.orderdetail.IOrderDetailRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Implementation of IGetOrdersDetailUseCase
 */
class GetOrdersDetailUseCase(
    private val orderDetailRepository: IOrderDetailRepository,
    private val getProductDetailUseCase: IGetProductDetailUseCase
) : IGetOrdersDetailUseCase {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Value("#{'\${app.cross-selling-desserts-halls}'.split(',')}")
    private val dessertsCrossSellingCategoriesIds: List<Long> = listOf()

    @Value("#{'\${app.cross-selling-drinks-halls}'.split(',')}")
    private val drinksCrossSellingCategoriesIds: List<Long> = listOf()

    @Value("#{'\${app.cross-selling-desserts-groups}'.split(',')}")
    private val dessertsGroupsCrossSellingIds: List<Long> = listOf()

    @Value("#{'\${app.cross-selling-drinks-groups}'.split(',')}")
    private val drinksGroupsCrossSellingIds: List<Long> = listOf()

    @Value("#{'\${app.cross-selling-toppings-groups}'.split(',')}")
    private val toppingGroupsCrossSellingIds: List<Long> = listOf()

    @Value("#{'\${app.cross-selling-top-toppings}'.split(',')}")
    private val portionIdsTop: List<Long> = listOf()

    @Value("#{'\${app.cross-selling-store}'.split(',')}")
    private val crossSellingStore: List<Long> = listOf()

    @Value("\${app.cross-selling-second-timer-popup}")
    private val secondsInTimerCross: Int = 0

    @Value("\${app.cross-selling-start-time-popup}")
    private val crossSellingStartTimePopup: Int = 0

    @Value("\${app.cross-selling-end-time-popup}")
    private val crossSellingEndTimePopup: Int = 0



    override suspend fun invoke(
        token: String,
        orderIds: List<Long>,
        countryId: Long,
        flowId: Long,
        storeId: Long,
        platformId: Long?
    ): Result<OrderDetailWithFlagsDTO> {

        val result = orderDetailRepository.getOrdersDetail(
            token,
            countryId,
            flowId,
            orderIds
        )

        val orders: List<OrderDetailDTO>

        when (result) {
            is Result.Success -> orders = result.data
            is Result.Error -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                result.exception.localizedMessage
            )
        }
        val order = orders.first()
        addCrossSellingFlag(order)
        setProductDiscountPrice(order)
        applyCrossSellingTopping(token, countryId, flowId, order)

        val orderDetailWithFlagsDTO = OrderDetailWithFlagsDTO(orders,
            getFlagsResult(orders, countryId, platformId))
        return Result.Success(orderDetailWithFlagsDTO)
    }

    /// Iterates the products in order to mark if should show cross selling flow
    private fun addCrossSellingFlag(order: OrderDetailDTO) {
        val orderHasDrinksInGroups = orderContainsGroupsWithIds(drinksGroupsCrossSellingIds, order)
        val orderHasDrinksInProducts = orderContainsProductsWithCategoryIds(drinksCrossSellingCategoriesIds, order)
        val orderHasDessertsInGroups = orderContainsGroupsWithIds(dessertsGroupsCrossSellingIds, order)
        val orderHasDessertsInProducts = orderContainsProductsWithCategoryIds(dessertsCrossSellingCategoriesIds, order)
        val crossSellingCategoriesToOffer : MutableList<Long> = mutableListOf()

        log.info("orderHasDrinksInProducts = $orderHasDrinksInProducts")
        log.info("orderHasDessertsInProducts = $orderHasDessertsInProducts")
        if (!orderHasDessertsInGroups) {
            order.crossSellingCategoryToOffer = dessertsCrossSellingCategoriesIds.first()
            crossSellingCategoriesToOffer.addAll(dessertsCrossSellingCategoriesIds)
        }

        if (!orderHasDrinksInGroups) {
            order.crossSellingCategoryToOffer = drinksCrossSellingCategoriesIds.first()
            crossSellingCategoriesToOffer.addAll(drinksCrossSellingCategoriesIds)
        }

        order.crossSellingCategoriesToOffer = crossSellingCategoriesToOffer
    }

    /// Returns true in case some of the order's products belongs to any categories given
    private fun orderContainsProductsWithCategoryIds(categories: List<Long>, order: OrderDetailDTO): Boolean {
        for (product in order.products) {
            if (categories.contains(product.category.id)) {
                return true
            }
        }
        return false
    }

    /// Returns true in case some of the order's products portions belongs to any groups given
    private fun orderContainsGroupsWithIds(groupsIds: List<Long>, order: OrderDetailDTO): Boolean {
        for (product in order.products) {
            for (group in product.size.groups) {
                if (groupsIds.contains(group.id)) {
                    return true
                }
            }
        }
        return false
    }

    /// Divide each product discount by the product quantity since in the database it is already multiplied
    private fun setProductDiscountPrice(order: OrderDetailDTO) {
        for (product in order.products) {
            for (discount in product.discounts) {
                discount.value = discount.value.divide(BigDecimal(product.quantity.toString()))
            }
        }
    }

    private suspend fun applyCrossSellingTopping(token: String, countryId: Long, flowId: Long, order: OrderDetailDTO) {
        log.info("function applyCrossSellingTopping...")
        log.info("originId {} ", order.originId)

        if (order.originId != 2L && order.originId != 4L) {
            log.info("originId {} not allowed", order.originId)
            return
        }

        if (order.products.isEmpty()) {
            log.info("not products")
            return
        }

        val sizeProducts = order.products.size
        log.info("size products {} ", sizeProducts)

        if (sizeProducts > 1) {
            log.info("product size greater than 1")
            return
        }

        val product = order.products.first();

        log.info("productId {}, productName {}", product.brand.id, product.brand.name)

        if (product.brand.id != 1L && product.brand.id != 12L) {
            return
        }

        log.info("categoryId {}, categoryName {}", product.category.id, product.category.name)

        if (product.category.id != 3L && product.category.id != 36L) {
            log.info("the product does not have a main course")
            return
        }

        continueCrossSellingTopping(token, countryId, flowId, order, product)
    }

    private suspend fun continueCrossSellingTopping(token: String, countryId: Long, flowId: Long, order: OrderDetailDTO, product: OrderDetailProductDTO) {
        val menuProductDetailDTO = getMenuProductDetail(token, countryId, product, flowId, order)

        var haveDrinks: Boolean = haveDrinks(product)
        var haveDesserts: Boolean = haveDesserts(product)
        var haveTopping: Boolean = haveTopping(product)

        log.info("haveDrinks {}, haveDesserts {}, haveTopping {} ", haveDrinks, haveDesserts, haveTopping)

        if (haveDrinks && haveDesserts && !haveTopping) {
            //TOPPING
            toppingCase(order, menuProductDetailDTO, product)
            log.info("001 TOPPING")
        } else if (!haveDrinks && !haveDesserts && haveTopping) {
            //DESSERTS/DRINKS
            caseDrinksOrDesserts(order, menuProductDetailDTO, product)
            log.info("002 DESSERTS_DRINKS")
        } else {
            //RANDOM
            mixedCase(order, menuProductDetailDTO, product)
            log.info("003 RANDOM")
        }
    }

    private fun caseDrinksOrDesserts(
        order: OrderDetailDTO,
        menuProductDetailDTO: MenuProductDetailDTO?,
        product: OrderDetailProductDTO
    ) {
        order.showCrossSelling = true
        order.showCrossSellingTopping = false
        order.mixedFlowCrossSelling = false
        applySuggestedGroups(menuProductDetailDTO, product)
    }

    private fun toppingCase(
        order: OrderDetailDTO,
        menuProductDetailDTO: MenuProductDetailDTO?,
        product: OrderDetailProductDTO
    ) {
        order.showCrossSelling = false
        order.showCrossSellingTopping = true
        order.mixedFlowCrossSelling = false
        applySuggestedGroups(menuProductDetailDTO, product)
    }

    private fun mixedCase(
        order: OrderDetailDTO,
        menuProductDetailDTO: MenuProductDetailDTO?,
        product: OrderDetailProductDTO
    ) {
        order.showCrossSelling = true
        order.showCrossSellingTopping = true
        order.mixedFlowCrossSelling = true
        applySuggestedGroups(menuProductDetailDTO, product)
    }

    private fun applySuggestedGroups(menuProductDetailDTO: MenuProductDetailDTO?, product: OrderDetailProductDTO) {
        if (menuProductDetailDTO != null) {
            product.size.suggestedGroups = getSuggestedGroups(menuProductDetailDTO, product)
        }
    }


    private fun haveDrinks(product: OrderDetailProductDTO): Boolean {
        val hasDrinks = product.size.groups.any { it.id in drinksGroupsCrossSellingIds }

        if (hasDrinks) {
            log.info("The product has beverage groups")
            return true
        }
        return false
    }

    private fun haveDesserts(product: OrderDetailProductDTO): Boolean {
        val hasDesserts = product.size.groups.any { it.id in dessertsGroupsCrossSellingIds }

        if (hasDesserts) {
            log.info("The product has groups of desserts")
            return true
        }
        return false
    }


    private fun haveTopping(product: OrderDetailProductDTO): Boolean {
        val hasTopping = product.size.groups.any { it.id in toppingGroupsCrossSellingIds }

        if (hasTopping) {
            log.info("The product has groups of topping")
            return true
        }

        return false
    }

    suspend fun getMenuProductDetail(token: String, countryId: Long, product: OrderDetailProductDTO, flowId: Long, order: OrderDetailDTO): MenuProductDetailDTO? {
        val result = getProductDetailUseCase.invoke(token, countryId, product.brand.id, flowId, order.store.id,
            null,
            listOf<Long>(product.article.menuHallProductId))

        return when (result) {
            is Result.Success -> result.data
            is Result.Error -> {
                log.info("Error: ${result.exception.message}")
                null
            }
        }
    }


    private fun getSuggestedGroups(
        menuProductDetailDTO: MenuProductDetailDTO,
        product: OrderDetailProductDTO
    ): List<MenuSizeGroupDTO> {//retorna una lista de sugeridos

        log.info("Started getSuggestedGroups()")

        //filtering by sizeId
        val menuProductSizeDTOFound = menuProductDetailDTO.sizes.find { it.id == product.size.id }


        if(menuProductSizeDTOFound == null){
            return emptyList()
        }

        //-----getting the groups ids that are to offer from menu----------
        val groupsToOfferFromMenu = menuProductSizeDTOFound.groups.filter{
            toppingGroupsCrossSellingIds.contains(it.id) }//?.flatMap { it.portions.map { it } }


        //------getting portion ids that are additions from product order detail-------
        val portionIdsInOrderDetail = product.size.groups.flatMap { it.portions.map {it.id} }


        groupsToOfferFromMenu.forEach { menuSizeGroupDto ->
            menuSizeGroupDto.portions = menuSizeGroupDto.portions.filterNot { it.id in portionIdsInOrderDetail }

            val sortedPortions = mutableListOf<MenuGroupProductDTO>()

            // Agregar primero los elementos presentes en portionIdsTop en el orden de portionIdsTop
            portionIdsTop.forEach { portionId ->
                val portion = menuSizeGroupDto.portions.find { it.id == portionId }
                portion?.let { sortedPortions.add(it) }
            }

            // Agregar los elementos restantes que no están en portionIdsTop al final de la lista
            val remainingPortions = menuSizeGroupDto.portions.filterNot { it.id in portionIdsTop }
            sortedPortions.addAll(remainingPortions)

            menuSizeGroupDto.portions = sortedPortions
        }


        return groupsToOfferFromMenu
    }

    private fun getFlagsResult(orders: List<OrderDetailDTO>,
                               countryId: Long,
                               platformId: Long?): FlagsDTO {
        val storeWithFlags: Boolean = isValueInStore(orders)
        return if (storeWithFlags) {
            addFlags(countryId,  platformId, true)
        } else {
            addFlags(countryId,  platformId, false)
        }
    }

    private fun isValueInStore(orderDetails: List<OrderDetailDTO>): Boolean {
        for (orderDetail in orderDetails) {
            for (storeId in crossSellingStore) {
                if (orderDetail.store.id == storeId) {
                    return true
                }
            }
        }
        return false
    }

    private fun addFlags(countryId: Long,  platformId: Long?, mustFlags: Boolean): FlagsDTO {
        var featureFlag1 = FeatureFlagsDTO("showPopupCrossSellingDessertsAndDrinks", false, 1)
        var featureFlag2 = FeatureFlagsDTO("showPopupCrossSellingToppings", false, 1)
        var featureFlag3 = FeatureFlagsDTO("timeToStartTimerCross", null, 2)
        var featureFlag4 = FeatureFlagsDTO("timeToEndTimerCross", null,2)
        var featureFlag5 = FeatureFlagsDTO("secondsInTimerCross", null, 3)

        var zoneId = "America/Sao_Paulo";

        if(countryId.toInt() == COLOMBIA){
            zoneId = "America/Bogota";
        }

        if(mustFlags){
            featureFlag1 = FeatureFlagsDTO("showPopupCrossSellingDessertsAndDrinks", true, 1)
            featureFlag2 = FeatureFlagsDTO("showPopupCrossSellingToppings", true, 1)
            featureFlag3 = FeatureFlagsDTO("timeToStartTimerCross",getCurrentDateTimeInZone(zoneId, crossSellingStartTimePopup, 0), 2)
            featureFlag4 = FeatureFlagsDTO("timeToEndTimerCross", getCurrentDateTimeInZone(zoneId, crossSellingEndTimePopup, 0), 2)
            featureFlag5 = FeatureFlagsDTO("secondsInTimerCross", secondsInTimerCross, 3)
        }
        return FlagsDTO(
            countryId = countryId,
            featureFlags = listOf(featureFlag1, featureFlag2, featureFlag3, featureFlag4, featureFlag5),
            platformId = platformId
        )
    }

    private fun getCurrentDateTimeInZone(zoneId: String, hour: Int, minute: Int): String {
        val timeZone = ZoneId.of(zoneId)
        val currentDate = LocalDate.now(timeZone)
        val dateTime = ZonedDateTime.of(currentDate, LocalTime.of(hour, minute), timeZone)
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}