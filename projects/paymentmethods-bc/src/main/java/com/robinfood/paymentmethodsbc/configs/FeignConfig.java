package com.robinfood.paymentmethodsbc.configs;

import feign.codec.Decoder;
import feign.codec.Encoder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Configuration class for OpenFeing Component
 */
@Configuration
public class FeignConfig {

    /**
     * Enable decoder for bug related when use webflux components
     * @return
     */
    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(
            new SpringDecoder(feignHttpMessageConverter())
        );
    }

    @Bean
    public Encoder feignEncoder() {
        return new SpringEncoder(feignHttpMessageConverter());
    }

    /**
     * HttpMessageConverters for openfeing, this implementation fix a bug
     * related to use webflux whit openfeing
     * @return
     */
    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(
            new GateWayMappingJackson2HttpMessageConverter()
        );
        return () -> httpMessageConverters;
    }

    public static class GateWayMappingJackson2HttpMessageConverter
        extends MappingJackson2HttpMessageConverter {

        GateWayMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(
                MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
            );
            setSupportedMediaTypes(mediaTypes);
        }
    }
}
