package com.robinfood.app.configs

import co.elastic.apm.attach.ElasticApmAttacher
import com.robinfood.core.constants.GlobalConstants.APM_MAP_PROPERTIES
import javax.annotation.PostConstruct


open class ElasticApmConfig(
        private val serverUrl: String,
        private val serviceName: String,
        private val secretToken: String,
        private val environment: String,
        private val applicationPackages: String,
        private val logLevel: String
        ) {

    @PostConstruct
    fun init() {
        val apmProps: MutableMap<String, String?> = HashMap(APM_MAP_PROPERTIES)
        apmProps[SERVER_URL_KEY] = serverUrl
        apmProps[SERVICE_NAME_KEY] = serviceName
        apmProps[SECRET_TOKEN_KEY] = secretToken
        apmProps[ENVIRONMENT_KEY] = environment
        apmProps[APPLICATION_PACKAGES_KEY] = applicationPackages
        apmProps[LOG_LEVEL_KEY] = logLevel
        ElasticApmAttacher.attach(apmProps)
    }

    companion object {
        private const val SERVER_URL_KEY = "server_url"
        private const val SERVICE_NAME_KEY = "service_name"
        private const val SECRET_TOKEN_KEY = "secret_token"
        private const val ENVIRONMENT_KEY = "environment"
        private const val APPLICATION_PACKAGES_KEY = "application_packages"
        private const val LOG_LEVEL_KEY = "log_level"
    }
}