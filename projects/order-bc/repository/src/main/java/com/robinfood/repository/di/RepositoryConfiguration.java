package com.robinfood.repository.di;

import com.robinfood.repository.orderdiscount.IOrderDiscountLocalDatasource;
import com.robinfood.repository.orderdiscount.IOrderDiscountRepository;
import com.robinfood.repository.orderdiscount.OrderDiscountRepository;
import com.robinfood.repository.transaction.ITransactionLocalDatasource;
import com.robinfood.repository.transaction.ITransactionRepository;
import com.robinfood.repository.transaction.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    IOrderDiscountRepository provideOrderDiscountRepository(
            IOrderDiscountLocalDatasource orderDiscountLocalDatasource
    ) {
        return new OrderDiscountRepository(orderDiscountLocalDatasource);
    }

    @Bean
    ITransactionRepository provideTransactionRepository(
            ITransactionLocalDatasource transactionLocalDatasource
    ) {
        return new TransactionRepository(transactionLocalDatasource);
    }
}
