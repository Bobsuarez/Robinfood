package com.robinfood.app.configs;

import static com.robinfood.core.constants.GlobalConstants.APM_MAP_PROPERTIES;

import co.elastic.apm.attach.ElasticApmAttacher;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@ConfigurationProperties(prefix = "elastic.apm")
@ConditionalOnProperty(value = "elastic.apm.enabled", havingValue = "true")
public class ElasticApmConfig {

    private static final String APPLICATION_PACKAGES_KEY = "application_packages";
    private static final String ENVIRONMENT_KEY = "environment";
    private static final String LOG_LEVEL_KEY = "log_level";
    private static final String SERVICE_NAME_KEY = "service_name";
    private static final String SERVER_URL_KEY = "server_url";
    private static final String SECRET_TOKEN_KEY = "secret_token";

    private String applicationPackages;
    private String environment;
    private String logLevel;
    private String serviceName;
    private String serverUrl;
    private String secretToken;

    public ElasticApmConfig() {
        // this constructor is empty because it is a configuration class
    }

    @PostConstruct
    public void init() {

        Map<String, String> apmProps = new HashMap<>(APM_MAP_PROPERTIES);
        apmProps.put(SERVER_URL_KEY, serverUrl);
        apmProps.put(SERVICE_NAME_KEY, serviceName);
        apmProps.put(SECRET_TOKEN_KEY, secretToken);
        apmProps.put(ENVIRONMENT_KEY, environment);
        apmProps.put(APPLICATION_PACKAGES_KEY, applicationPackages);
        apmProps.put(LOG_LEVEL_KEY, logLevel);

        ElasticApmAttacher.attach(apmProps);
    }
}
