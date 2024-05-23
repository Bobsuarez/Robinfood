package com.robinfood.paymentmethodsbc.constants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("Permissions")
public class ControllerPermissions {
    public static final String PERM_CREDIT_CARD_LIST =
        "or_billing_creditcard_list";

    public static final String PERM_CREDIT_CARD_UPDATE =
        "or_billing_creditcard_update";

    public static final String PERM_CREDIT_CARD_DELETE =
        "or_billing_creditcard_delete";

    public static final String PERM_CREDIT_CARD_CREATE =
        "or_billing_creditcard_create";

    public static final String PERM_RESET_GW_CONFIG = "or_billing_cache_reset";

    public static final String PERM_SERVICE = "internal_service";

    private ControllerPermissions() {}

    public static List<String> getAllPermissions()
        throws IllegalAccessException {
        Field[] fields = ControllerPermissions.class.getDeclaredFields();
        List<String> permsList = new ArrayList<>();
        for (Field field : fields) {
            if (!field.getName().startsWith("PERM_")) {
                continue;
            }
            permsList.add(field.get(ControllerPermissions.class).toString());
        }
        return permsList;
    }
}
