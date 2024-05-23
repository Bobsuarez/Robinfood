package com.robinfood.repository.di;

import com.robinfood.repository.changestatusorders.ChangeStatusOrdersRepository;
import com.robinfood.repository.changestatusorders.IChangeStatusOrdersRemoteDataSource;
import com.robinfood.repository.changestatusorders.IChangeStatusOrdersRepository;
import com.robinfood.repository.co2.CO2Repository;
import com.robinfood.repository.co2.ICO2RemoteDataSource;
import com.robinfood.repository.co2.ICO2Repository;
import com.robinfood.repository.coupons.CouponSystemRepository;
import com.robinfood.repository.coupons.ICouponSystemLocalDataSource;
import com.robinfood.repository.coupons.ICouponSystemRemoteDataSource;
import com.robinfood.repository.coupons.ICouponSystemRepository;
import com.robinfood.repository.electronicbill.ElectronicBillRepository;
import com.robinfood.repository.electronicbill.IElectronicBillRemoteDataSource;
import com.robinfood.repository.electronicbill.IElectronicBillRepository;
import com.robinfood.repository.getordersbytransaction.GetOrdersByTransactionRepository;
import com.robinfood.repository.getordersbytransaction.IGetOrdersByTransactionRemoteDataSource;
import com.robinfood.repository.getordersbytransaction.IGetOrdersByTransactionRepository;
import com.robinfood.repository.loyalty.IRedeemFoodCoinsRemoteDataSource;
import com.robinfood.repository.loyalty.IRedeemFoodCoinsRepository;
import com.robinfood.repository.loyalty.RedeemFoodCoinsRepository;
import com.robinfood.repository.menu.IMenuRemoteDataSource;
import com.robinfood.repository.menu.IMenuRepository;
import com.robinfood.repository.menu.MenuRepository;
import com.robinfood.repository.paymentmethods.IPaymentMethodPaidRemoteDatasource;
import com.robinfood.repository.paymentmethods.IPaymentMethodPaidRepository;
import com.robinfood.repository.paymentmethods.PaymentMethodPaidRepository;
import com.robinfood.repository.productfinancecategory.IProductFinanceCategoryRemoteDataSource;
import com.robinfood.repository.productfinancecategory.IProductFinanceCategoryRepository;
import com.robinfood.repository.productfinancecategory.ProductFinanceCategoryRepository;
import com.robinfood.repository.queue.activemq.paymentmethodsrefunds.IPaymentMethodRefundsDatasource;
import com.robinfood.repository.queue.activemq.paymentmethodsrefunds.IPaymentMethodRefundsRepository;
import com.robinfood.repository.queue.activemq.paymentmethodsrefunds.PaymentMethodRefundsRepository;
import com.robinfood.repository.services.IServicesDataSource;
import com.robinfood.repository.services.IServicesRepository;
import com.robinfood.repository.services.ServicesRepository;
import com.robinfood.repository.tax.ITaxRemoteDataSource;
import com.robinfood.repository.tax.ITaxRepository;
import com.robinfood.repository.tax.TaxRepository;
import com.robinfood.repository.transaction.ITransactionLocalDataSource;
import com.robinfood.repository.transaction.ITransactionRemoteDataSource;
import com.robinfood.repository.transaction.ITransactionRepository;
import com.robinfood.repository.transaction.TransactionRepository;
import com.robinfood.repository.user.IUserRemoteDataSource;
import com.robinfood.repository.user.IUserRepository;
import com.robinfood.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    IChangeStatusOrdersRepository provideChangeStatusOrdersRepository(
            ModelMapper modelMapper,
            IChangeStatusOrdersRemoteDataSource changeStatusOrdersRemoteDataSource
    ) {
        return new ChangeStatusOrdersRepository(modelMapper,
                changeStatusOrdersRemoteDataSource
        );
    }

    @Bean
    ICouponSystemRepository provideCouponSystemRepository(
            ICouponSystemLocalDataSource couponSystemLocalDataSource,
            ICouponSystemRemoteDataSource couponSystemRemoteDataSource
    ) {
        return new CouponSystemRepository(
                couponSystemLocalDataSource,
                couponSystemRemoteDataSource
        );
    }

    @Bean
    IMenuRepository provideMenuRepository(
            IMenuRemoteDataSource menuRemoteDataSource,
            ModelMapper modelmapper
    ) {
        return new MenuRepository(
                menuRemoteDataSource,
                modelmapper
        );
    }

    @Bean
    IServicesRepository provideServiceRepository(IServicesDataSource servicesDataSource) {
        return new ServicesRepository(servicesDataSource);
    }

    @Bean
    IProductFinanceCategoryRepository provideProductFinanceCategoryRepository(
            IProductFinanceCategoryRemoteDataSource productFinanceCategoryRemoteDataSource
    ) {
        return new ProductFinanceCategoryRepository(
                productFinanceCategoryRemoteDataSource
        );
    }

    @Bean
    IRedeemFoodCoinsRepository provideRedeemFoodCoinsRepository(
            IRedeemFoodCoinsRemoteDataSource redeemFoodCoinDataSource
    ) {
        return new RedeemFoodCoinsRepository(
                redeemFoodCoinDataSource
        );
    }

    @Bean
    ITaxRepository provideTaxRepository(
            ITaxRemoteDataSource taxRemoteDataSource
    ) {
        return new TaxRepository(taxRemoteDataSource);
    }

    @Bean
    ITransactionRepository provideTransactionRepository(
            ModelMapper modelMapper,
            ITransactionLocalDataSource transactionLocalDataSource,
            ITransactionRemoteDataSource transactionRemoteDataSource
    ) {
        return new TransactionRepository(
                modelMapper,
                transactionLocalDataSource,
                transactionRemoteDataSource
        );
    }

    @Bean
    IUserRepository provideUserRepository(IUserRemoteDataSource userRemoteDataSource) {
        return new UserRepository(userRemoteDataSource);
    }

    @Bean
    IPaymentMethodPaidRepository providePaymentMethodRepository(
            IPaymentMethodPaidRemoteDatasource paymentMethodPaidDatasource
    ) {
        return new PaymentMethodPaidRepository(paymentMethodPaidDatasource);
    }

    @Bean
    ICO2Repository provideCO2Repository(
            ICO2RemoteDataSource co2RemoteDataSource
    ) {
        return new CO2Repository(co2RemoteDataSource);
    }

    @Bean
    IGetOrdersByTransactionRepository provideGetOrdersByTransactionRepository(
            ModelMapper modelMapper,
            IGetOrdersByTransactionRemoteDataSource getOrdersByTransactionRemoteDataSource
    ) {
        return new GetOrdersByTransactionRepository(modelMapper, getOrdersByTransactionRemoteDataSource);
    }

    @Bean
    IPaymentMethodRefundsRepository providePaymentMethodRefundsRepository(
            IPaymentMethodRefundsDatasource paymentMethodRefundsDatasource
    ) {
        return new PaymentMethodRefundsRepository(paymentMethodRefundsDatasource);
    }

    @Bean
    IElectronicBillRepository provideElectronicBillRepository(
            IElectronicBillRemoteDataSource electronicBillRemoteDataSource
    ) {
        return new ElectronicBillRepository(electronicBillRemoteDataSource);
    }

}
