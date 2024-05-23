package com.robinfood.network.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robinfood.network.api.CO2BCApi;
import com.robinfood.network.api.ConfigurationsBCAPI;
import com.robinfood.network.api.CouponSystemAPI;
import com.robinfood.network.api.DeductionsApi;
import com.robinfood.network.api.LoyaltyAPI;
import com.robinfood.network.api.MenuBCAPI;
import com.robinfood.network.api.MenuBaseAdminBCAPI;
import com.robinfood.network.api.OrderBCApi;
import com.robinfood.network.api.OrderBcQueryAPI;
import com.robinfood.network.api.OrderBillNumberGeneratorBCAPI;
import com.robinfood.network.api.OrderSyncDataBCAPI;
import com.robinfood.network.api.PaymentMethodsBCApi;
import com.robinfood.network.api.ServiceBCApi;
import com.robinfood.network.api.StoreConfigurationsAPI;
import com.robinfood.network.api.TaxesBCAPI;
import com.robinfood.network.api.TokenToBusinessCapabilityAPI;
import com.robinfood.network.api.UserBCAPI;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@RefreshScope
@Slf4j
public class NetworkConfiguration {

    @Value("${url.co2-bc}")
    private String co2BcBaseUrl;

    @Value("${url.configurations-bc}")
    private String configurationsBcUrl;

    @Value("${url.coupon-system}")
    private String couponSystemUrl;

    @Value("${url.cubano}")
    private String cubanoBaseUrl;

    @Value("${url.cubano-v2}")
    private String cubanoV2BaseUrl;

    @Value("${url.loyalty}")
    private String loyaltyBaseUrl;

    @Value("${url.menu-bc}")
    private String menuBCURL;

    @Value("${url.menu-base-admin-bc}")
    private String menuBaseAdminBCUrl;

    @Value("${url.order-bc}")
    private String orderBcBaseUrl;

    @Value("${url.order-bc-query}")
    private String orderBcQueryUrl;

    @Value("${url.order-billnumber-generator-bc}")
    private String orderBillNumberGeneratorUrl;

    @Value("${url.order-syncdata-bc}")
    private String orderSyncDataBcBaseUrl;

    @Value("${url.payment-methods-bc}")
    private String paymentMethodsBcBaseUrl;

    @Value("${url.services-bc}")
    private String serviceBcBaseUrl;

    @Value("${url.store-configurations}")
    private String storeConfigurationsBaseUrl;

    @Value("${url.taxes-bc}")
    private String taxesBcBaseUrl;

    @Value("${url.token-to-business-capability}")
    private String tokenToBusinessCapabilityBaseUrl;

    @Value("${url.users-bc}")
    private String usersBcBaseUrl;


    public NetworkConfiguration() {
        // This constructor is empty because it is a configuration class
    }

    @Bean
    @RefreshScope
    public ConfigurationsBCAPI provideConfigurationsBCAPI() {
        return provideRetrofit(configurationsBcUrl, FieldNamingPolicy.IDENTITY)
                .create(ConfigurationsBCAPI.class);
    }

    @Bean
    @RefreshScope
    public DeductionsApi provideDeductionsBCAPI() {
        return provideRetrofit(orderBcBaseUrl, FieldNamingPolicy.IDENTITY)
                .create(DeductionsApi.class);
    }

    @Bean
    @RefreshScope
    public OrderSyncDataBCAPI provideOrderSyncDataBCAPI() {
        return provideRetrofit(orderSyncDataBcBaseUrl, FieldNamingPolicy.IDENTITY)
                .create(OrderSyncDataBCAPI.class);
    }

    @Bean
    @RefreshScope
    public MenuBaseAdminBCAPI provideMenuBaseAdminBCAPI() {
        return provideRetrofit(
                menuBaseAdminBCUrl,
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
                .create(MenuBaseAdminBCAPI.class);
    }

    @Bean
    @RefreshScope
    public MenuBCAPI provideMenuBCAPI() {
        return provideRetrofit(
                menuBCURL,
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
                .create(MenuBCAPI.class);
    }

    @Bean
    @RefreshScope
    public OrderBCApi provideOrderBCAPI() {
        return provideRetrofit(orderBcBaseUrl, FieldNamingPolicy.IDENTITY)
                .create(OrderBCApi.class);
    }

    @Bean
    @RefreshScope
    public CouponSystemAPI provideCouponSystemAPI() {
        return provideRetrofit(
                couponSystemUrl,
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
                .create(CouponSystemAPI.class);
    }

    @Bean
    @RefreshScope
    public LoyaltyAPI provideLoyaltyAPI() {
        return provideRetrofit(
                loyaltyBaseUrl,
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
                .create(LoyaltyAPI.class);
    }

    @Bean
    @RefreshScope
    public TaxesBCAPI provideTaxesBCAPI() {
        return provideRetrofit(taxesBcBaseUrl, FieldNamingPolicy.IDENTITY)
                .create(TaxesBCAPI.class);
    }

    @Bean
    @RefreshScope
    public StoreConfigurationsAPI providePickupTimeAPI() {
        return provideRetrofit(
                storeConfigurationsBaseUrl,
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
                .create(StoreConfigurationsAPI.class);
    }

    @Bean
    @RefreshScope
    public TokenToBusinessCapabilityAPI provideTokenToBusinessCapabilityAPI() {
        return provideRetrofit(
                tokenToBusinessCapabilityBaseUrl,
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
                .create(TokenToBusinessCapabilityAPI.class);
    }

    @Bean
    @RefreshScope
    public PaymentMethodsBCApi providePaymentMethodsBCAPI() {
        return provideRetrofit(
                paymentMethodsBcBaseUrl,
                FieldNamingPolicy.IDENTITY
        )
                .create(PaymentMethodsBCApi.class);
    }

    @Bean
    @RefreshScope
    public ServiceBCApi provideServiceMethodsBCAPI() {
        return provideRetrofit(
                serviceBcBaseUrl,
                FieldNamingPolicy.IDENTITY
        )
                .create(ServiceBCApi.class);
    }

    @Bean
    @RefreshScope
    public CO2BCApi provideCO2BCAPI() {
        return provideRetrofit(co2BcBaseUrl, FieldNamingPolicy.IDENTITY)
                .create(CO2BCApi.class);
    }

    @Bean
    @RefreshScope
    public OrderBcQueryAPI provideOrderBcQueryAPI() {
        return provideRetrofit(orderBcQueryUrl, FieldNamingPolicy.IDENTITY)
                .create(OrderBcQueryAPI.class);
    }

    private Retrofit provideRetrofit(
            String baseUrl,
            FieldNamingPolicy fieldNamingPolicy
    ) {
        log.info("Configurando API: " + baseUrl);

        try {

            final OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new SimpleLoggingInterceptor())
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .callTimeout(30, TimeUnit.SECONDS)
                    .build();

            final Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(fieldNamingPolicy)
                    .setPrettyPrinting()
                    .create();

            return new Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        } catch (Exception e) {
            log.error("Network Configuration has failed. {}", e.getMessage(), e);
        }

        return new Retrofit.Builder()
                .client(null)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(null))
                .build();
    }

    @Bean
    public UserBCAPI provideUserBCAPI() {
        return provideRetrofit(usersBcBaseUrl, FieldNamingPolicy.IDENTITY)
                .create(UserBCAPI.class);
    }

    @Bean
    public OrderBillNumberGeneratorBCAPI provideOrderBillNumberGeneratorBCAPI() {
        return provideRetrofit(orderBillNumberGeneratorUrl, FieldNamingPolicy.IDENTITY)
                .create(OrderBillNumberGeneratorBCAPI.class);
    }
}
