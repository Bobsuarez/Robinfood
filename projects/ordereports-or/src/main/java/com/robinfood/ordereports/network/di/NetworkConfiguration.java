package com.robinfood.ordereports.network.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.robinfood.app.library.comunication.interceptor.SimpleLoggingInterceptor;
import com.robinfood.ordereports.network.api.OrderDetailBcAPI;
import com.robinfood.ordereports.network.api.PaymentMethodBcAPI;
import com.robinfood.ordereports.network.api.SSOApi;
import com.robinfood.ordereports.network.api.ServicesBcAPI;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class NetworkConfiguration {

    @Value("${url.payment-methods-bc}")
    private String baseUrlPaymentMethodBc;

    @Value("${url.services-bc}")
    private String baseUrlServicesBc;

    @Value("${url.sso}")
    private String ssoUrl;

    @Value("${url.orderreports-bc-muyapp}")
    private String baseUrlOrderDetailBc;

    @Bean
    public PaymentMethodBcAPI providePaymentMethodBcAPI() {
        return provideRetrofit(baseUrlPaymentMethodBc, new PropertyNamingStrategies.LowerCamelCaseStrategy())
                .create(PaymentMethodBcAPI.class);
    }

    @Bean
    public OrderDetailBcAPI provideOrderDetailBcAPI() {
        return provideRetrofit(baseUrlOrderDetailBc, new PropertyNamingStrategies.LowerCamelCaseStrategy())
                .create(OrderDetailBcAPI.class);
    }

    @Bean
    public ServicesBcAPI provideServicesBcAPI() {
        return provideRetrofit(baseUrlServicesBc, new PropertyNamingStrategies.LowerCamelCaseStrategy())
                .create(ServicesBcAPI.class);
    }

    @Bean
    public SSOApi provideTokenToBusinessCapabilityAPI() {
        return provideRetrofit(ssoUrl).create(SSOApi.class);
    }

    private Retrofit provideRetrofit(String baseUrl) {
        return provideRetrofit(baseUrl, new PropertyNamingStrategies.SnakeCaseStrategy());
    }

    private Retrofit provideRetrofit(String baseUrl, PropertyNamingStrategy propertyNamingStrategies) {

        log.info("Configurando API: {}", baseUrl);

        try {

            final OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new SimpleLoggingInterceptor())
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .callTimeout(30, TimeUnit.SECONDS)
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setPropertyNamingStrategy(propertyNamingStrategies);

            return new Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .build();

        } catch (Exception e) {
            log.error("Network Configuration has failed. {}", e.getMessage(), e);
        }

        return new Retrofit.Builder()
                .client(null)
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

}
