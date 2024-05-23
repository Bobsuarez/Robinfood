package com.robinfood.paymentmethodsbc.constants;

public final class RedisConstants {

    public static final String PM_REDIS_GATEWAY_SETTINGS_KEY =
        "pm-gateway-settings-";
    public static final String PM_REDIS_SSO_SERVICE_TOKEN_KEY =
        "pm-sso-service-token";
    public static final String PM_REDIS_TERMINAL_PAYMENT_METHOD_SETTINGS_VISIBLE_KEY =
        "pm-terminalpaymentmethodsettings-terminal:%d:paymentgateway:%d:visible:%s";
    public static final String PM_REDIS_TERMINAL_PAYMENT_METHOD_SETTINGS_KEY =
        "pm-terminalpaymentmethodsettings-terminal:%d:paymentgateway:%d";
    public static final String PM_REDIS_PAYMENTMETHOD_STORE_FLOW_CHANNEL_KEY =
        "pm-paymentmethodstoreflowchannel-store:%d:channel:%d:flow:%d:status:%s";
}
