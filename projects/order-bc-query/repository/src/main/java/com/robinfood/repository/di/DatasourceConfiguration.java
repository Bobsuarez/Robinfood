package com.robinfood.repository.di;

import com.robinfood.repository.orderdiscount.IOrderDiscountLocalDatasource;
import com.robinfood.repository.orderdiscount.OrderDiscountLocalDatasource;
import com.robinfood.repository.transaction.ITransactionLocalDatasource;
import com.robinfood.repository.transaction.TransactionLocalDatasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfiguration {

    @Bean
    IOrderDiscountLocalDatasource provideOrderDiscountLocalDatasource() {
        return new OrderDiscountLocalDatasource();
    }

    @Bean
    ITransactionLocalDatasource provideTransactionLocalDatasource() {
        return new TransactionLocalDatasource();
    }
}
