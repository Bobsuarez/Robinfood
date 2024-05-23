package com.robinfood.customersbc.thirdparties.configs;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.APPLICATION_PACKAGES_KEY;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.ENVIRONMENT_KEY;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.INITIAL_CAPACITY;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.LOG_LEVEL_KEY;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.SECRET_TOKEN_KEY;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.SERVER_URL_KEY;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.SERVICE_NAME_KEY;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.TRACE_CONTINUATION_STRATEGY;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.TRANSACTION_NAME_GROUPS;
import static com.robinfood.customersbc.thirdparties.constants.ElasticApmConstants.USE_PATH_AS_TRANSACTION_NAME;

@Configuration
@ConditionalOnProperty(
    value = "elastic.apm.enabled"
)
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

    @Value("${elastic.apm.trace-continuation-strategy}")
    private String traceContinuationStrategy;

    @Value("${elastic.apm.use-path-as-transaction-name}")
    private String usePathAsTransactionName;

    @Value("${elastic.apm.transaction-name-groups}")
    private String transactionNameGroups;

    @PostConstruct
    public void init() {

        Map<String, String> apmProps = new HashMap<>(INITIAL_CAPACITY);
        apmProps.put(SERVER_URL_KEY, serverURL);
        apmProps.put(SERVICE_NAME_KEY, serviceName);
        apmProps.put(SECRET_TOKEN_KEY, secretToken);
        apmProps.put(ENVIRONMENT_KEY, environment);
        apmProps.put(APPLICATION_PACKAGES_KEY, applicationPackages);
        apmProps.put(LOG_LEVEL_KEY, logLevel);
        apmProps.put(TRACE_CONTINUATION_STRATEGY, traceContinuationStrategy);
        apmProps.put(USE_PATH_AS_TRANSACTION_NAME, usePathAsTransactionName);
        apmProps.put(TRANSACTION_NAME_GROUPS, transactionNameGroups);

        ElasticApmAttacher.attach(apmProps);
    }
}
