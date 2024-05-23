package com.robinfood.network.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robinfood.network.api.ConfigurationBcAPI;
import com.robinfood.network.api.LambdaExchangesBcAPI;
import com.robinfood.network.api.LoyaltyBcAPI;
import com.robinfood.network.api.MenuBcAPI;
import com.robinfood.network.api.OrderBcAPI;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.network.api.SSOApi;
import com.robinfood.network.api.ServiceBcAPI;
import com.robinfood.network.api.UserBcAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class NetworkConfiguration {

    @Value("${url.configurationBc}")
    private String configurationBaseUrl;

    @Value("${url.lambdaExchangesBc}")
    private String lambdaExchangesBcUrl;

    @Value("${url.Loyalty}")
    private String loyaltyBaseUrl;

    @Value("${url.MenuBc}")
    private String menuBcBaseUrl;

    @Value("${url.orderBc}")
    private String orderBcBaseUrl;

    @Value("${url.serviceBc}")
    private String serviceBcBaseUrl;

    @Value("${url.sso}")
    private String ssoUrl;

    @Value("${url.orderBcQueries}")
    private String orderBcQueriesUrl;

    @Value("${url.userBc}")
    private String userBcUrl;

    public NetworkConfiguration() {
        // Document why this constructor is empty
    }

    @Bean
    public ConfigurationBcAPI provideConfigurationBcAPI() {
        return provideRetrofit(configurationBaseUrl, FieldNamingPolicy.IDENTITY)
                .create(ConfigurationBcAPI.class);
    }

    @Bean
    public LambdaExchangesBcAPI provideLambdaExchangesBcAPI() {
        return provideRetrofit(lambdaExchangesBcUrl, FieldNamingPolicy.IDENTITY)
                .create(LambdaExchangesBcAPI.class);
    }

    @Bean
    public LoyaltyBcAPI provideLoyaltyBcAPI() {
        return provideRetrofit(loyaltyBaseUrl, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create(LoyaltyBcAPI.class);
    }

    @Bean
    public MenuBcAPI provideMenuBcAPI() {
        return provideRetrofit(menuBcBaseUrl, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create(MenuBcAPI.class);
    }

    @Bean
    public OrderBcAPI provideOrderBcAPI() {
        return provideRetrofit(orderBcBaseUrl, FieldNamingPolicy.IDENTITY)
                .create(OrderBcAPI.class);
    }

    @Bean
    public ServiceBcAPI provideServiceBcAPI() {
        return provideRetrofit(serviceBcBaseUrl, FieldNamingPolicy.IDENTITY)
            .create(ServiceBcAPI.class);
    }

    @Bean
    public SSOApi provideTokenToBusinessCapabilityAPI() {
        return provideRetrofit(
                ssoUrl,
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        ).create(SSOApi.class);
    }

    @Bean
    public OrderBcQueriesAPI provideOrderBcQueriesAPI() {
        return provideRetrofit(orderBcQueriesUrl, FieldNamingPolicy.IDENTITY)
                .create(OrderBcQueriesAPI.class);
    }

    @Bean
    public UserBcAPI provideUserBcAPI() {
        return provideRetrofit(userBcUrl, FieldNamingPolicy.IDENTITY)
                .create(UserBcAPI.class);
    }

    private Retrofit provideRetrofit(String baseUrl, FieldNamingPolicy fieldNamingPolicy) {

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS).build();

        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(fieldNamingPolicy)
                .setPrettyPrinting()
                .create();

        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}
