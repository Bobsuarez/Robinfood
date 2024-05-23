package com.robinfood.ordereports_bc_muyapp.constants;

public final class APIConstants {

    public static final String USERS_V1 = "/api/v1/users";
    public static final String USERS_ORDER_DETAIL = "/detail/{transactionUuid}";

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }
}
