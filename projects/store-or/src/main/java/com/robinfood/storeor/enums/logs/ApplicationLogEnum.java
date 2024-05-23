package com.robinfood.storeor.enums.logs;

import io.netty.util.internal.StringUtil;

public enum ApplicationLogEnum {

    ORDER_START_CREATE_POS("0001", "Create pos has started with request {} "),
    ORDER_SUCCESSFULLY_CREATE_POS("0002", "Create pos has started with request {} "),
    ORDER_START_UPDATE_POS("0003", "Update pos has started with request {} and pos Id {}"),
    ORDER_CONFIGURATIONS_UPDATE_POS("0004", "Update pos has started with request {} and pos Id {}"),
    ORDER_START_CONFIGURATIONS_BC_UPDATE("0005", "Start [UpdatePosConfigurationsUseCase] update pos {} with Id {} "),
    ORDER_FINISH_CONFIGURATIONS_BC_UPDATE("0006", "Finish [UpdatePosConfigurationsUseCase] update successfully Id {} "),
    ORDER_SUCCESSFULLY_UPDATE_POS("0007", "pos response updated successfully");

    private final String code;
    private final String message;

    ApplicationLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.code + StringUtil.SPACE + this.message;
    }
}
