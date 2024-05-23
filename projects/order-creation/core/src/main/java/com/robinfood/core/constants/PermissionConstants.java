package com.robinfood.core.constants;

public final class PermissionConstants {

    // Transaction permissions
    public static final String CREATE_TRANSACTION = "or_order_create_transaction";

    private PermissionConstants() {
        throw new IllegalStateException("Utility class");
    }
}
