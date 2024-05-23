package com.robinfood.enums;

import lombok.Getter;

@Getter
public enum AppLogsTraceEnum {

    PROGRAM_START("0001", "Execute routing integration handler %s , data : %s"),

    ENTERED_DATA("0002", "Entered path parameters uuid: %s and thirdParty :%s"),

    DATA_ASSIGN_THIRD_PARTY("0003", "Data entry third party %s"),

    DATA_INVOKE_USE_CASE("0004", "Data invoke useCase %s , %s:%s"),

    INIT_THIRD_PARTY("0005", "The process of saving third party starts with order_id %s , third_party %s"),

    SAVED_THIRD_PARTY("0006", "The third party’s information was saved"),

    INIT_CONNECTOR_SIMBA("0007", "The process of sending to SISMBA begins"),

    RESPONSE_CONNECTOR_SIMBA("0008", "this is the SISMBA connector response: %s"),

    INIT_ELECTRONIC_BILLING("0009", """
            The process of saving electronic billing starts with order_id %s , electronic_billing %s
            """),

    SAVED_ELECTRONIC_BILLING("0010", "The electronic billing's information was saved"),

    SEND_INFORMATION_SIMBA_OK("0011", "Data send to simba OK, Uuid: %s , Response: %s"),

    ORDER_SENT_SIMBA("0012", "Order sent to simba");

    private final String code;
    private final String message;

    AppLogsTraceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessageWithCode() {
        return code + " " + message;
    }

}
