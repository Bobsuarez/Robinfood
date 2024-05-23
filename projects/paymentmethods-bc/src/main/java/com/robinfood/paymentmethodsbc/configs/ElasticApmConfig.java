package com.robinfood.paymentmethodsbc.configs;

import static com.robinfood.paymentmethodsbc.constants.ElasticApmConstants.APPLICATION_PACKAGES_KEY;
import static com.robinfood.paymentmethodsbc.constants.ElasticApmConstants.ENVIRONMENT_KEY;
import static com.robinfood.paymentmethodsbc.constants.ElasticApmConstants.INITIAL_CAPACITY;
import static com.robinfood.paymentmethodsbc.constants.ElasticApmConstants.LOG_LEVEL_KEY;
import static com.robinfood.paymentmethodsbc.constants.ElasticApmConstants.SECRET_TOKEN_KEY;
import static com.robinfood.paymentmethodsbc.constants.ElasticApmConstants.SERVER_URLS_KEY;
import static com.robinfood.paymentmethodsbc.constants.ElasticApmConstants.SERVICE_NAME_KEY;
import static com.robinfood.paymentmethodsbc.constants.ElasticApmConstants.USE_PATH_AS_TRANSACTION_NAME;

import co.elastic.apm.attach.ElasticApmAttacher;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "elastic.apm.enabled")
public class ElasticApmConfig {

    @Value("${elastic.apm.server-url}")
    private String serverURL;

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

    @Value("${elastic.apm.use_path_as_transaction_name}")
    private String usePathAsTransactionName;

    @PostConstruct
    public void init() {

        Map<String, String> apmProps = new HashMap<>(INITIAL_CAPACITY);
        apmProps.put(SERVER_URLS_KEY, serverURL);
        apmProps.put(SERVICE_NAME_KEY, serviceName);
        apmProps.put(SECRET_TOKEN_KEY, secretToken);
        apmProps.put(ENVIRONMENT_KEY, environment);
        apmProps.put(APPLICATION_PACKAGES_KEY, applicationPackages);
        apmProps.put(LOG_LEVEL_KEY, logLevel);
        apmProps.put(USE_PATH_AS_TRANSACTION_NAME, usePathAsTransactionName);

        ElasticApmAttacher.attach(apmProps);
    }

}
