package com.robinfood.app.di

import com.robinfood.app.usecases.createtransaction.CreateTransactionUseCase
import com.robinfood.app.usecases.createtransaction.ICreateTransactionUseCase
import com.robinfood.app.usecases.crosssellingmenu.GetCrossSellingMenuUseCase
import com.robinfood.app.usecases.crosssellingmenu.IGetCrossSellingMenuUseCase
import com.robinfood.app.usecases.deleteorder.DeleteOrderUseCase
import com.robinfood.app.usecases.deleteorder.IDeleteOrderUseCase
import com.robinfood.app.usecases.getmenu.GetMenuStoreBrandsUseCase
import com.robinfood.app.usecases.getmenu.GetMenuUseCase
import com.robinfood.app.usecases.getmenu.IGetMenuStoreBrandsUseCase
import com.robinfood.app.usecases.getmenu.IGetMenuUseCase
import com.robinfood.app.usecases.getorderhistory.GetOrderHistoryUseCase
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase
import com.robinfood.app.usecases.getpaymentmethods.GetPaymentMethodsUseCase
import com.robinfood.app.usecases.getpaymentmethods.IGetPaymentMethodsUseCase
import com.robinfood.app.usecases.getstoredeliveryplatforms.GetStoreDeliveryPlatformsUseCase
import com.robinfood.app.usecases.getstoredeliveryplatforms.IGetStoreDeliveryPlatformsUseCase
import com.robinfood.app.usecases.menuproductdetail.*
import com.robinfood.app.usecases.orderdaily.GetOrdersDailyUseCase
import com.robinfood.app.usecases.orderdaily.IGetOrdersDailyUseCase
import com.robinfood.app.usecases.orderdetail.GetOrdersDetailUseCase
import com.robinfood.app.usecases.orderdetail.IGetOrdersDetailUseCase
import com.robinfood.app.usecases.validatecoupon.IValidateCouponUseCase
import com.robinfood.app.usecases.validatecoupon.ValidateCouponUseCase
import com.robinfood.app.usecases.validategiftcard.IValidateGiftCardUseCase
import com.robinfood.app.usecases.validategiftcard.ValidateGiftCardUseCase
import com.robinfood.app.usecases.validateorigin.IValidateOriginUseCase
import com.robinfood.app.usecases.validateorigin.ValidateOriginUseCase
import com.robinfood.app.usecases.validateuuid.IValidateUuidUseCase
import com.robinfood.app.usecases.validateuuid.ValidateUuidUseCase
import com.robinfood.repository.coupons.ICouponsRepository
import com.robinfood.repository.menu.IMenuRepository
import com.robinfood.repository.orderdaily.IOrdersDailyRepository
import com.robinfood.repository.orderdetail.IOrderDetailRepository
import com.robinfood.repository.orderhistory.IOrderHistoryRepository
import com.robinfood.repository.orders.IOrdersRepository
import com.robinfood.repository.paymentmethods.IPaymentMethodsRepository
import com.robinfood.repository.store.IStoreRepository
import com.robinfood.repository.transactions.ITransactionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class UseCaseConfiguration {

    @Bean
    open fun provideCreateTransactionUseCase(
            transactionRepository: ITransactionRepository,
            validateOriginUseCase: IValidateOriginUseCase,
            validateUuidCase: IValidateUuidUseCase
    ): ICreateTransactionUseCase {
        return CreateTransactionUseCase(
                transactionRepository, validateOriginUseCase, validateUuidCase
        )
    }

    @Bean
    open fun provideDeleteOrdersUseCase(
            ordersRepository: IOrdersRepository
    ): IDeleteOrderUseCase {
        return DeleteOrderUseCase(ordersRepository)
    }

    @Bean
    open fun provideGetChangePortionUseCase(
            menuRepository: IMenuRepository
    ): IGetSuggestedPortionsUseCase {
        return GetSuggestedPortionUseCase(menuRepository)
    }

    @Bean
    open fun provideGetMenuProductDetailUseCase(
            menuRepository: IMenuRepository
    ): IGetMenuProductUseCase {
        return GetMenuProductUseCase(menuRepository)
    }

    @Bean
    open fun provideGetMenuStoreBrandsUseCase(
            menuRepository: IMenuRepository
    ): IGetMenuStoreBrandsUseCase {
        return GetMenuStoreBrandsUseCase(menuRepository)
    }

    @Bean
    open fun provideGetMenuUseCase(
            getMenuStoreBrandsUseCase: IGetMenuStoreBrandsUseCase,
            menuRepository: IMenuRepository
    ): IGetMenuUseCase {
        return GetMenuUseCase(
                getMenuStoreBrandsUseCase,
                menuRepository
        )
    }

    @Bean
    open fun provideGetCrossSellingMenuUseCase(
            getMenuStoreBrandsUseCase: IGetMenuStoreBrandsUseCase,
            menuRepository: IMenuRepository,
            getProductDetailUseCase: IGetProductDetailUseCase
    ): IGetCrossSellingMenuUseCase {
        return GetCrossSellingMenuUseCase(
                getMenuStoreBrandsUseCase,
                menuRepository,
                getProductDetailUseCase
        )
    }

    @Bean
    open fun provideGetOrderHistoryUseCase(
            orderHistoryRepository: IOrderHistoryRepository
    ): IGetOrderHistoryUseCase {
        return GetOrderHistoryUseCase(orderHistoryRepository)
    }

    @Bean
    open fun provideGetOrdersDetailUseCase(
            orderDetailRepository: IOrderDetailRepository,
            getProductDetailUseCase: IGetProductDetailUseCase
    ): IGetOrdersDetailUseCase {
        return GetOrdersDetailUseCase(orderDetailRepository, getProductDetailUseCase)
    }

    @Bean
    open fun provideGetPaymentMethodsUseCase(
            paymentMethodsRepository: IPaymentMethodsRepository
    ): IGetPaymentMethodsUseCase {
        return GetPaymentMethodsUseCase(
                paymentMethodsRepository
        )
    }

    @Bean
    open fun provideGetProductDetailUseCase(
            getMenuProductUseCase: IGetMenuProductUseCase,
            groupProductsBySizeIdUseCase: IGroupProductsBySizeIdUseCase
    ): IGetProductDetailUseCase {
        return GetProductDetailUseCase(getMenuProductUseCase, groupProductsBySizeIdUseCase)
    }

    @Bean
    open fun provideGetStoreDeliveryPlatformsUseCase(
            storeRepository: IStoreRepository
    ): IGetStoreDeliveryPlatformsUseCase {
        return GetStoreDeliveryPlatformsUseCase(storeRepository)
    }

    @Bean
    open fun provideGroupProductsBySizeIdUseCase(
            getSuggestedPortionUseCase: IGetSuggestedPortionsUseCase
    ): IGroupProductsBySizeIdUseCase {
        return GroupProductsBySizeIdUseCase(getSuggestedPortionUseCase)
    }

    @Bean
    open fun provideValidateCouponUseCase(
            couponsRepository: ICouponsRepository
    ): IValidateCouponUseCase {
        return ValidateCouponUseCase(couponsRepository)
    }

    @Bean
    open fun provideValidateOriginUseCase(
    ): IValidateOriginUseCase {
        return ValidateOriginUseCase()
    }

    @Bean
    open fun provideValidateUuidUseCase(
    ): IValidateUuidUseCase {
        return ValidateUuidUseCase()
    }

    @Bean
    open fun provideGetOrdersToInvoiceUseCase(
            ordersToInvoiceRepository: IOrdersDailyRepository
    ): IGetOrdersDailyUseCase {
        return GetOrdersDailyUseCase(ordersToInvoiceRepository)
    }

    @Bean
    open fun provideValidateGiftCardUseCase(): IValidateGiftCardUseCase {
        return ValidateGiftCardUseCase()
    }
}