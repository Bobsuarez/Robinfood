package com.robinfood.repository.di;

import com.robinfood.network.api.CO2BCApi;
import com.robinfood.network.api.CouponSystemAPI;
import com.robinfood.network.api.LoyaltyAPI;
import com.robinfood.network.api.MenuBCAPI;
import com.robinfood.network.api.MenuBaseAdminBCAPI;
import com.robinfood.network.api.OrderBCApi;
import com.robinfood.network.api.OrderBillNumberGeneratorBCAPI;
import com.robinfood.network.api.PaymentMethodsBCApi;
import com.robinfood.network.api.ServiceBCApi;
import com.robinfood.network.api.TaxesBCAPI;
import com.robinfood.network.api.UserBCAPI;
import com.robinfood.repository.changestatusorders.ChangeStatusOrdersRemoteDataSource;
import com.robinfood.repository.changestatusorders.IChangeStatusOrdersRemoteDataSource;
import com.robinfood.repository.co2.CO2RemoteDataSource;
import com.robinfood.repository.co2.ICO2RemoteDataSource;
import com.robinfood.repository.coupons.CouponSystemLocalDataSource;
import com.robinfood.repository.coupons.CouponSystemRemoteDataSource;
import com.robinfood.repository.coupons.ICouponSystemLocalDataSource;
import com.robinfood.repository.coupons.ICouponSystemRemoteDataSource;
import com.robinfood.repository.electronicbill.ElectronicBillRemoteDataSource;
import com.robinfood.repository.electronicbill.IElectronicBillRemoteDataSource;
import com.robinfood.repository.getordersbytransaction.GetOrdersByTransactionRemoteDataSource;
import com.robinfood.repository.getordersbytransaction.IGetOrdersByTransactionRemoteDataSource;
import com.robinfood.repository.loyalty.IRedeemFoodCoinsRemoteDataSource;
import com.robinfood.repository.loyalty.RedeemFoodCoinsRemoteDataSource;
import com.robinfood.repository.menu.IMenuRemoteDataSource;
import com.robinfood.repository.menu.MenuRemoteDataSource;
import com.robinfood.repository.paymentmethods.IPaymentMethodPaidRemoteDatasource;
import com.robinfood.repository.paymentmethods.PaymentMethodPaidRemoteDatasource;
import com.robinfood.repository.queue.activemq.paymentmethodsrefunds.IPaymentMethodRefundsDatasource;
import com.robinfood.repository.queue.activemq.paymentmethodsrefunds.PaymentMethodRefundsDatasource;
import com.robinfood.repository.services.IServicesDataSource;
import com.robinfood.repository.services.ServicesDataSource;
import com.robinfood.repository.tax.ITaxRemoteDataSource;
import com.robinfood.repository.tax.TaxRemoteDataSource;
import com.robinfood.repository.transaction.ITransactionLocalDataSource;
import com.robinfood.repository.transaction.ITransactionRemoteDataSource;
import com.robinfood.repository.transaction.TransactionLocalDataSource;
import com.robinfood.repository.transaction.TransactionRemoteDataSource;
import com.robinfood.repository.user.IUserRemoteDataSource;
import com.robinfood.repository.user.UserRemoteDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class DataSourceConfiguration {

    @Bean
    IChangeStatusOrdersRemoteDataSource provideChangeStatusOrdersRemoteDataSource(
            OrderBCApi orderBCApi
    ) {
        return new ChangeStatusOrdersRemoteDataSource(orderBCApi);
    }

    @Bean
    @ApplicationScope
    ICouponSystemLocalDataSource provideCouponSystemLocalDataSource() {
        return new CouponSystemLocalDataSource();
    }

    @Bean
    ICouponSystemRemoteDataSource provideCouponSystemRemoteDataSource(
            CouponSystemAPI couponSystemAPI
    ) {
        return new CouponSystemRemoteDataSource(couponSystemAPI);
    }

    @Bean
    IMenuRemoteDataSource provideMenuRemoteDataSource(
            MenuBaseAdminBCAPI menuBaseAdminBCAPI,
            MenuBCAPI menuBCAPI
    ) {
        return new MenuRemoteDataSource(menuBaseAdminBCAPI, menuBCAPI);
    }

    @Bean
    IServicesDataSource provideValidateDataSource(
            ServiceBCApi serviceBCApi
    ) {
        return new ServicesDataSource(serviceBCApi);
    }

    @Bean
    @ApplicationScope
    ITransactionLocalDataSource provideTransactionLocalDataSource() {
        return new TransactionLocalDataSource();
    }

    @Bean
    ITransactionRemoteDataSource provideOrdersRemoteDataSource(
            OrderBCApi orderBCApi
    ) {
        return new TransactionRemoteDataSource(orderBCApi);
    }

    @Bean
    IRedeemFoodCoinsRemoteDataSource provideRedeemFoodCoinsDataSource(
            LoyaltyAPI loyaltyAPI
    ) {
        return new RedeemFoodCoinsRemoteDataSource(loyaltyAPI);
    }

    @Bean
    ITaxRemoteDataSource provideTaxRemoteDataSource(TaxesBCAPI taxesBCAPI) {
        return new TaxRemoteDataSource(taxesBCAPI);
    }

    @Bean
    IUserRemoteDataSource provideUserRemoteDataSource(
            OrderBCApi orderBCApi,
            UserBCAPI userBCAPI
    ) {
        return new UserRemoteDataSource(orderBCApi, userBCAPI);
    }

    @Bean
    IPaymentMethodPaidRemoteDatasource providePaymentMethodPaidRemoteDatasource(
            PaymentMethodsBCApi paymentMethodsBCApi
    ) {
        return new PaymentMethodPaidRemoteDatasource(paymentMethodsBCApi);
    }

    @Bean
    ICO2RemoteDataSource provideCO2RemoteDataSource(CO2BCApi co2BCAPI) {
        return new CO2RemoteDataSource(co2BCAPI);
    }

    @Bean
    IGetOrdersByTransactionRemoteDataSource provideGetOrdersByTransactionRemoteDataSource(
            OrderBCApi orderBCApi
    ) {
        return new GetOrdersByTransactionRemoteDataSource(orderBCApi);
    }

    @Bean
    IPaymentMethodRefundsDatasource providePaymentMethodRefundsDatasource(
            PaymentMethodsBCApi paymentMethodsBCApi
    ) {
        return new PaymentMethodRefundsDatasource(paymentMethodsBCApi);
    }

    @Bean
    IElectronicBillRemoteDataSource provideElectronicBillRemoteDataSource(
            OrderBillNumberGeneratorBCAPI orderBillNumberGeneratorBCAPI
    ) {
        return new ElectronicBillRemoteDataSource(orderBillNumberGeneratorBCAPI);
    }
}
