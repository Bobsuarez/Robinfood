package com.robinfood.network.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.robinfood.network.api.CouponsAPI
import com.robinfood.network.api.IntegrationsBackofficeAPI
import com.robinfood.network.api.MenuBCAPI
import com.robinfood.network.api.OrderCreationAPI
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.api.PaymentMethodsAPI
import com.robinfood.network.api.RickOrAPI
import com.robinfood.network.api.StoreOrAPI

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Configuration
open class NetworkConfiguration {

    @Value("\${url.coupons}")
    private lateinit var couponsBaseUrl: String

    @Value("\${url.integrationsBackoffice}")
    private lateinit var integrationsBackofficeUrl: String

    @Value("\${url.menuBC}")
    private lateinit var menuORUrl: String

    @Value("\${url.orderCreation}")
    private lateinit var orderCreationBaseUrl: String

    @Value("\${url.orderCreationQueries}")
    private lateinit var orderCreationQueriesBaseUrl: String

    @Value("\${url.paymentMethodsOR}")
    private lateinit var paymentMethodsBaseUrl: String

    @Value("\${url.storeOr_ch}")
    private lateinit var storeOrBaseUrl: String

    @Value("\${url.rickOr}")
    private lateinit var rickOrBaseUrl: String

    @Bean
    open fun provideCouponsAPI(): CouponsAPI {
        return provideRetrofit(couponsBaseUrl, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create(CouponsAPI::class.java)
    }

    @Bean
    open fun provideDispatcher(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

    @Bean
    open fun provideIntegrationsBackofficeAPI(): IntegrationsBackofficeAPI {
        return provideRetrofit(integrationsBackofficeUrl, FieldNamingPolicy.IDENTITY)
            .create(IntegrationsBackofficeAPI::class.java)
    }

    @Bean
    open fun provideMenuORAPI(): MenuBCAPI {
        return provideRetrofit(menuORUrl)
            .create(MenuBCAPI::class.java)
    }

    @Bean
    open fun provideOrderCreationAPI(): OrderCreationAPI {
        return provideRetrofit(orderCreationBaseUrl, FieldNamingPolicy.IDENTITY)
            .create(OrderCreationAPI::class.java)
    }

    @Bean
    open fun provideOrderCreationQueriesAPI(): OrderCreationQueriesAPI {
        return provideRetrofit(orderCreationQueriesBaseUrl, FieldNamingPolicy.IDENTITY)
            .create(OrderCreationQueriesAPI::class.java)
    }

    @Bean
    open fun providePaymentMethodsAPI(): PaymentMethodsAPI {
        return provideRetrofit(paymentMethodsBaseUrl, FieldNamingPolicy.IDENTITY)
            .create(PaymentMethodsAPI::class.java)
    }

    @Bean
    open fun provideStoreOrAPI(): StoreOrAPI {
        return provideRetrofit(storeOrBaseUrl, FieldNamingPolicy.IDENTITY)
            .create(StoreOrAPI::class.java)
    }

    @Bean
    open fun provideRickOrAPI(): RickOrAPI {
        return provideRetrofit(rickOrBaseUrl, FieldNamingPolicy.IDENTITY)
            .create(RickOrAPI::class.java)
    }

    private fun provideRetrofit(
        baseUrl: String,
        fieldNamingPolicy: FieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
    ): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS).build()

        val gson = GsonBuilder()
            .setFieldNamingPolicy(fieldNamingPolicy)
            .setPrettyPrinting()
            .create()

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}