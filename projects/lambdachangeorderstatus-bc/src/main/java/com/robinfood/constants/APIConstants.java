package com.robinfood.constants;

import java.util.Objects;

public class APIConstants {

    public static final String BROKER_USERNAME = System.getenv("BROKER_USERNAME");

    public static final String BROKER_PASSWORD = System.getenv("BROKER_PASSWORD");

    public static final String BROKER_URL = Objects.nonNull(System.getenv("BROKER_URL")) ? System.getenv("BROKER_URL") : System.getProperty("BROKER_URL");

    public static final String DESTINATION = Objects.nonNull(System.getenv("DESTINATION")) ? System.getenv("DESTINATION") : System.getProperty("DESTINATION");

    public static final String JWT_TOKEN_SECRET = Objects.nonNull(System.getenv("JWT_TOKEN_SECRET")) ? System.getenv("JWT_TOKEN_SECRET") : System.getProperty("JWT_TOKEN_SECRET");

    public static final String PILOT_STORES = Objects.nonNull(System.getenv("PILOT_STORES")) ? System.getenv("PILOT_STORES") : System.getProperty("PILOT_STORES");
}
