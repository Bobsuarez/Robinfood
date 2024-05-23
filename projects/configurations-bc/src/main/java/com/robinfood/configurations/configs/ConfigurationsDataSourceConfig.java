package com.robinfood.configurations.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "configurationsEntityManagerFactory",
    transactionManagerRef = "configurationsTransactionManager",
    basePackages = {"com.robinfood.configurations.repositories.jpa"})
public class ConfigurationsDataSourceConfig {

    @Primary
    @Bean(name = "configurationsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource configurationsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "configurationsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean configurationsEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("configurationsDataSource") DataSource dataSource) {
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return builder.dataSource(dataSource)
            .packages("com.robinfood.configurations.models")
            .persistenceUnit("configurationsPersistenceUnit")
            .properties(jpaProperties)
            .build();
    }

    @Primary
    @Bean(name = "configurationsTransactionManager")
    public PlatformTransactionManager configurationsTransactionManager(
        @Qualifier("configurationsEntityManagerFactory") EntityManagerFactory configurationsEntityManagerFactory) {
        return new JpaTransactionManager(configurationsEntityManagerFactory);
    }

}

