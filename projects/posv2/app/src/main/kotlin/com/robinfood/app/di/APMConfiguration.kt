package com.robinfood.app.di

import com.robinfood.app.configs.ElasticApmConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class APMConfiguration() {

    @Value("\${elastic.apm.server-url}")
    private lateinit var serverUrl: String

    @Value("\${elastic.apm.service-name}")
    private lateinit var serviceName: String

    @Value("\${elastic.apm.secret-token}")
    private lateinit var secretToken: String

    @Value("\${elastic.apm.environment}")
    private lateinit var environment: String

    @Value("\${elastic.apm.application-packages}")
    private lateinit var applicationPackages: String

    @Value("\${elastic.apm.log-level}")
    private lateinit var logLevel: String

    @Bean
    open fun provideAPMConfig(): ElasticApmConfig {
        return ElasticApmConfig(serverUrl,
                serviceName,
                secretToken,
                environment,
                applicationPackages,
                logLevel
        )
    }
}