package com.robinfood.configurationsposbc;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ConfigurationsPosBcApplication {

    public static final String SERVER_URL_KEY = "server_url";
    public static final String SERVICE_NAME_KEY = "service_name";
    public static final String SECRET_TOKEN_KEY = "secret_token";
    public static final String ENVIRONMENT_KEY = "environment";
    public static final String APPLICATION_PACKAGES_KEY = "application_packages";
    public static final String LOG_LEVEL_KEY = "log_level";
    public static final Integer INITIAL_CAPACITY_APM_PROPS = 6;

    @Value("${elastic.apm.server-url}")
    private String apmServerURL;

    @Value("${elastic.apm.service-name}")
    private String serviceName;

    @Value("${elastic.apm.secret-token}")
    private String secretToken;

    @Value("${elastic.apm.environment}")
    private String environment;

    @Value("${elastic.apm.application-packages}")
    private String applicationPackages;

    @Value("${elastic.apm.log-level}")
    private String logLevel;

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationsPosBcApplication.class, args);
    }

    @PostConstruct
    public void init() {
        Map<String, String> apmProps = new HashMap<>(INITIAL_CAPACITY_APM_PROPS);
        apmProps.put(SERVER_URL_KEY, apmServerURL);
        apmProps.put(SERVICE_NAME_KEY, serviceName);
        apmProps.put(SECRET_TOKEN_KEY, secretToken);
        apmProps.put(ENVIRONMENT_KEY, environment);
        apmProps.put(APPLICATION_PACKAGES_KEY, applicationPackages);
        apmProps.put(LOG_LEVEL_KEY, logLevel);

        ElasticApmAttacher.attach(apmProps);
    }

}
